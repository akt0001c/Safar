//let token= JSON.stringify(localStorage.getItem());
let walletDetails;
let bookingBody= document.querySelector("#booking-table-body");
let transactionBody= document.querySelector("#transaction-div-body");
let statusbtn= document.querySelector("#status-btn");
let addbtn= document.querySelector("#addMoney-btn");
// let token= JSON.parse(localStorage.getItem("jwtToken"))||"";
// let loggedUser= JSON.parse(localStorage.getItem("loggedUser"))||{};

let token=`eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBbWFuIiwic3ViIjoiSldUIFRva2VuIiwidXNlcm5hbWUiOiJkaGFudXNoQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjoiUk9MRV9BRE1JTiIsImlhdCI6MTY4NzA5NDM4NSwiZXhwIjoxNjg3MTU0Mzg1fQ.fY1CEGUC73ajFePcU7QxZBnFpqltFWapS6hBxK3ZYs4`;
let loggedUser={
    "userId":2,
    "username":"Ankit choubey",
    "email":"ankit@gmail.com",
    "address":"Uttarakhand",
    "phone":"1234567893",
    "role":"User",
    "wallet":{
        "walletId":100,
        "balance":0.0,
        "status":"Active",

    }
};


window.onload= ()=>{
    console.log("working");
   document.querySelector("#user-name").textContent=loggedUser.username;
   document.querySelector("#user-email").textContent=loggedUser.email;
   document.querySelector("#user-address").textContent=loggedUser.address;
   document.querySelector("#user-mobile").textContent=loggedUser.phone;
   document.querySelector("#user-role").textContent=loggedUser.role;
   showAllBookings();
   

};  

// let createWallet= async (loggedUser)=>{
// try {
//     let res= await fetch(`http://localhost:8888//WALLET/createWallet/${loggedUser.userId}`,{
//         method:"POST",
//         headers:{
//             "Content-Type":"application/json",
//             "Authorization": `Bearer ${token}`
//         },
       
//     });
// if(res.ok)
//   {
//      walletDetails= await res.json();
//     assignValuetowallet(walletDetails);
//   }
//    else 
//     {
//       let message= await res.json();
//       alert(message); 
//     }
// } catch (error) {
//     alert(error);
// }

// }

let assignValuetowallet= (walletDetails)=>{
    document.querySelector("#balance-box").textContent=walletDetails.balance;
    document.querySelector("#status-box").textContent=walletDetails.status;
   
}



statusbtn.onclick= async (event)=>{

 try {
    let  res= await fetch(`http://localhost:8888//WALLET/changeStatus/${loggedUser.wallet.walletId}`,{
    method:'PATCH',
    headers:{
        "Content-Type":"application/json",
        "Authorization": `Bearer ${token}`
    },
    
   });

   if(res.ok)
    {
        walletDetails= await res.json();
        loggedUser.wallet=walletDetails;

       assignValuetowallet(loggedUser.wallet);
       document.querySelector("#status-btn").textContent=walletDetails.status;
    }
     else
      {
         alert("Something went wrong");
      }
 } catch (error) {
    alert(error);
 }
   
   
};


addbtn.onclick= async (event)=>{
    try {
        let amount= prompt("Enter amount:");

        let res= await fetch(`http://localhost:8888/WALLET?Id=${loggedUser.wallet.walletId}&amount=${amount}`,{
            method:"POST",
            headers:{
                "Content-Type":"application/json",
                "Authorization":`Bearer ${token}`
            }
        });
    
        if(res.ok)
          {
           walletDetails= await res.json();
           loggedUser.wallet=walletDetails;
           assignValuetowallet(loggedUser.wallet);
          }
          else
           {
            alert("Something went wrong");
           }
    } catch (error) {
        alert(error);
    }
   
};



let bookingAppend= (data)=>{
   data.forEach(ele => {
       let row= document.createElement("tr");
       let td1= document.createElement("td");
       td1.textContent=ele.cabBookingId;
       let td2= document.createElement("td");
       td2.textContent= ele.fromLocation;
       let td3= document.createElement("td");
       td3.textContent=ele.toLocation;
       let td4= document.createElement("td");
       td4.textContent= ele.fromDateTime;
       let td5= document.createElement("td");
       td5.textContent=ele.status;
       if(td5.textContent=="Booked")
           {
             td5.style.color="Green";
           }
            else 
             {
                td5.style.color="Red";
             }
       let td6= document.createElement("td");
       td6.textContent=ele.distanceInKm;
       let td7= document.createElement("td");
       td7.textContent= ele.bill;

       row.append(td1,td2,td3,td4,td5,td6,td7);
       bookingBody.append(row);

   });
};

let transactionAppend= (data)=>{
    data.forEach(ele => {
        let row= document.createElement("tr");
        let td1= document.createElement("td");
        td1.textContent=ele.transactionId;
        let td2= document.createElement("td");
        td2.textContent= ele.transactionDate;
        let td3= document.createElement("td");
        td3.textContent=ele.amount;
        let td4= document.createElement("td");
        td4.textContent= ele.type;
       
        if(td4.textContent=="Credit")
            {
              td4.style.color="Green";
            }
             else 
              {
                 td4.style.color="Red";
              }
        let td5= document.createElement("td");
        td5.textContent=ele.CurrentBalance;
      
 
        row.append(td1,td2,td3,td4,td5);
        transactionBody.append(row);
 
    });

}


let showAllBookings= async ()=>{

    try {
        let res =  await fetch(`http://localhost:8888/${loggedUser.email}`,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            "Authorization":`Bearer ${token}`,
        }
    });
    
    if(res.ok)
     {
        let user = await res.json();
        let data= user.cabBookings;
        bookingAppend(data);
     }else
      {
         let error= await res.json();
         alert(error.HttpStatus);
      }

    } catch (error) {
        alert(error);
    }
    
};

let showAllTransaction= async ()=>{
    try {
        let res =  await fetch(`http://localhost:8888/getWallet/${loggedUser.wallet.walletId}`,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            "Authorization":`Bearer ${token}`,
        }
    });
    
    if(res.ok)
     {
        let user = await res.json();
        let data= user.cabBookings;
        transactionAppend(data);
     }else
      {
         let error= await res.json();
         alert(error.HttpStatus);
      }

    } catch (error) {
        alert(error);
    }
    
};







