

let bookCab = document.getElementById("subBtn");



bookCab.addEventListener('click', function (event) {
    let pickUp = document.getElementById('pickUp');
    let to = document.getElementById('to');
    let distance = document.getElementById('distance');
    event.preventDefault();
    console.log('hello');
    var cabData = {
        "fromLocation": "delhi",
        "toLocation": "kanpur",
        "distanceInKm": 12.0

    }
    const token = localStorage.getItem('jwtToken');

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Authorization", "Bearer " + token);

    var raw = JSON.stringify({
        "fromLocation": pickUp.value,
        "toLocation": to.value,
        "distanceInKm": distance.value
    });

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch("http://localhost:8888/cabBooking", requestOptions)
        // .then(response => response.text())
        // .then((result) => console.log(result))
        // .catch((error) => console.log("error", error));
        .then(response => {
            if (response.ok) {
                console.log("hii")
                response.json().then(data => {
                    Swal.fire(
                        'Good job!',
                        'Ride Booked Successfully',
                        'success'
                    )
                })
                setTimeout(()=>{
                    window.location.href="/profile.html"
                  },1000)
            } else {
                response.json().then(data => Swal.fire({
                                icon: 'error',
                                title: 'Oops...',
                                text:  data.message,
                                footer: '<a href="">Why do I have this issue?</a>'
                            }));
            }
        })

    



});



