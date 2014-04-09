$(function() {
	$( "#city" ).autocomplete({
		source: function( request, response ) {
			$.ajax({
				url: "service/scroll",
				dataType: "json",
				data: {
					maxRows: 12,
					term: request.term
				},
				error: function() {
					console.log("error");
					console.log(arguments);
				},
				success: function( data ) {
					response(data.items);
				}
			});
		},
		minLength: 1, //how many characters should trigger ajax
		select: function( event, ui ) {
			console.log( ui.item ?
				"Selected: " + ui.item.label :
				"Nothing selected, input was " + this.value);
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		}
	});
});