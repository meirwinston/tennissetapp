var pagination;
var isSearch = false;
//var datepicker;
function initPagination(){
	pagination = new Pagination({
		classOfNode: "paginationNode",
		classNextBack: "paginationNextBack",
		classOfSelectedLabel: "paginationSelectedLabel",
		classOfUnselectedLabel: "paginationUnselectedLabel"
	});
	pagination.postCreate();
	pagination.pageSize = 2;
//	console.log(document.getElementById("paginationCell"));
//	console.log(pagination.domNode);
	
	document.getElementById("paginationCell").appendChild(pagination.domNode);
	pagination.render();
	pagination.onBrowse = scrollMates;
}

function initGeolocationInput(){
	//geolocation--
	$("#geolocationInput").geocomplete({
//      map: ".mapCanvas"
    }).bind("geocode:result", function(event, result){
  	  document.getElementById("latitudeInput").value = result.geometry.location.lat();
  	  document.getElementById("longitudeInput").value = result.geometry.location.lng(); 
  	  
  	  document.getElementById("addressTypesInput").value = result.types;
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

//function initDatePicker(){
//	datepicker = $( "#datepicker" ).datepicker();
//	datepicker.datepicker( "option", "dateFormat", "yy-mm-dd" );
//	
//	datepicker = datepicker.data().datepicker;
//}

function populateMates(list,count){
	if(list.length <= 0){
		var title = $("<div>",{
			text: "Find a Tennis Match",
			'class': "theme1-font-title"
		});
		var text = $("<div>",{
			'class': "theme1-font-normal",
			html: "Sorry, we couldn't find matches based one your preferences.<br/>Help us build the community by inviting your friends to join Tennis SetApp, or continue your search on the left."
		}).css("padding-top","2em");
		
		$("#centerPane").html("").append(title).append(text);
		pagination.totalResults = count;
		pagination.refresh();
	}
	else{
		var ul = $("<ul />",{'class': "theme1-font-smaller"})
		.css("list-style-type","none")
		.css("width","500px")
		.css("padding","0");
		var s = ""; //temporary variable
		for(var i = 0 ; i < list.length ; i++){
			var r = $("<ul>")
			.css("list-style-type","none")
			.css("display","table")
			.css("line-height","2.5em")
			.css("padding","0 2em 0"); 
			
			r.append($("<li>",{html: 
				"<span class='theme1-font-title'>" + list[i].firstName + " " + list[i].lastName + "</span>&nbsp;<img style='border: 0; margin-left: 1.5em; margin-right: 1.2em;' src='/tennissetapp/resources/images/level_18x14.png' />" + list[i].levelOfPlay}));
//					.css("list-style-image","url('" + rootDomain + "resources/images/user_15x15.png')"));
			if(list[i].address.length > 0){
				r.append($("<li>",{html: list[i].address})
					.css("list-style-image","url('" + rootDomain + "resources/images/place_14x18.png')"));
			}
						
			s = "";
			if(list[i].playPoints){
				s += "Points, ";
			}
			if(list[i].playSingles){
				s += "Singles, ";
			}
			if(list[i].playDoubles){
				s += "Doubles, ";
			}
			if(list[i].playHittingAround){
				s += "Hitting Ground, ";
			}
			if(list[i].playFullMatch){
				s += "Full Match, ";
			}
			if(s.length > 0){
				s = s.substr(0,s.length-2); //truncate the last comma and space
				r.append($("<li>",{text: s})
					.css("list-style-image","url('" + rootDomain + "resources/images/type_of_game_12x20.png')"));
			}
			if(list[i].start){
				r.append($("<li>",{text: $.fn.formatDatetime(new Date(list[i].start))})
					.css("list-style-image","url('" + rootDomain + "resources/images/best_availability_14x14.png')"));
			}
			var t = document.createElement("table"),tr,td;
			tr = t.insertRow(t.rows.length);
			
			td = tr.insertCell(tr.cells.length);
			$(td).css("width","100px").css("height","160px").css("vertical-align","top");
			
			if(list[i].profilePhoto){
				$(td).append($("<img>",{src: list[i].profilePhoto, style: "borde: 1px solid red; width: 100px; height: 100px"}));	
			}
			else{
				$(td).append($("<img>",{src: rootDomain + "/resources/images/nophoto_user_thumb_profile_218x218.png", style: "borde: 1px solid red; width: 100px; height: 100px"}));
			}
			
			td = tr.insertCell(tr.cells.length);
			$(td).css("vertical-align","top").append(r);
			
			var li = $("<li>");
			li.append(t);
			ul.append(li);
		}
		$("#centerPane").html("").append(ul);
	}
}

function scrollMates(offset,pageSize){
	
	if(!isSearch){
		var data = {
				firstResult: offset,
				maxResults: pageSize
			};
		services.scrollMatesNearbyUser(data,function(response){
			populateMates(response.list,response.count);
			
			pagination.totalResults = response.count;
			pagination.refresh();
		});
	}
	else{
		var data = $("#searchForm").serializeObject();
		data.firstResult = offset;
		data.maxResults = pageSize;
//		console.log("searchMates form ");
//		console.log(data);
//		return;
		
		services.searchMates(data,function(response){
			populateMates(response.list,response.count);
			
			pagination.totalResults = response.count;
			pagination.refresh();
		});
	}
	
}

function submitSearch(){
	isSearch = true;
	scrollMates(0,pagination.pageSize);
}

function cleanForm (formId){
//	console.log("cleanForm ");
//	console.log($("#" + formId + " input"));
	$("#" + formId + " input").val(null);
}

$(document).ready(function(){
	cleanForm("searchForm");
	initPagination();
//	initGeolocationInput();
//	initDatePicker();
	
	scrollMates(0,pagination.pageSize);
});
