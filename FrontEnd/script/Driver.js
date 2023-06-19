let did = document.getElementsByClassName("dId");
let dName = document.getElementsByClassName("dName");
let dEmail = document.getElementsByClassName("dEmail");
let dAddress = document.getElementsByClassName("dAddress");
let dMob = document.getElementsByClassName("dMob");
let dLic = document.getElementsByClassName("dLic");
let dRate = document.getElementsByClassName("dRate");

let tbody = document.getElementById("tbody");


// for(let i = 0; i < 5; i++){
//     display();
// }


// function display(){
//     let tr = document.createElement("tr");
//     let td1 = document.createElement("td");
//     td1.innerHTML = '1';
//     let td2 = document.createElement("td");
//     td2.innerHTML = 'Aman'
//     let td3 = document.createElement("td");
//     td3.innerHTML = 'aman@gmail.com';
//     let td4 = document.createElement("td");
//     td4.innerHTML = 'Kanpur';
//     let td5 = document.createElement("td");
//     td5.innerText = '1234567890';
//     let td6 = document.createElement("td");
//     td6.innerHTML = 'aman1234kl';
//     let td7 = document.createElement("td");
//     td7.innerHTML = 4.5;

//     tr.append(td1,td2,td3,td4,td5,td6,td7);
//     tbody.append(tr);

   
// }

getallusers();
function getallusers(){
    // const usertablebody=document.getElementById("tbody");
    const token=localStorage.getItem('jwtToken');
    console.log(token);

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
                <td>${user.driverId}</td>
                <td>${user.driverName}</td>
                <td>${user.email}</td>
                <td>${user.address}</td>
                <td>${user.mobileNo}</td>
                <td>${user.licenceNo}</td>
                <td>${user.rating}</td>
                <td><button style="background-color: green;" onclick="updateDriver('${user.email}','${user.username}','${user.phone}','${user.address}')">Update</button></td>
                <td><button style="background-color: red;" onclick="deleteuser('${user.email}')">Delete</button></td>
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



function deleteuser(mail){
    const token = localStorage.getItem('jwtToken');
    
    console.log(token);
        
    // console.log("deleted started");
    let choice=confirm("Are you sure ? ");
    if(choice){
        fetch("http://localhost:8888/ADMIN/driver/"+mail,{
            method:"DELETE",
            headers: {
                'Authorization': `Bearer ${token}`
              }
        }
        ).then(response=>{
            response.json().then(data=>{
                alert("user deleted successfully !");
                getallusers()
                
            });setTimeout(()=>{
                window.location.href="/Driver.html"
            },1000)
            
        }).catch(error => {
            getallusers()
            console.error(error);
        });
    }


}

function updateDriver(email,username,phone,address){
    const url=`updateDriver.html?email=${email}&username=${username}&phone=${phone}&address=${address}`;
    location.href=url;
}