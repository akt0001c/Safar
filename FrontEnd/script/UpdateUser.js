let url=new URLSearchParams(window.location.search);
let email=url.get('email');
let username=url.get('username');
let phone=url.get('phone');
let address=url.get('address');


document.getElementById('email').value=email;
document.getElementById('username').value=username;
document.getElementById('phone').value=phone;
document.getElementById('address').value=address;

document.getElementById("submit").addEventListener("click",function(e){
    e.preventDefault();
    let mail=document.getElementById("email").value;
    updateUser(mail);
});

function updateUser(mail){
    console.log(mail)
    const token="eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBbWFuIiwic3ViIjoiSldUIFRva2VuIiwidXNlcm5hbWUiOiJkaGFudXNoQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjoiUk9MRV9BRE1JTiIsImlhdCI6MTY4NzEwNzA1OSwiZXhwIjoxNjg3MTY3MDU5fQ.3rCI-q82wFE1zyUg4_GIsLl0UAkTWQsVsIwPpWvXWgw";
    fetch("http://localhost:8888/users/"+mail,{
        method:"PATCH",
        headers: {
            'Authorization': `Bearer ${token}`
          },
          body:JSON.stringify({
            "username":username,
            "phone":phone,
            "address": address
          })
    }).then(response=>{
        if(response.status==202){
            console.log("hii")
            response.json().then(data=>{
                alert("user updated successfully :"+email)
            })
        }else{
            console.log("testing")
            response.json().then(data=> alert("Something went wrong !"));
        }
    })
}