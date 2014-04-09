var pagination;

function scrollTennisCourts(offset,pageSize){
	//console.log("scrollTennisCourts " + offset + ", " + pageSize +", " + rootDomain + "service/courts/scroll");
	
	$.ajax({
		url: rootDomain + "service/courts/scroll",
		dataType: "json",
		method: "post",
		data: {
			maxResults: pageSize,
			offset: offset
		},
		error: function() {
			console.log("error");
			console.log(arguments);
		},
		success: function( response ) {
			console.log("Success");
			console.log(response);
			
			pagination.totalResults = response.count;
			pagination.refresh();
			var courtsList = $("#courtsList");
			courtsList.empty();
			
			var list = response.list;
			for(var i in list){
				var l = list[i];
				
				console.log(l);
				var div1 = $("<div>");
				div1.append($("<img>",{src: "http://www.tennissetapp.com/public/court/07/09/08fe_8e9f.png?c=acb2"}));
				
				var div2 = $("<div>");
				div2.append($("<h2>",{html: l.courtName, 'class': "theme1"}));
				div2.append($("<p>",{text: l.phoneNumber}));
				div2.append($("<p>",{text: l.route + ", " + l.locality}));
//				div2.append($("<p>",{text: "Outdoor: Hard (2)"}));
				
				var li = $("<li>");
				li.append(div1);
				li.append(div2);
				
				courtsList.append(li);
			}
		}
	});
}

function initPagination(){
	pagination = new Pagination({});
	pagination.postCreate();
//	console.log(document.getElementById("paginationCell"));
//	console.log(pagination.domNode);
	
	document.getElementById("paginationCell").appendChild(pagination.domNode);
	pagination.render();
	pagination.onBrowse = scrollTennisCourts;
}
$(document).ready(function(){
	initPagination();
});