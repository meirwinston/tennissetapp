function initGeolocationInput(){
	//geolocation--
	$("#geolocationInput").geocomplete({
//      map: ".mapCanvas"
    }).bind("geocode:result", function(event, result){
  	  document.getElementById("latitude").value = result.geometry.location.lat();
  	  document.getElementById("longitude").value = result.geometry.location.lng(); 
  	  
  	  document.getElementById("address_types").value = result.types;
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
      var img = document.getElementById("locationStaticMap");
      img.src = "http://maps.googleapis.com/maps/api/staticmap?center=" + result.geometry.location.lat() + "," + result.geometry.location.lng() + "&zoom=13&size=400x200&&markers=color:red%7Ccolor:red%7Clabel:C%7C" + 
      result.geometry.location.lat() + "," + result.geometry.location.lng() + "&sensor=false";
      img.style.visibility = "";
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

function addOutdoorCourt(){
	var court = $("#outdoorTableLayout > tbody > tr");
	var table = document.getElementById("outdoorTableLayout");
	var tr = table.insertRow(table.rows.length);
	tr.innerHTML = $(court[0]).html();
}

function addIndoorCourt(){
	var court = $("#indoorTableLayout > tbody > tr");
	var table = document.getElementById("indoorTableLayout");
	var tr = table.insertRow(table.rows.length);
	tr.innerHTML = $(court[0]).html();
}

function submitForm(){
//	console.log("submitForm");
	var form = document.getElementById("createForm");
	var o = $(form).serialize();
//	console.log(o);
//	return;
	services.createTennisCenter(o,function(response){
		console.log("createTennisCenter:response:");
		console.log(response);
		
		$(form).find("label.error").html("");
		
		var errorLabelMap = {
			courtName: "courtNameErr",
			latitude: "addressErr",
			numberOfIndoorCourts: "indoorSurfaceErr",
			indoorSurface: "indoorSurfaceErr",
			numberOfOutdoorCourts: "outdoorSurfaceErr",
			outdoorSurface: "outdoorSurfaceErr",
			phoneNumber: "phoneNumberErr",
			websiteErr: "websiteErr"
		};
		if(response.errors){
//	    	console.log("HERE ARE ERRORS");
//	    	console.log(response.errors);
	    	for(var k in response.errors){
	    		var input = $("#" + errorLabelMap[k]);
	    		if(input){
	    			input.text(response.errors[k]).css("display","");
	    		}
	    	}
	    }
		else if(response.exception){
			$( "<div>",{text: response.exception} ).dialog({
				modal: true,
				buttons: {
					Ok: function() {
						$( this ).dialog( "close" );
					}
				}
			});
		}
	    else{
//	    	window.location.href = redirect;
	    	
	    	$( "<div>",{text: "Tennis center created successfuly"} ).dialog({
				modal: true,
				buttons: {
					Ok: function() {
						$( this ).dialog( "close" );
					}
				}
			});
	    }
	});
}

$(document).ready(function(){
	try{
		initGeolocationInput();
		$("input[type='hidden']").val(null);
	}
	catch(e){
		console.log(e);
	}
});