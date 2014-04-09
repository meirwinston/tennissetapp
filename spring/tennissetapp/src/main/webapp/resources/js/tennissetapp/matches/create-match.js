function populateCourts(lat,lng){
	$.ajax({
		url: rootDomain + "service/courts/findnearby",
		dataType: "json",
		method: "get",
		data: {
			latitude: lat,
			longitude: lng,
			distance: 25
		},
		error: function() {
			console.log("error");
			console.log(arguments);
		},
		success: function( response ) {
//			console.log("Success");
//			console.log(response);
			if(response.errors){
		    	console.log("HERE ARE ERRORS");
		    	console.log(response.errors);
		    }
		    else{
		    	var select = $("#courtsSelect");
		    	var courts = response.list;
		    	for(var i = 0 ; i < courts.length ; i++){
		    		select.append($("<option>",{value: courts[i].tennisCenterId, text: courts[i].name}));
		    	}
		    }
			
		}
	});
}
function initGeolocationInput(){
	//geolocation--
	$("#geolocationInput").geocomplete({})
	.bind("geocode:result", function(event, result){
  	  populateCourts(result.geometry.location.lat(), result.geometry.location.lng());
    })
    .bind("geocode:error", function(event, status){
  	  console.log("ERROR: ");
  	  console.log(status);
    })
    .bind("geocode:multiple", function(event, results){
      console.log("Multiple: ");
      console.log(result);
    });
}

function submitForm(){
	console.log("submitForm");
	var form = document.getElementById("createForm");
	
	var data = $(form).serialize();
	data += "&startTime=17:00&endTime=19:00";
	
	console.log(data);
//	return;
	$.ajax({
		url: form.action,
		dataType: "json",
		method: form.method,
		data: data,
		error: function() {
			console.log("error");
			console.log(arguments);
		},
		success: function( response ) {
//			console.log("Success");
//			console.log(response);
			$(form).find("label.error").html("");
			if(response.errors){
//		    	console.log("HERE ARE ERRORS");
//		    	console.log(response.errors);
		    	for(var k in response.errors){
		    		$("#" + k + "Err").text(response.errors[k]).css("visibility","visible");
		    	}
		    }
		    else{
		    	//window.location.href = redirect;
		    }
			
		}
	});
}
$(function() {
	initGeolocationInput();
	var datepicker = $( "#datepicker" ).datepicker();
	datepicker.datepicker( "option", "dateFormat", "yy-mm-dd" );
});