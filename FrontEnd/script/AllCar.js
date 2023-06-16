
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
    td2.innerHTML = 'SUV'
    let td3 = document.createElement("td");
    td3.innerHTML = '100';
    let td4 = document.createElement("td");
    td4.innerHTML = '1';
    
    tr.append(td1,td2,td3,td4);
    tbody.append(tr);

   
}