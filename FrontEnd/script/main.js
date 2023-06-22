// let input1 = document.getElementById('from');
// let input2 = document.getElementById('to');


// let autocomplete1 = new google.maps.places.Autocomplete(input1);
// let autocomplete1 = new google.maps.places.Autocomplete(input1);

// console.log(autocomplete1);

// let autocomplete2 = new google.maps.places.Autocomplete(input2);
// let autocomplete2 = new google.maps.places.Autocomplete(input2);


// let myLetLng = {
//     lat:38.346,
//     lng:-0.4907
// }

// let mapOptions = {
//     center: myLetLng,
//     zoom: 7,
//     mapTypeId: google.maps.MapTypeId.ROADMAP
// };


// let map = new google.maps.Map(document.getElementById('googleMap'), mapOptions)

// var directionsService = new google.maps.DirectionsService();

// var directionsDisplay = new google.maps.DirectionsRenders();

// directionsDisplay.setMap(map);



// function calcRoute(){
//     let request = {
//         origin:document.getElementById('from').value,
//         destination: document.getElementById('to').value,
//         travelMode: google.maps.travelMode.DRIVING,
//         unitSystem: google.maps.UnitSystem.IMPERIAL
//     }

//     directionsService.route(request, function(result, status){
//         if(status == google.maps.DirectionsStatus.OK){
//             const output = document.querySelector('#output');
//             output.innerHTML = 
//             "<div class='alert-info'>From: " + 
//             document.getElementById('from').value +
//             ".<br />To: " +
//             document.getElementById('to').value +
//             ".<br /> Driving distance <i class='fas fa-road'></i> : " +
//             result.routes[0].legs[0].distance.text +
//             ".<br />Duration <i class='fas fa-hourglass-start'></i> : " +
//             result.routes[0].legs[0].duration.text +
//             ".</div>";

//             directionsDisplay.setDirections(result);
//         }
        // else{

        //     directionsDisplay.setDirections({routes: []});
        //     map.setCenter(myLetLng);

        //     output.innerHTML = "<div class='alert-danger'><i class='fas fa-exclamation-triangle'></i> Could not retrieve driving distance.</div>"
        // }
//     })
// }


var input1 = document.getElementById("from");
var autocomplete1 = new google.maps.places.Autocomplete(input1);
 
var input2 = document.getElementById("to");
var autocomplete2 = new google.maps.places.Autocomplete(input2);

//javascript.js
//set map options
var myLatLng = { lat: 38.346, lng: -0.4907 };
var mapOptions = {
  center: myLatLng,
  zoom: 7,
  mapTypeId: google.maps.MapTypeId.ROADMAP,
};

//create map
var map = new google.maps.Map(document.getElementById("googleMap"), mapOptions);

//create a DirectionsService object to use the route method and get a result for our request
var directionsService = new google.maps.DirectionsService();

//create a DirectionsRenderer object which we will use to display the route
var directionsDisplay = new google.maps.DirectionsRenderer();

//bind the DirectionsRenderer to the map
directionsDisplay.setMap(map);

//define calcRoute function
function calcRoute() {
  //create request
  var request = {
    origin: document.getElementById("from").value,
    destination: document.getElementById("to").value,
    travelMode: google.maps.TravelMode.DRIVING, //WALKING, BYCYCLING, TRANSIT
    unitSystem: google.maps.UnitSystem.IMPERIAL,
  };

  //pass the request to the route method
  directionsService.route(request, function (result, status) {
    if (status == google.maps.DirectionsStatus.OK) {
      //Get distance and time
      const output = document.querySelector("#output");
      output.innerHTML =
        "<div class='alert-info'>From: " +
        document.getElementById("from").value +
        ".<br />To: " +
        document.getElementById("to").value +
        ".<br /> Driving distance <i class='fas fa-road'></i> : " +
        result.routes[0].legs[0].distance.text +
        ".<br />Duration <i class='fas fa-hourglass-start'></i> : " +
        result.routes[0].legs[0].duration.text +
        ".</div>";

      //display route
      directionsDisplay.setDirections(result);
    } else {
      //delete route from map
      directionsDisplay.setDirections({ routes: [] });
      //center map in London
      map.setCenter(myLatLng);

      //show error message
      output.innerHTML =
        "<div class='alert-danger'><i class='fas fa-exclamation-triangle'></i> Could not retrieve driving distance.</div>";
    }
  });
}