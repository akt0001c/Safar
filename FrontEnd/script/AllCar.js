
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
    const token=localStorage.getItem('jwtToken');

    fetch("http://localhost:8888/ADMIN/drivers",{
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



