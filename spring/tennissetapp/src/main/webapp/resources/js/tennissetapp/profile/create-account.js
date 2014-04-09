

$(function() {
	$("#birthdateInput").birthdaypicker({});
	
//	initGeolocationInput();
	
	//-----------
	var url = "/tennissetapp/service/upload-profile-photo/";
    $('#fileupload').fileupload({
        url: url,
        dataType: 'json',
        fileTypes: /^image\/(gif|jpeg|png)$/,
//        add: function (e, data) {
//        	console.log("add file");
//        },
        fail: function (e, data){
        	console.log("-->fail----");
        	console.log(data);
        	
        },
        done: function (e, data) {
//        	$("#profileImage").src = data.result.imageUrl;
        	document.getElementById("profileImage").src = data.result.imageUrl; 
        	if(data.result.imageId){
        		document.getElementById("profileFileItemIdInput").value = data.result.imageId ;
        	}
        },
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            console.log("---> file upload progress " + progress);
        }
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
    
});

function initGeolocationInput(){
	//geolocation--
	$("#geolocationInput").geocomplete({
//      map: ".mapCanvas"
    }).bind("geocode:result", function(event, result){
  	  document.getElementById("latitude").value = result.geometry.location.lat();
  	  document.getElementById("longitude").value = result.geometry.location.lng(); 
  	  
  	  document.getElementById("addressTypesInput").value = result.types;
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
//        console.log(result);
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
//	console.log("--->validateForm");
//	console.log($('form#createAccountForm').serializeObject());
//	return;
//	console.log("--->>BEFORE SUBMIT: " + $('form#createAccountForm').serialize());
	var e = document.getElementById("profileTypeInput");
	var type = e.options[e.selectedIndex].value;
	var data = $('form#createAccountForm').serializeObject();
	
	if(type == "TENNIS_PLAYER"){
		redirect = rootDomain + "profile/player/complete?userAccountId=" + data.userAccountId;
	}
	else if(type == "TENNIS_TEACHER"){
		redirect = rootDomain + "profile/teacher/complete?userAccountId=" + data.userAccountId;
	}
	$.ajax({  
		  type: "POST",  
		  url: rootDomain + "service/profile/create",  
		  data: data,//$('form#createAccountForm').serialize(),  
		  success: function(response) {  
		    console.log("HERE IS CALLBACK");
		    console.log(response);
		    if(response.errors){
//		    	console.log("HERE ARE ERRORS");
//		    	console.log(response.errors);
		    	for(var k in response.errors){
		    		$("#" + k + "Err").text(response.errors[k]).css("visibility","visible");
		    	}
//		    	$( "#dialog-message" ).dialog({
//					modal: true,
//					buttons: {
//						Ok: function() {
//							$( this ).dialog( "close" );
//						}
//					}
//				});
		    	
		    }
		    else{
		    	window.location.href = redirect;
		    }
		  } ,
		  error:function(){
			  console.log("ERROR");
			    console.log(arguments);
		  }
			  
		});  
	
//	$('form#createAccountForm').submit(function() {
//		console.log("HERE ARE THE RESULTS");
//		console.log(arguments);
//        //$('#result').text(JSON.stringify($('form').serializeObject()));
////        return false;
//    });
}
