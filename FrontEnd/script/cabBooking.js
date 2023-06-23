

getallusers();
function getallusers(){
    // const usertablebody=document.getElementById("tbody");
    const token=localStorage.getItem('jwtToken');
    console.log(token);

    fetch("http://localhost:8888/ADMIN/cabBooking/history",{
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
                <td>${user.cabBookingId}</td>
                <td>${user.userId}</td>
                <td>${user.driver.driverId}</td>
                <td>${user.fromDateTime}</td>
                <td>${user.toDateTime}</td>
                <td>${user.fromLocation}</td>
                <td>${user.toLocation}</td>
                <td>${user.distanceInKm}</td>
                <td>${user.bill}</td>
                <td>${user.status}</td>
               
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