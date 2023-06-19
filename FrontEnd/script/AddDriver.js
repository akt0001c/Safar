let dName = document.getElementById("name");
let dEmail = document.getElementById("email");
let password = document.getElementById("password");
let dAddress = document.getElementById("address");
let dMob = document.getElementById("Mobile");
let dLic = document.getElementById("licence");
let dRate = document.getElementById("rating");

let tbody = document.getElementById("tbody");


let subBtn = document.getElementById("subBtn");

let form = document.getElementById('form');

form.addEventListener('submit',(e)=>{
    e.preventDefault();
    // alert(dName)
    const token=localStorage.getItem('jwtToken');
   var driver={
        "driverName" : dName.value,
        "email" : dEmail.value,
        "password" : password.value,
        "mobileNo" : dMob.value,
        "address" : dAddress.value,
        "newLocation": "delhi",
        "licenceNo" : dLic.value,
        "rating" : dRate.value,
        "status" : "Available",
        "car" : {
            "carType" : "SUV",
            "perKmRate" : 10.0
        }
        
    }
    // console.log(driver);
    fetch("http://localhost:8888/ADMIN/driver",{
        method:"POST",
        headers: {"Content-Type": "application/json"},
          body:JSON.stringify(driver)
    }).then(response=>{
        if(response.ok){
            console.log("hii")
            response.json().then(data=>{
                alert("driver added successfully :")
            })
        }else{
            response.json().then(data=> alert("Something went wrong !"));
        }
    })
})