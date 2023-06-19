// let did = document.getElementsByClassName("dId");
// let dName = document.getElementsByClassName("dName");
// let dEmail = document.getElementsByClassName("dEmail");
// let dAddress = document.getElementsByClassName("dAddress");
// let dMob = document.getElementsByClassName("dMob");
// let dLic = document.getElementsByClassName("dLic");
// let dRate = document.getElementsByClassName("dRate");

// let tbody = document.getElementById("tbody");


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

function getAllDriver(){
    const usertablebody=document.getElementById("driver-table-body");
    const token="eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBbWFuIiwic3ViIjoiSldUIFRva2VuIiwidXNlcm5hbWUiOiJkaGFudXNoQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjoiUk9MRV9BRE1JTiIsImlhdCI6MTY4NzE2MTM0NSwiZXhwIjoxNjg3MjIxMzQ1fQ.lDq3U1ftE0jA1EuQughlJm8g4uf-kZqe6xoIMhVDE80";

    fetch("http://localhost:8888/driver/drivers",{
       method:"GET",
       headers: {
        'Authorization': `Bearer ${token}`
      }
    }).then(response=>{
    if(response.ok){
        response.json().then(data=>{
            console.log(data);
            data.forEach(driver => {
                const row=document.createElement("tr");
                row.innerHTML=`
                <td>${driver.driverId}</td>
                <td>${driver.driverName}</td>
                <td>${driver.email}</td>
                <td>${driver.mobileNo}</td>
                <td>${driver.address}</td>
                <td>${driver.licenceNo}</td>
                <td>${driver.rating}</td>
                 <td><button style="background-color: green;" onclick="updateuser())">Update</button></td>
                <td><button style="background-color: red;" onclick="deletedriver(${driver.driverId})">Delete</button></td>
                `;
                usertablebody.appendChild(row);
            })
        })
    }
    else{
        console.log("testing");
        response.json().then(data=>alert("Something went wrong !"));
    }
    })


}


function deletedriver(driverId){
    const token="eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBbWFuIiwic3ViIjoiSldUIFRva2VuIiwidXNlcm5hbWUiOiJkaGFudXNoQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjoiUk9MRV9BRE1JTiIsImlhdCI6MTY4NzE2MTM0NSwiZXhwIjoxNjg3MjIxMzQ1fQ.lDq3U1ftE0jA1EuQughlJm8g4uf-kZqe6xoIMhVDE80";
        
    // console.log("deleted started");
    let choice=confirm("Are you sure ? ");
    if(choice){
        fetch("http://localhost:8888/driver/"+driverId,{
            method:"DELETE",
            headers: {
                'Authorization': `Bearer ${token}`
              }
        }
        ).then(response=>{
            if(response.ok){
                // response.json().then(data=>{
                    alert("user deleted successfully !");
                    location.reload();
                // });
            }
            else{
                alert("Sometging went wrong !")
            }
        })
    }

}