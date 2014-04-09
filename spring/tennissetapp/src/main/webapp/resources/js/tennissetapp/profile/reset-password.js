//$.validator.setDefaults({
//	submitHandler: function() { alert("submitted!"); }
//});

$().ready(function() {
	// validate signup form on keyup and submit
//	$("#resetPasswordForm").validate({
//		rules: {
//			password: {
//				required: true,
//				minlength: 5
//			},
//			confirmPassword: {
//				required: true,
//				minlength: 5,
//				equalTo: "#password"
//			},
//		},
//		messages: {
//			password: {
//				required: "Please provide a password",
//				minlength: "Your password must be at least 5 characters long"
//			},
//			confirmPassword: {
//				required: "Please provide a password",
//				minlength: "Your password must be at least 5 characters long",
//				equalTo: "Please enter the same password as above"
//			}
//		}
//	});
});

function submitForm(){
	var form = $("#resetPasswordForm");
//	$("#resetPasswordForm").submit();
//	console.log("submit form");
	
	$.ajax({  
		  type: "post",  
		  url: form.attr("action"),
		  data: form.serialize(),  
		  dataType: "json",
		  success: function(response) {  
//			  console.log("RESPONSE");
//			  console.log(response);
		    if(response.errors){
		    	var d = $("#errorPane");
		    	d.html("");
		    	for(var k in response.errors){
		    		d.append($("<span class='error'/>").text(response.errors[k]));
		    		d.append($("<br />"));
		    	}
		    }
		    else if(response.exception){//here we show dialog or error page
		    	if(response.exception == 'com.tennissetapp.exception.NotAuthorizedException'){
		    		$("#errorPane").text("Illigal request");
		    	}
		    }
		    else{
		    	window.location.href = rootDomain + response.redirect;
		    }
		  },
		  error:function(){
			  console.log("ERROR");
			    console.log(arguments);
		  }
	});  

}