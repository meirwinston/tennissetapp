//var approot = "/tennissetapp";

function submitPost(){
	$.ajax({  
		  type: $('form#postForm').attr("method"),  
		  url: $('form#postForm').attr("action"),  
		  data: $('form#postForm').serialize(),  
		  success: function(response) {  
		    console.log("HERE IS CALLBACK");
		    console.log(response);
		    if(response.errors){
//		    	console.log("HERE ARE ERRORS");
//		    	console.log(response.errors);
		    	for(var k in response.errors){
		    		//$("#" + k + "Err").text(response.errors[k]).css("visibility","visible");
		    		console.log("ERRORS: ");
		    		console.log(response.errors);
		    	}
		    }
		    else{
		    	//success
		    }
		  } ,
		  error:function(){
			  console.log("ERROR");
			    console.log(arguments);
		  }
			  
		});
}

$(function() {
	console.log("home.constructing menubar");
});