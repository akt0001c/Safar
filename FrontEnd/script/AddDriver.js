let dName = document.getElementById("name");
let dEmail = document.getElementById("email");
let password = document.getElementById("password");
let dAddress = document.getElementById("address");
let dMob = document.getElementById("Mobile");
let dLic = document.getElementById("licence");
let dRate = document.getElementById("rating");
let carType = document.getElementById("carType");
let perKmRate = document.getElementById("perKmRate");
let carNumber = document.getElementById("carNumber");


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
            "carType" : carType.value,
            "carNumber" : carNumber.value,
            "perKmRate" :perKmRate.value
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
                Swal.fire(
                    'Good job!',
                    'Driver Successfully Registered',
                    'success'
                  )
            })
            setTimeout(()=>{
                window.location.href="./Driver.html"
              },1000)
        }else{
            response.json().then(data=> {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Invalid Credentials',
                    footer: '<a href="">Why do I have this issue?</a>'
                  })
            });
        }
    })
})