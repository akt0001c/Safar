document.getElementById("signup-toggle").addEventListener("click", function () {
    document.getElementById("login-form").classList.remove("active");
    document.getElementById("signup-form").classList.add("active");
});

document.getElementById("login-toggle").addEventListener("click", function () {
    document.getElementById("signup-form").classList.remove("active");
    document.getElementById("login-form").classList.add("active");
});

// import Swal from 'sweetalert2'

// CommonJS
// const Swal = require('sweetalert2')

const loginForm = document.getElementById("login-form");

loginForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const usernameInput = document.getElementById("loginemail");
    // const emailInput = document.querySelector('input[name="email"]');
    const passwordInput = document.getElementById("loginPassword");
    const username = usernameInput.value;
    // const email = emailInput.value;
    const password = passwordInput.value;
    if (password && username != "") {
        fetch("http://localhost:8888/signIn", {
            method: 'GET',
            headers: {
                'Authorization': 'Basic ' + btoa(username + ':' + password)
            },
        })
            .then(res => {
                if (res.status == 200 | res.status == 202) {
                    const token = res.headers.get("Authorization");
                    console.log(token)
                    localStorage.setItem("jwtToken", token)


                    Swal.fire(
                        'Good job!',
                        'Successfully Registered',
                        'success'
                    )

                    res.json().then(data => {
                        console.log(data.role, "line 47")
                        localStorage.setItem("username", data.username)
                        localStorage.setItem("userData", JSON.stringify(data))
                        if (data.role == "ROLE_ADMIN") {
                            setTimeout(() => {
                                window.location.href = "../Driver.html"
                            }, 2000)
                        }else{
                            setTimeout(() => {
                                window.location.href = "../FrontEnd/index.html"
                            }, 2000)
                        }
                    })
                    



                } else {
                    // alert("Invalid Credentials")
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Invalid Credentials',
                        footer: '<a href="">Why do I have this issue?</a>'
                    })
                }
            })
            .catch(error => {
                console.error(error);
                alert("Invalid Credentials")
            });
    } else {
        // alert("please fill all details")
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'please fill all details',
            footer: '<a href="">Why do I have this issue?</a>'
        })
    }

});

const signupForm = document.getElementById("signup-form");

signupForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const usernameInput = document.getElementById("username");
    const emailInput = document.getElementById("email");
    const passwordInput = document.getElementById("Password");
    const phoneInput = document.getElementById("phoneNumber");
    const addressInput = document.getElementById("address");


    const username = usernameInput.value;
    const email = emailInput.value;
    const password = passwordInput.value;
    const phone = phoneInput.value;
    const addrss = addressInput.value;
    const data = { username: username, password: password, email: email, phone: phone, address: addrss, role: 'user' };
    console.log(data)
    if (email && password && username != "") {
        fetch("http://localhost:8888/users", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        })
            .then(res => res.json())
            .then(res => {
                console.log(res)
                if (res) {
                    // alert(res.msg)
                    Swal.fire(
                        'Good job!',
                        'Successfully Registered',
                        'success'
                    )


                    setTimeout(() => {
                        window.location.href = "login.html"
                    }, 1000)
                } else {
                    // alert(res.message)

                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'User Already Exists',
                        footer: '<a href="">Why do I have this issue?</a>'
                    })
                }

            })
            .catch(error => {

                console.error(error);
            });
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'please fill all details',
            footer: '<a href="">Why do I have this issue?</a>'
        })
        // alert("please fill all details")
    }

});
