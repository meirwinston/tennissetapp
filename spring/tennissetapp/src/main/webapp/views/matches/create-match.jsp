<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="/WEB-INF/tld/simple-tags.tld" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value='/resources/js/jquery-ui-1.10.3/themes/base/jquery.ui.all.css' />">
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/jquery-1.9.1.js' />"></script>
	
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery-ui.js' />"></script>
	
	<!-- Geoautocomplete Google Maps -->
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places"></script>
	<script src="<c:url value='/resources/js/jquery-plugins/jquery.geocomplete.js' />"></script>
	
	<script src="<c:url value='/resources/js/tennissetapp/matches/create-match.js' />"></script>
	<link rel="stylesheet" href="<c:url value='/resources/css/global.css' />" />
<title>Matches</title>
	
<style>

/* .ui-widget {
    font-family: Verdana,Arial,sans-serif;
    font-size: 0.8em;
} */

#header {
	width: 100%;
	background-image: url('../images/header-background_137x40.png');
	background-repeat: repeat-x;
	background-size: 137px 40px;
	/*for some reason putting an image requires us to stretch the background 4 more pixels*/
	height: 40px;
	color: white;
}

#header>table {
	border-spacing: 0;
	border-collapse: collapse;
	height: 40px;
}

#logoCell {
	background-image: url(../images/tennissetapp_logo_444x32.png);
	background-repeat: no-repeat;
	width: 444px;
	background-position: 0 3px;
}

#signupCell {
	text-decoration: none;
	color: white;
	cursor: pointer;
	background: none repeat scroll 0 0 #2EB0B9;
	padding-left: 20px;
	padding-right: 20px;
}

#formBox {
	background-color: #F1F0ED;
	width: 465px;
	margin: 50px auto auto;
}

#formHeaderCell {
	text-align: center;
	height: 3.2em;
	color: #373538;
	font-family: Lato, Arial, Helvetica, sans-serif;
	font-size: 1.3em;
	font-weight: 400;
	line-height: 70px;
}

#dateInputWrapper {
	border: 1px solid #BBBBBB;
	border-radius: 5px 5px 5px 5px;
	height: 40px;
	text-indent: 60px;
	width: 400px;
	color: grey;
	font-size: medium;
	margin: 2em auto auto;
	background-color: white;
	display: table;
}

.timePickerWrapper{
	height: 40px;
	width: 400px;
	color: grey;
	font-size: medium;
	margin-right: auto;
	margin-left: auto;
	display: table;
}


.title{
	height: 40px;
	width: 400px;
	color: grey;
	font-size: medium;
	margin-right: auto;
	margin-left: auto;
	display: table;
	border-bottom: 1px solid grey;
}
.timePickerWrapper select{
	width: 60px;
	margin-left: 10px;
}

.topInputWrapper {
	border: 1px solid #BBBBBB;
	border-radius: 5px 5px 0 0;
	height: 40px;
	text-indent: 60px;
	width: 400px;
	color: grey;
	font-size: medium;
	margin: 0 auto auto;
	background-color: white;
	display: table;
}

.middleInputWrapper {
	border: 1px solid #BBBBBB;
	border-top: 1px solid white;
	border-bottom: 1px solid white;
	border-radius: 0 0 0 0;
	height: 40px;
	text-indent: 60px;
	width: 400px;
	color: grey;
	font-size: medium;
	margin: 0 auto auto;
	background-color: white;
	display: table;
}

.bottomImputWrapper {
	border: 1px solid #BBBBBB;
	border-radius: 0 0 5px 5px;
	height: 40px;
	text-indent: 60px;
	width: 400px;
	color: grey;
	font-size: medium;
	margin: 0 auto auto;
	background-color: white;
	display: table;
}

#submitButton {
	background: none repeat scroll 0 0 #2EB0B9;
	border: 0 none;
	border-radius: 5px 5px 5px 5px;
	font-size: 1.3em;
	font-weight: normal;
	height: 44px;
	text-shadow: 0 0 0 #107532;
	text-transform: none;
	width: 115px;
	color: white;
	cursor: pointer;
}

.inputWrapper {
	text-align: center;
	padding: 0 1.3em;
}

#checkboxWrapper {
	padding-left: 5px;
	color: gray;
	font-size: smaller;
	height: 50px
}

.submitButtonWrapper {
	text-align: center;
	height: 80px;
}

ul.vlist{
	list-style: none;
	display: inline-table;
}

ul.vlist > li{
	float: left;
}
</style>
</head>
<body>
	<st:header />
	<div id="formBox">
	<div id="formHeaderCell">CREATE A TENNIS MATCH</div>
	<div style="width: 400px; margin: auto; visibility: hidden;">
		<span  class="error" id="nameErr" /><br/>
		<span  class="error" id="courtErr" />
	</div>
	<form id="createForm" method="POST" action="<c:url value='/service/matches/create' />">
		<div class="topInputWrapper">
			<div style="vertical-align: middle; display: table-cell;">
			<input type="text" placeholder="Match name" autofocus="autofocus" tabindex="1" value="" id="nameInput" name="name" style="border: 0">
			</div>
		</div>
		<div class="middleInputWrapper">
			<div style="vertical-align: middle; display: table-cell;">
			<input type="text" placeholder="City" tabindex="2" value="" id="geolocationInput" name="address" style="border: 0;"/>
			</div>
		</div>
		<div class="bottomImputWrapper">
			<div style="vertical-align: middle; display: table-cell;">
			Tennis court
			<select id="courtsSelect" name="tennisCenterId">
			</select>
			</div>
		</div>
		
		<div id="dateInputWrapper" >
			<div style="vertical-align: middle; display: table-cell;">
			<input type="text" placeholder="Date" autofocus="autofocus" tabindex="1" value="" id="datepicker" name="date" style="border: 0; width: 60%;">
			</div>
			<span  class="error" id="startErr" />
		</div>
		
		<div class="timePickerWrapper" style="margin-top: 2em;">
			<div style="vertical-align: middle; display: table-cell;">
			Start time:
			<select class="theme1">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>
			<select class="theme1">
				<option value="0">00</option>
				<option value="15">15</option>
				<option value="30">30</option>
				<option value="45">45</option>
			</select>
			<select class="theme1">
				<option value="AM">AM</option>
				<option value="PM">PM</option>
			</select>
			</div>
		</div>
		
		<div class="timePickerWrapper">
			<div style="vertical-align: middle; display: table-cell;">
			End time:&nbsp;&nbsp;
			<select class="theme1">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>
			<select class="theme1">
				<option value="0">00</option>
				<option value="15">15</option>
				<option value="30">30</option>
				<option value="45">45</option>
			</select>
			<select class="theme1">
				<option value="AM">AM</option>
				<option value="PM">PM</option>
			</select>
			</div>
		</div>
		<div class="title">
			LEVEL OF PLAY
		</div>
		<div style="width: 400px; margin-left: auto; margin-right: auto;">
			<input type="text" id="levelOfPlayInput" name="levelOfPlay" class="theme1" />			
		</div>
		<div class="title">
			TYPE OF PLAY
		</div>
		<div>
			<ul class="vlist">
				<li>
					<input name="singlesCheck" id="singlesCheck" type="checkbox" />
					<label for="singlesCheck">Singles</label>
				</li>
				<li>
					<input name="doublesCheck" id="doublesCheck" type="checkbox" />
					<label for="doublesCheck">Doubles</label>
				</li>
				<li>
					<input name="fullMatchCheck" id="fullMatchCheck" type="checkbox" />
					<label for="fullMatchCheck">Full match</label>
				</li>
				<li>
					<input name="pointsCheck" id="pointsCheck" type="checkbox" />
					<label for="pointsCheck">Points</label>
				</li>
				<li>
					<input name="HittingAroundCheck" id="HittingAroundCheck" type="checkbox" />
					<label for="HittingAroundCheck">5+</label>
				</li>
			</ul>
		</div>
		<div class="title">
			WHO CAN VIEW AND RSVP TO THIS MATCH?
		</div>
		<div>
			<input type="radio" id="everyoneRadio" name="visibility" value="PUBLIC" />
			<label for='everyoneRadio'>Everyone</label>
			
			<input type="radio" id="friendsRadio" name="visibility" value="CYCLE" />
			<label for='friendsRadio'>Only the friends I invite</label>
		</div>
		
		<div>
			<button type="button" onclick="submitForm();" class="theme1" style="height: 2em;">Create Match</button>
		</div>
	</form>
	</div>
	<st:footer />
</body>
</html>