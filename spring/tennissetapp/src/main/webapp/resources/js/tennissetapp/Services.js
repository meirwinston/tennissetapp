function Services(){
	this.searchMates = function(args,callback){
		$.ajax({
			url: rootDomain + "service/mates/search",
			dataType: "json",
			method: "get",
			data: args,
			error: function(e) {
				if(callback){
					callback(e);
				}
			},
			success: function( response ) {
				if(callback){
					callback(response);
				}
			}
		});

	};
	
	this.scrollMatesNearbyUser = function(args,callback){
		$.ajax({
			url: rootDomain + "service/mates/findnearbyme",
			dataType: "json",
			method: "get",
			data: args,
			error: function() {
				if(callback){
					callback(response);
				}
			},
			success: function( response ) {
				if(callback){
					callback(response);
				}
			}
		});
	};

	this.scrollMatchesNearbyUser = function(args,callback){
		$.ajax({
			url: rootDomain + "service/matches/findnearbyme",
			dataType: "json",
			method: "get",
			data: args,
			error: function() {
				console.log("error");
				console.log(arguments);
			},
			success: function( response ) {
				if(callback){
					callback(response);
				}
			}
		});
	};
	
	this.searchMatches = function(args,callback){
		$.ajax({
			url: rootDomain + "service/matches/search",
			dataType: "json",
			method: "get",
			data: args,
			error: function() {
				console.log("error");
				console.log(arguments);
			},
			success: function( response ) {
				if(callback){
					callback(response);
				}
			}
		});
	};
	
	this.signup = function(args,callback){
		$.ajax({  
			  type: "post",  
			  url: rootDomain + "service/signup",  
			  data: args,  
			  dataType: "json",
			  success: function(response) {  
				  if(callback){
					  callback(response);
				  }
			  } ,
			  error:function(){
				  console.log("ERROR");
				  console.log(arguments);
			  }
		});
	};
	
	this.completePlayerProfile = function(args,callback){
//		console.log("Services.completePlayerProfile");
//		console.log(args);
//		return;
		$.ajax({  
			  type: "POST",  
			  url: rootDomain + "service/profile/player/complete",
			  data: args,
			  dataType: "json",
			  success: function(response) {
				  if(callback){
					  callback(response);
				  }
			  } ,
			  error:function(){
				  console.log("ERROR completePlayerProfile");
				  console.log(arguments);
			  }
		}); 
	};
	
	this.createTennisCenter = function(params,callback){
		$.ajax({
			type: "POST",
			url: rootDomain + "service/courts/create",
			dataType: "json",
			data: params,
			error: function(err) {
				if(callback){
					callback(err);
				}
			},
			success: function( response ) {
				if(callback){
					callback(response);
				}
			}
		});
	};
}

var services = new Services(); //global variable