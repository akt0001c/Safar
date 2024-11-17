//let token= JSON.stringify(localStorage.getItem());
let walletDetails;
let bookingBody = document.querySelector("#booking-table-body");
let transactionBody = document.querySelector("#transaction-div-body");
let statusbtn = document.querySelector("#status-btn");
let addbtn = document.querySelector("#addMoney-btn");
let token = localStorage.getItem("jwtToken") || "";
let loggedUser = JSON.parse(localStorage.getItem("userData")) || {};

const myDetailsBtn= document.querySelector("#my-details-btn");
const walletBtn= document.querySelector("#wallet-btn");
const transactionBtn= document.querySelector("#transaction-btn");
const bookingBtn= document.querySelector("#booking-btn");


const myDetailsSection= document.querySelector("#details-section");
const walletSection= document.querySelector("#wallet-section");
const transctionSection= document.querySelector("#transaction-section");
const bookingSection= document.querySelector("#booking-section");


// Get elements
const addMoneyBtn = document.getElementById('addMoney-btn');
const addMoneyPopup = document.getElementById('addMoneyPopup');
const closePopupBtn = document.getElementById('closePopup');
const confirmAddMoneyBtn = document.getElementById('confirmAddMoney');

// Show the popup when the "Add Money" button is clicked
addMoneyBtn.addEventListener('click', function() {
    addMoneyPopup.classList.remove('hidden');
});

// Close the popup when the "Close" button is clicked
closePopupBtn.addEventListener('click', function() {
    addMoneyPopup.classList.add('hidden');
});

// Optional: Handle the confirm button click
confirmAddMoneyBtn.addEventListener('click', function() {
    const amount = document.getElementById('amount').value;
    if (amount) {
        // Here you can add your logic to add money
        console.log(`Adding money: ${amount}`);
        // Close the popup after confirming
        addMoneyPopup.classList.add('hidden');
    } else {
        alert('Please enter an amount.');
    }
});

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
    myDetailsSection.style.display="block";
    document.querySelector("#user-name").textContent = loggedUser.username;
    document.querySelector("#user-email").textContent = loggedUser.email;
    document.querySelector("#user-address").textContent = loggedUser.address;
    document.querySelector("#user-mobile").textContent = loggedUser.phone;
    document.querySelector("#user-role").textContent = loggedUser.role;
    getWalletData();
    showAllBookings();
    showAllTransaction();
   



};


myDetailsBtn.onclick= ()=>{
    myDetailsSection.style.display="block";
    walletSection.style.display="none";
    transctionSection.style.display="none";
    bookingSection.style.display="none";
};

walletBtn.onclick = ()=>{
    myDetailsSection.style.display="none";
    walletSection.style.display="block";
    transctionSection.style.display="none";
    bookingSection.style.display="none";
};

transactionBtn.onclick= ()=>{
    myDetailsSection.style.display="none";
    walletSection.style.display="none";
    transctionSection.style.display="block";
    bookingSection.style.display="none";
};


bookingBtn.onclick= ()=>{
    myDetailsSection.style.display="none";
    walletSection.style.display="none";
    transctionSection.style.display="none";
    bookingSection.style.display="block";
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
            localStorage.setItem("userData", JSON.stringify(loggedUser));
            assignValuetowallet(loggedUser.wallet);
        }
        else {
            let response = await res.json();
            console.log(response);
        }
    } catch (error) {
        // alert(error);
        console.log(error);
    }



};

let assignValuetowallet = (walletDetails) => {
    document.querySelector("#balance-box").textContent = walletDetails.balance;
    document.querySelector("#status-box").textContent = walletDetails.status;

}



statusbtn.onclick = async (event) => {

    try {
        let res = await fetch(`http://localhost:8888/WALLET/changeStatus`, {
            method: 'PATCH',
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },

        });

        if (res.ok) {
            walletDetails = await res.json();
            loggedUser.wallet = walletDetails;
            localStorage.setItem("userData", JSON.stringify(loggedUser));
            assignValuetowallet(loggedUser.wallet);
            if (loggedUser.wallet.status === "Active")
                document.querySelector("#status-btn").textContent = "Disable";
            else
                document.querySelector("#status-btn").textContent = "Enable";
        }
        else {
            alert("Something went wrong");
        }
    } catch (error) {
        // alert(error);
        console.log(error);
    }


};


// addbtn.onclick = async (event) => {
//     try {
//         let amount = prompt("Enter amount:");

//         let res = await fetch(`http://localhost:8888/WALLET/addMoney?amount=${amount}`, {
//             method: "POST",
//             headers: {
//                 "Content-Type": "application/json",
//                 "Authorization": `Bearer ${token}`
//             }
//         });

//         if (res.ok) {
//             walletDetails = await res.json();
//             loggedUser.wallet = walletDetails;
//             assignValuetowallet(loggedUser.wallet);
//             showAllTransaction();
//         }
//         else {
//             alert("Something went wrong");
//         }
//     } catch (error) {
//         // alert(error);
//         console.log(error);
//     }

// };



let bookingAppend = (data) => {
    bookingBody.innerHTML = null;
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
        let td8 = document.createElement("button");
        td8.textContent = "Complete Ride"
        td8.setAttribute("class", "payBill_btn");
        td8.style.backgroundColor = "gray";
        td8.style.color="white";
        td8.style.width="100%";
        if(ele.status==='COMPLETED')
        {
                td8.disabled=true;
                td8.textContent='Already completed';
        }
            
         td8.onclick = async (event) => {
           
            console.log(ele);
            console.log("t8" + ele.cabBookingId);
            
            var myHeaders = new Headers();
            myHeaders.append("Authorization", `Bearer ${token}`);
            var requestOptions = {
                method: 'GET',
                headers: myHeaders,
                redirect: 'follow'
            };
            
            fetch(`http://localhost:8888/cabBooking/completeTrip/${ele.cabBookingId}`, requestOptions)
            .then(response => {
                
                if (response.ok) {
                    console.log("hii")
                    response.json().then(data => {
                        Swal.fire(
                            'Good job!',
                            'Ride Completed Successfully ',
                            'success'
                        )
                        showAllBookings()
                        console.log(data);
                        setTimeout(() => {
                            window.location.href = "/profile.html"
                        }, 2000)
                    })
    
                } else {
                    td8.disabled=false;
                    response.json().then(data => Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: data.message,
                        footer: '<a href="">Why do I have this issue?</a>'
                        
                    }));
                }
            })

        }

        row.append(td1, td2, td3, td4, td5, td6, td7, td8);
        bookingBody.append(row);

    });
};

let transactionAppend = (data) => {
    transactionBody.innerHTML = null;
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
        console.log(token);
      
       let res= await fetch(`http://localhost:8888/cabBooking/history`,{
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`,
            }
        });

        if (res.ok) {
            let data = await res.json();
         
            console.log(data);
            bookingAppend(data);
        } else {
            let error = await res.json();
           
            console.log(error);
        }

    } catch (error) {
        // alert(error);
        console.log(error);
    }

};

let showAllTransaction = async () => {
    try {
        let wid= loggedUser.wallet.walletId;
        let res = await fetch(`http://localhost:8888/WALLET/getWallet/${wid}`, {
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
        // alert(error);
        console.log(error);
    }

};







