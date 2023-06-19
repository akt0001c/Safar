let url = new URLSearchParams(window.location.search);
let email = url.get("email");
let username = url.get("username");
let phone = url.get("phone");
let address = url.get("address");

document.getElementById("email").value = email;


document.getElementById("submit").addEventListener("click", function (e) {
  e.preventDefault();
  let mail = document.getElementById("email").value;
  let username = document.getElementById("username").value;
  let phone = document.getElementById("phone").value ;
  let address = document.getElementById("address").value;
  console.log("++++++++++++++++++++++++++++++++++");
  console.log(mail);
  console.log(username);
  console.log(phone);
  console.log(address);
  console.log("++++++++++++++++++++++++++++++++++");
  updateUser(mail,username,phone,address);
});

function updateUser(mail,username,phone,address) {
  console.log(mail);
  console.log(username);
  console.log(phone);
  console.log(address);
  const token = localStorage.getItem("jwtToken");
  // fetch("http://localhost:8888/ADMIN/driver/"+mail,{
  //     method:"PATCH",
  //     headers: {
  //         'Authorization': `Bearer ${token}`
  //       },
  //       body:JSON.stringify({
  //         "driverName" : username,
  //         "email" : email,
  //         "password" : "1234",
  //         "mobileNo" : phone,
  //         "address" : address,
  //         "newLocation": "delhi",
  //         "licenceNo" : "12343987987",
  //         "rating" : "4.5",
  //         "status" : "Available",
  //         "car" : {
  //             "carType" : "SUV",
  //             "perKmRate" : 10.0
  //         }
  //       })
  // }).then(response=>{
  //     if(response.status==202){
  //         console.log("hii")
  //         response.json().then(data=>{
  //             alert("user updated successfully :"+email)
  //         })
  //     }else{
  //         console.log("testing")
  //         response.json().then(data=> alert("Something went wrong !"));
  //     }
  // })

  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  myHeaders.append(
    "Authorization",
    "Bearer "+token
  );

  var raw = JSON.stringify({
    driverName: username,
    mobileNo: phone,
    address: address
  });

  var requestOptions = {
    method: "PATCH",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
  };

  fetch("http://localhost:8888/ADMIN/driver/"+mail, requestOptions)
    .then((response) => response.text())
    .then((result) => console.log(result),
      Swal.fire(
        'Good job!',
        'Successfully Updated Driver',
        'success'
      ),
      // alert("user updated successfully"),
      setTimeout(()=>{
        window.location.href="./Driver.html"
      },1000) 
        
    )
    .catch((error) => console.log("error", error));
}
