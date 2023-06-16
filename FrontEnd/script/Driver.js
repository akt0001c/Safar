let did = document.getElementsByClassName("dId");
let dName = document.getElementsByClassName("dName");
let dEmail = document.getElementsByClassName("dEmail");
let dAddress = document.getElementsByClassName("dAddress");
let dMob = document.getElementsByClassName("dMob");
let dLic = document.getElementsByClassName("dLic");
let dRate = document.getElementsByClassName("dRate");

let tbody = document.getElementById("tbody");


for(let i = 0; i < 5; i++){
    display();
}


function display(){
    let tr = document.createElement("tr");
    let td1 = document.createElement("td");
    td1.innerHTML = '1';
    let td2 = document.createElement("td");
    td2.innerHTML = 'Aman'
    let td3 = document.createElement("td");
    td3.innerHTML = 'aman@gmail.com';
    let td4 = document.createElement("td");
    td4.innerHTML = 'Kanpur';
    let td5 = document.createElement("td");
    td5.innerText = '1234567890';
    let td6 = document.createElement("td");
    td6.innerHTML = 'aman1234kl';
    let td7 = document.createElement("td");
    td7.innerHTML = 4.5;

    tr.append(td1,td2,td3,td4,td5,td6,td7);
    tbody.append(tr);

   
}