
let dName = document.getElementById("name");
let dEmail = document.getElementById("email");
let password = document.getElementById("password");
let dAddress = document.getElementById("address");
let dMob = document.getElementById("Mobile");
let dLic = document.getElementById("licence");
let dRate = document.getElementById("rating");

let tbody = document.getElementById("tbody");


let subBtn = document.getElementById("subBtn");

let form = document.getElementById('form');

form.addEventListener('submit',(e)=>{
    e.preventDefault();
    console.log(dName.value);
})