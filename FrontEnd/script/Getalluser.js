function getallusers(){
    const usertablebody=document.getElementById("user-table-body");
    const token=localStorage.getItem('jwtToken');

    fetch("http://localhost:8888/users",{
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
                <td>${user.userId}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td>${user.address}</td>
                <td>${user.role}</td>
                <td><button style="background-color: green;" onclick="updateuser('${user.email}','${user.username}','${user.phone}','${user.address}')">Update</button></td>
                <td><button style="background-color: red;" onclick="deleteuser('${user.email}')">Delete</button></td>
                `;
                usertablebody.appendChild(row);
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
    const token=localStorage.getItem('jwtToken');
        
    // console.log("deleted started");
    let choice=confirm("Are you sure ? ");
    if(choice){
        fetch("http://localhost:8888/users/"+mail,{
            method:"DELETE",
            headers: {
                'Authorization': `Bearer ${token}`
              }
        }
        ).then(response=>{
            if(response.ok){
                response.json().then(data=>{
                    alert("user deleted successfully !");
                    location.reload();
                });
            }
            else{
                response.json().then(data=>alert("Sometging went wrong !"))
            }
        })
    }

}

function updateuser(email,username,phone,address){
    const url=`Updateuser.html?email=${email}&username=${username}&phone=${phone}&address=${address}`;
    location.href=url;
}