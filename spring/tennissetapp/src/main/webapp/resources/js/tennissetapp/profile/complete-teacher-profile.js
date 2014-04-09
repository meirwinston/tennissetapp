function initGeolocationInput(){
	//geolocation--
	$("#geolocationInput").geocomplete({
//      map: ".mapCanvas"
    }).bind("geocode:result", function(event, result){
  	  document.getElementById("latitude").value = result.geometry.location.lat();
  	  document.getElementById("longitude").value = result.geometry.location.lng(); 
  	  
  	  document.getElementById("addressTypesInput").value = result.types;
  	  $("#addressErr").text("");
  	  for( var i = 0 ; i <  result.address_components.length ; i++){
  		  var c = result.address_components[i];
  		  var input = document.getElementById(c.types[0]);
  		  if(input){
  			  input.value = c.long_name;
  			  
  			  var inputShort = document.getElementById(c.types[0] + "_short");
  			  if(inputShort){
  				  inputShort.value = c.short_name;
  			  }
  		  }
  	  }
        console.log(result);
        document.getElementById("locationStaticMap").src = "http://maps.googleapis.com/maps/api/staticmap?center=" + result.geometry.location.lat() + "," + result.geometry.location.lng() + "&zoom=13&size=400x200&maptype=roadmap&sensor=false";
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
	//console.log("this is the form");
	//console.log($('form#personalDetailsForm').serializeObject());
//	console.log("--->>BEFORE SUBMIT: " + $('form#personalDetailsForm').serialize());
//	return;
	$.ajax({  
		  type: "POST",  
		  url: rootDomain + "service/profile/teacher/complete",
		  data: $('form#personalDetailsForm').serialize(),
		  dataType: "json",
		  success: function(response) {  
		    console.log("HERE IS CALLBACK");
		    console.log(response);
		    if(response.errors){
//		    	console.log("HERE ARE ERRORS");
//		    	console.log(response.errors);
		    	if(response.errors["streetNumber"]){
		    		$("#addressErr").text(response.errors["streetNumber"]);
		    	}
		    	else if(response.errors["route"]){
		    		$("#addressErr").text(response.errors["route"]);
		    	}
		    	else if(response.errors["locality"]){
		    		$("#addressErr").text(response.errors["locality"]);
		    	}
		    	else if(response.errors["administrativeAreaLevel2"]){
		    		$("#addressErr").text(response.errors["administrativeAreaLevel2"]);
		    	}
		    	else if(response.errors["administrativeAreaLevel1"]){
		    		$("#addressErr").text(response.errors["administrativeAreaLevel1"]);
		    	}
		    	else if(response.errors["country"]){
		    		$("#addressErr").text(response.errors["country"]);
		    	}
		    	else{
		    		$("#addressErr").text("");
		    	}
		    	
		    	if(response.errors["yearsOfExperience"]){
		    		$("#yearsOfExperienceErr").text(response.errors["yearsOfExperience"]);
		    	}
		    	else{
		    		$("#yearsOfExperienceErr").text("");
		    	}
		    	
		    	if(response.errors["clinicRates"]){
		    		$("#clinicRatesErr").text(response.errors["clinicRates"]);
		    	}
		    	else{
		    		$("#clinicRatesErr").text("");
		    	}
		    	
		    	if(response.errors["club"]){
		    		$("#clubErr").text(response.errors["club"]);
		    	}
		    	else{
		    		$("#clubErr").text("");
		    	}
		    	
		    	if(response.errors["certifyingOrganization"]){
		    		$("#certifyingOrganizationErr").text(response.errors["certifyingOrganization"]);
		    	}
		    	else{
		    		$("#certifyingOrganizationErr").text("");
		    	}
		    	
		    	if(response.errors["certifyingOrganization"]){
		    		$("#certifyingOrganizationErr").text(response.errors["certifyingOrganization"]);
		    	}
		    	else{
		    		$("#certifyingOrganizationErr").text("");
		    	}
		    	
		    	if(response.errors["specialityAdults"]){
		    		$("#specialityErr").text(response.errors["specialityAdults"]);
		    	}
		    	else{
		    		$("#specialityErr").text("");
		    	}
		    	
		    	if(response.errors["weekdayAvailabilityAfternoonCheck"]){
		    		$("#availabilityErr").text(response.errors["weekdayAvailabilityAfternoonCheck"]);
		    	}
		    	else{
		    		$("#availabilityErr").text("");
		    	}
		    }
		    else{
		    	window.location.href = rootDomain + "profile/completed";
		    }
		  } ,
		  error:function(){
			  console.log("ERROR");
			    console.log(arguments);
		  }
	});  
}


$(document).ready(function(){
	initGeolocationInput();
	$('input').val(null);
});

