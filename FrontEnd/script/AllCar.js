
let did = document.getElementsByClassName("dId");
let dName = document.getElementsByClassName("dName");
let dEmail = document.getElementsByClassName("dEmail");
let dAddress = document.getElementsByClassName("dAddress");
let dMob = document.getElementsByClassName("dMob");
let dLic = document.getElementsByClassName("dLic");
let dRate = document.getElementsByClassName("dRate");

let tbody = document.getElementById("tbody");



getallusers();
function getallusers(){
    // const usertablebody=document.getElementById("tbody");
    const token="eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBbWFuIiwic3ViIjoiSldUIFRva2VuIiwidXNlcm5hbWUiOiJkaGFudXNoQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjoiUk9MRV9BRE1JTiIsImlhdCI6MTY4NzEwNzA1OSwiZXhwIjoxNjg3MTY3MDU5fQ.3rCI-q82wFE1zyUg4_GIsLl0UAkTWQsVsIwPpWvXWgw";

    fetch("http://localhost:8888/driver/drivers",{
       method:"GET",
       headers: {
        'Authorization': `Bearer ${token}`
      }
    }).then(response=>{
    if(response.ok){
        response.json().then(data=>{
            console.log(data);
            data.forEach(user => {
                const row=document.createElement("tr");
                row.innerHTML=`
                <td>${user.car.carId}</td>
                <td>${user.car.carType}</td>
                <td>${user.car.perKmRate}</td>
                <td>${user.driverId}</td>
                `;
                tbody.appendChild(row);
            })
        })
    }
    else{
        console.log("testing");
        response.json().then(data=>alert("Sometging went wrong !"));
    }
    })


}



