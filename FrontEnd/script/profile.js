//let token= JSON.stringify(localStorage.getItem());
let walletDetails;
let bookingBody = document.querySelector("#booking-table-body");
let transactionBody = document.querySelector("#transaction-div-body");
let statusbtn = document.querySelector("#status-btn");
let addbtn = document.querySelector("#addMoney-btn");
let token = localStorage.getItem("jwtToken") || "";
let loggedUser = JSON.parse(localStorage.getItem("userData")) || {};

//let token= `eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBbWFuIiwic3ViIjoiSldUIFRva2VuIiwidXNlcm5hbWUiOiJkaGFudXNoQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjoiUk9MRV9BRE1JTiIsImlhdCI6MTY4NzI4MDQ1MiwiZXhwIjoxNjg3MzQwNDUyfQ.U5yb3Nxs6KI5-pYqvhlSZZuwsiKYD8miZE1R-rqpwWY`;
// let loggedUser={
//     "userId": 1,
//     "username": "Dhanush",
//     "email": "dhanush@gmail.com",
//     "phone": "12345567890",
//     "address": "kanpur",
//     "role": "ROLE_ADMIN",
//     "cabBookings": [],
//     "wallet": {
//         "walletId": 1,
//         "balance": 0.0,
//         "status": "Active",
//         "transactions": []
//     }
// };


window.onload = () => {
    console.log("working");
    document.querySelector("#user-name").textContent = loggedUser.username;
    document.querySelector("#user-email").textContent = loggedUser.email;
    document.querySelector("#user-address").textContent = loggedUser.address;
    document.querySelector("#user-mobile").textContent = loggedUser.phone;
    document.querySelector("#user-role").textContent = loggedUser.role;
    showAllBookings();
    showAllTransaction();
    getWalletData();



};

let getWalletData = async () => {

    try {
        let res = await fetch(`http://localhost:8888/WALLET/WalletDetails`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }
        });

        if (res.ok) {
            loggedUser.wallet = await res.json();
            assignValuetowallet(loggedUser.wallet);
        }
        else {
            let response = await res.json();
            console.log(response);
        }
    } catch (error) {
        alert(error);
    }



};

let assignValuetowallet = (walletDetails) => {
    document.querySelector("#balance-box").textContent = walletDetails.balance;
    document.querySelector("#status-box").textContent = walletDetails.status;

}



statusbtn.onclick = async (event) => {

    try {
        let res = await fetch(`http://localhost:8888/WALLET/changeStatus/${loggedUser.wallet.walletId}`, {
            method: 'PATCH',
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },

        });

        if (res.ok) {
            walletDetails = await res.json();
            loggedUser.wallet = walletDetails;

            assignValuetowallet(loggedUser.wallet);
            document.querySelector("#status-btn").textContent = walletDetails.status;
        }
        else {
            alert("Something went wrong");
        }
    } catch (error) {
        alert(error);
    }


};


addbtn.onclick = async (event) => {
    try {
        let amount = prompt("Enter amount:");

        let res = await fetch(`http://localhost:8888/WALLET/addMoney?amount=${amount}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }
        });

        if (res.ok) {
            walletDetails = await res.json();
            loggedUser.wallet = walletDetails;
            assignValuetowallet(loggedUser.wallet);
        }
        else {
            alert("Something went wrong");
        }
    } catch (error) {
        alert(error);
    }

};



let bookingAppend = (data) => {
    data.forEach(ele => {
        let row = document.createElement("tr");
        let td1 = document.createElement("td");
        td1.textContent = ele.cabBookingId;
        let td2 = document.createElement("td");
        td2.textContent = ele.fromLocation;
        let td3 = document.createElement("td");
        td3.textContent = ele.toLocation;
        let td4 = document.createElement("td");
        td4.textContent = ele.fromDateTime;
        let td5 = document.createElement("td");
        td5.textContent = ele.status;
        if (td5.textContent == "Booked") {
            td5.style.color = "Green";
        }
        else {
            td5.style.color = "Red";
        }
        let td6 = document.createElement("td");
        td6.textContent = ele.distanceInKm;
        let td7 = document.createElement("td");
        td7.textContent = ele.bill;

        row.append(td1, td2, td3, td4, td5, td6, td7);
        bookingBody.append(row);

    });
};

let transactionAppend = (data) => {
    data.forEach(ele => {
        let row = document.createElement("tr");
        let td1 = document.createElement("td");
        td1.textContent = ele.transactionId;
        let td2 = document.createElement("td");
        td2.textContent = ele.transactionDate;
        let td3 = document.createElement("td");
        td3.textContent = ele.amount;
        let td4 = document.createElement("td");
        td4.textContent = ele.type;

        if (td4.textContent == "Credit") {
            td4.style.color = "Green";
        }
        else {
            td4.style.color = "Red";
        }
        let td5 = document.createElement("td");
        td5.textContent = ele.currentBalance;


        row.append(td1, td2, td3, td4, td5);
        transactionBody.append(row);

    });

}


let showAllBookings = async () => {

    try {
        let res = await fetch(`http://localhost:8888/users/${loggedUser.email}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`,
            }
        });

        if (res.ok) {
            let user = await res.json();
            let data = user.cabBookings;
            console.log(data);
            bookingAppend(data);
        } else {
            let error = await res.json();
            //  alert(error.HttpStatus);
        }

    } catch (error) {
        alert(error);
    }

};

let showAllTransaction = async () => {
    try {
        let res = await fetch(`http://localhost:8888/WALLET/getWallet`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`,
            }
        });

        if (res.ok) {
            let user = await res.json();
            let data = user.transactions;
            console.log(data);
            console.log("testing");
            transactionAppend(data);
        } else {
            let error = await res.json();
            //  alert(error.HttpStatus);
        }

    } catch (error) {
        alert(error);
    }

};







