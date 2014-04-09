//form to object
//e.g. console.log($('form#createAccountForm').serializeObject());
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
$.fn.formatDatetime = function(date){
	var months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	var hours = date.getHours();
	var daytime;
	if(hours > 11){
		daytime = "PM";
		daytime = (daytime % 12);
	}
	else{
		daytime = "AM";
	}
	var minutes = date.getMinutes();
	if(minutes < 10){
		minutes = ("0" + minutes);
	}
	return months[date.getMonth()] + " " + date.getDate() + ", " + 
	date.getFullYear() + " " + hours + ":" + minutes +  " " + daytime;
};

function capitalizeInput(input){
	if(input.value.length > 1){
		input.value = (input.value.charAt(0).toUpperCase() + input.value.substr(1));
	}
	else{
		input.value = input.value.charAt(0).toUpperCase();
	}
	
}