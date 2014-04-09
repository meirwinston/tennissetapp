function initGeolocationInput(){
	//geolocation--
	$("#geolocationInput").geocomplete({
//      map: ".mapCanvas"
    }).bind("geocode:result", function(event, result){
  	  document.getElementById("latitude").value = result.geometry.location.lat();
  	  document.getElementById("longitude").value = result.geometry.location.lng(); 
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
  	  initTennisCourtsDropdown();
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
	
//	console.log("TEST GEO_LOCATION ");
//	console.log($("#geolocationInput").geocomplete("find", "NYC"));
//  $("#search").click(function(){
//  $("#geocomplete").geocomplete("find", "NYC");
//});
//
//$("#center").click(function(){
//  var map = $("#geocomplete").geocomplete("map"),
//    center = new google.maps.LatLng(10, 0);
//  
//  map.setCenter(center);
//  map.setZoom(3);
//});
}

//function initTennisCourtsAutoComplete(){
//	$( "#courtsInput" ).autocomplete({
//		source: function( request, response ) {
//			console.log("source");
//			console.log(request);
//			$.ajax({
//				url: rootDomain + "service/courts/scrollnearby",
//				dataType: "json",
//				data: {
//					maxRows: 12,
//					term: request.term
//				},
//				error: function() {
//					console.log("error");
//					console.log(arguments);
//				},
//				success: function( data ) {
//					response(data.items);
//				}
//			});
//		},
//		minLength: 1, //how many characters should trigger ajax
//		select: function( event, ui ) {
//			console.log("select");
//			console.log( ui.item ?
//				"Selected: " + ui.item.label :
//				"Nothing selected, input was " + this.value);
//		},
//		open: function() {
//			console.log("open");
////			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
//		},
//		close: function() {
//			console.log("close");
////			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
//		}
//	});
//}

function onRemoveCourt(c){
	$(c).remove();
}

function addFavouriteCourt(){
	var option = $("#courtsInput :selected");
	if($("#myFavouriteCourts > li[value='" + option.val() + "']").length <= 0){
		var myFavouriteCourts = $("#myFavouriteCourts");
		var child = $("<li>",{html: option.text(), value: option.val(), onclick: "onRemoveCourt(this);"});
		myFavouriteCourts.append(child);
	}
	
}

function initTennisCourtsDropdown(){
	$.ajax({
		url: rootDomain + "service/courts/findnearby",
		dataType: "json",
		
		data: {
			latitude: document.getElementById("latitude").value,
			longitude: document.getElementById("longitude").value,
			distance: 200
		},
		error: function() {
			console.log("error");
			console.log(arguments);
		},
		success: function( data ) {
//			console.log("Success");
//			console.log(data);
			var input = $("#courtsInput");
			for(var i = 0 ; i < data.list.length ; i++){
				input.append($("<option>",{value: data.list[i].tennisCenterId, html: data.list[i].name + " - " + data.list[i].administrativeAreaLevel1}));
			}
		}
	});
}

function getFavouriteCourts(){
	var courtIds = [];
	var arr = $("#myFavouriteCourts").children();
	for(var i = 0 ; i < arr.length ; i++){
		courtIds.push(arr[i].value);
	}
	return courtIds;
}

function submitForm(){
	document.getElementById("favouriteCourtsInput").value = getFavouriteCourts();
	var o = $('form#personalDetailsForm').serializeObject();
	
//	if(o.addressTypes && o.addressTypes.length > 0){
//		  var hasRealValue = false;
//		  for(var i = 0 ; i < o.addressTypes.length ; i++){
//			  if(o.addressTypes[i] != ""){
//				hasRealValue = true;
//				break;
//			  }
//		  }
//		  if(!hasRealValue){
//			delete o.addressTypes;
//		  }
//	  }
//	console.log("submitForm---");
//	console.log(o);
//	return;
	
	services.completePlayerProfile(o, function(response){
//		console.log("completePlayerProfile " );
//		console.log(response);
		
		if(response.errors){
	    	if(response.errors["latitude"]){
	    		$("#addressErr").text(response.errors["latitude"]);
	    	}
	    	else{
	    		$("#addressErr").text("");
	    	}
	    	
	    	if(response.errors["singlesCheck"]){
	    		$("#typeOfPlayErr").text(response.errors["singlesCheck"]);
	    	}
	    	else{
	    		$("#typeOfPlayErr").text("");
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
	});
}

function cleanForm(){
//	$('input').val(null); //don't erase userAccountId
	$('#addressInputs input').val(null);
}

$(document).ready(function(){
//	cleanForm();
	try{
//		initTennisCourtsDropdown(); //only after the user enters address
		initGeolocationInput();
	}
	catch(exp){
		console.log(exp);
	}
	//we are not sure whether to use auto-complete or dropdown
	//initTennisCourtsAutoComplete();
	
	
});
