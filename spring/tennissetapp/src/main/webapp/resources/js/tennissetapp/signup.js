
$(function() {
//	console.log("signup.js file loaded");
});

function twitterSignup(){
	$("form#signupForm").attr("action",rootDomain + "/signin/twitter");
	$("form#signupForm").submit();
}

function facebookSignup(){
	var form = $("#signupForm");
	form.attr("action",rootDomain + "signin/facebook");
	var input = $("<input type='hidden' name='scope' />").attr("value","email,user_birthday,user_location,publish_stream,user_photos,offline_access,user_likes,read_stream,status_update,user_about_me,read_friendlists,friends_about_me,friends_birthday,friends_photos").prependTo(form);
	form.submit();
}

function signup(){
	var form = $("#signupForm");
	services.signup(form.serializeObject(), function(response){
		if(response.errors){
	    	var d = $("#formHeaderCell");
	    	d.html("");
	    	for(var k in response.errors){
	    		d.append($("<span class='error'/>").text(response.errors[k]));
	    		d.append($("<br />"));
	    	}
	    }
	    else if(response.exception){//here we show dialog or error page
	    	console.log("exception");
	    	console.log(response);
	    }
	    else{
	    	window.location.href = rootDomain + response.redirect;
	    }
	});
	
//	$.ajax({  
//		  type: "post",  
//		  url: rootDomain + "service/signup",  
//		  data: form.serialize(),  
//		  dataType: "json",
//		  success: function(response) {  
//			  console.log("response");
//			  console.log(response);
//		    if(response.errors){
//		    	var d = $("#formHeaderCell");
//		    	d.html("");
//		    	for(var k in response.errors){
//		    		d.append($("<span class='error'/>").text(response.errors[k]));
//		    		d.append($("<br />"));
//		    	}
//		    }
//		    else if(response.exception){//here we show dialog or error page
////		    	if(response.exception == 'com.tennissetapp.exception.ProfileNotCreatedException'){
////		    		window.location.href = rootDomain + "profile/complete";
////		    	}
//		    }
//		    else{
//		    	window.location.href = rootDomain + response.redirect;
//		    }
//		  } ,
//		  error:function(){
//			  console.log("ERROR");
//			    console.log(arguments);
//		  }
//	});  
}
