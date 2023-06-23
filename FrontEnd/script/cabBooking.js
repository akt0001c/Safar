

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

