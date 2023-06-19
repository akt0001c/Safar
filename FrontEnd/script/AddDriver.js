
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
    const token="eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBbWFuIiwic3ViIjoiSldUIFRva2VuIiwidXNlcm5hbWUiOiJkaGFudXNoQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjoiUk9MRV9BRE1JTiIsImlhdCI6MTY4NzE2MTM0NSwiZXhwIjoxNjg3MjIxMzQ1fQ.lDq3U1ftE0jA1EuQughlJm8g4uf-kZqe6xoIMhVDE80";
   var driver={
        "driverName" : dName.value,
        "email" : dEmail.value,
        "password" : password.value,
        "mobileNo" : dMob.value,
        "address" : dAddress.value,
        "licenceNo" : dLic.value,
        "rating" : dRate.value,
        "newLocation" : "delhi",
        "status" : "Available",
        "car" : {
            "carType" : "SUV",
            "perKmRate" : 10.0
        }
    }
    // console.log(driver);
    fetch("http://localhost:8888/driver/driver",{
        method:"POST",
        headers: {
            'Authorization': `Bearer ${token}`
          },
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
