var pagination;
var datepicker;
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
	pagination.onBrowse = scrollMatches;
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

function initDatePicker(){
	datepicker = $( "#datepicker" ).datepicker();
	datepicker.datepicker( "option", "dateFormat", "yy-mm-dd" );
	
	datepicker = datepicker.data().datepicker;
}

function populateMatches(list,count){
	if(!list) return;
	
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
		pagination.totalResults = 0;
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
				"<span class='theme1-font-title'>" + list[i].name + "</span>&nbsp;<img style='border: 0; margin-left: 1.5em; margin-right: 1.2em;' src='/tennissetapp/resources/images/level_18x14.png' />" + list[i].levelOfPlayMin}));
			r.append($("<li>",{text: list[i].orgenizerFirstName + " " + list[i].orgenizerLastName})
					.css("list-style-image","url('" + rootDomain + "resources/images/user_15x15.png')"));
			if(list[i].tennisCenterName.length > 0){
				s = list[i].tennisCenterName;
				if(list[i].address.length > 0){
					s += "&nbsp;|&nbsp;";
					s += list[i].address;
				}
				r.append($("<li>",{html: s})
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
			$(td)
			.css("width","100px").css("height","160px")
			.append($("<img>",{
				src: "http://maps.googleapis.com/maps/api/staticmap?center=" + 
				list[i].latitude + "," + list[i].longitude + 
				"&zoom=13&size=100x160&maptype=roadmap&markers=color:red%7Ccolor:red%7Clabel:C%7C" + 
				list[i].latitude + "," + list[i].longitude + "&sensor=false"}));
			
			td = tr.insertCell(tr.cells.length);
			$(td).css("vertical-align","top").append(r);
			
			var li = $("<li>");
			li.append(t);
			ul.append(li);
		}
		$("#centerPane").html("").append(ul);
	}
}

function scrollMatches(offset,pageSize){
	services.scrollMatchesNearbyUser({
		firstResult: offset,
		maxResults: pageSize
	},function(response){
		console.log("Success");
		console.log(response);
		populateMatches(response.list,response.count);
		
		pagination.totalResults = response.count;
		pagination.refresh();
	});
}

function scrollSearch(offset,pageSize){
	var data = $("#searchForm").serializeObject();
	data.firstResult = offset;
	data.maxResults = pageSize;
	
	services.searchMatches(data, function(response){
		console.log("Success");
		console.log(response);
		populateMatches(response.list,response.count);
		pagination.totalResults = response.count;
		pagination.refresh();
	});
//	$.ajax({
//		url: rootDomain + "service/matches/search?" + $("#searchForm").serialize(),
//		dataType: "json",
//		method: "get",
//		data: {
//			offset: offset,
//			maxResults: pageSize
//		},
//		error: function() {
//			console.log("error");
//			console.log(arguments);
//		},
//		success: function( response ) {
//			console.log("Success");
//			console.log(response);
//			populateMatches(response.list,response.count);
//			pagination.totalResults = response.count;
//			pagination.refresh();
//		}
//	});
}

function submitSearch(){
	console.log("submitSearch");
	console.log(datepicker);
	console.log(datepicker.selectedYear);
	document.getElementById("dateYearInput").value = datepicker.selectedYear;
	document.getElementById("dateMonthInput").value = datepicker.selectedMonth;
	document.getElementById("dateDayInput").value = datepicker.selectedDay;
	document.getElementById("timezoneInput").value = new Date().getTimezoneOffset();
	var form = $("#searchForm");
	var data = $(form).serialize();
	scrollSearch(0,pagination.pageSize,data);
	
	
}

function cleanForm (formId){
	console.log("cleanForm ");
	console.log($("#" + formId + " input"));
	$("#" + formId + " input").val(null);
}

$(document).ready(function(){
	cleanForm("searchForm");
	initPagination();
	try{
		initGeolocationInput();
	}
	catch(e){
		console.log(e);
	}
	initDatePicker();
	scrollMatches(0,pagination.pageSize);
});
