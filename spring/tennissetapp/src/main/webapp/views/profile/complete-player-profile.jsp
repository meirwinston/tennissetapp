<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="/WEB-INF/tld/simple-tags.tld" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value='/resources/css/global.css' />" />
	<link rel="stylesheet" href="<c:url value='/resources/js/jquery-ui-1.10.3/themes/base/jquery.ui.all.css' />" />
	
	<!-- JQuery -->
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/jquery-1.9.1.js' />"></script>
	
	<!-- JQuery UI -->
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.core.js' />"></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.widget.js' />"></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.position.js' />"></script>
	
	<script src="<c:url value='/resources/js/jquery-plugins/bday-picker.js' />"></script>
	<script src="<c:url value='/resources/js/tennissetapp/global.js' />"></script>
	<script src="<c:url value='/resources/js/tennissetapp/Services.js' />"></script>
	<script src="<c:url value='/resources/js/tennissetapp/profile/complete-player-profile.js' />"></script>
	
	<!-- Geoautocomplete Google Maps -->
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places"></script>
	<script src="<c:url value='/resources/js/jquery-plugins/jquery.geocomplete.js' />"></script>


	<!-- autocomplete -->
	<%-- <script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.menu.js' />"></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.autocomplete.js' />"></script> --%>
<title>Profile</title>
</head>
<body >
	<st:header />
	<div id="centerPane" style="margin-left: 10em;">
	<form id="personalDetailsForm" method="POST" action="<c:url value='/account/create' />">
		<h2 class="theme1" style="display: inline; margin-right: 1em;">Personal Details</h2>
		<small class="theme1">You can update your personal details at any time</small>
		<input type="hidden" name="userAccountId" value="${userAccountId}"/>
		<p>
		<label class="theme1" for="levelOfPlayInput">Level of play</label><br/> 
		<select style="width: 100px;" class="theme1" name="levelOfPlay" id="levelOfPlayInput">
			<option value="1.5">1.5</option>
			<option value="2.5">2.5</option>
			<option value="3.0">3.0</option>
			<option value="3.5">3.5</option>
			<option value="4.0">4.0</option>
			<option value="4.5">4.5</option>
			<option value="5.0">5.0</option>
			<option value="5.5">5.5</option>
			<option value="6.0">6.0</option>
			<option value="6.5">6.5</option>
			<option value="7.0">7.0</option>
		</select>
		</p>
		
		<p>
		<label class="theme1" for="handInput">Hand</label><br/> 
		<select style="width: 100px;" class="theme1" name="hand" id="handInput">
			<option value="RIGHT">Right</option>
			<option value="LEFT">Left</option>
		</select>
		</p>
		<p>
			<h3 class="theme1">Type of play</h3>
			<span id="typeOfPlayErr" class="error"></span><br/> 
			<small class="theme1" style="display: table;">you can select multiple options</small>
			<table id="typeOfPlayTable" style="border-spacing: 0em; width: 30em; height: 4em;">
				<tbody>
				<tr>
					<td><input type="checkbox" id="singlesCheckInput" name="singlesCheck" /><label class="theme1" for="singlesCheckInput">Singles</label></td>
					<td><input type="checkbox" id="doublesCheckInput" name="doublesCheck" /><label class="theme1" for="doublesCheckInput">Doubles</label></td>
					<td></td>
				</tr>
				<tr>
					<td><input type="checkbox" id="fullMatchCheckInput" name="fullMatchCheck"/><label class="theme1" for="fullMatchCheckInput">Full match</label></td>
					<td><input type="checkbox" id="pointsCheckInput" name="pointsCheck" /><label class="theme1" for="pointsCheckInput">Points</label></td>
					<td><input type="checkbox" id="HittingAroundCheckInput" name="HittingAroundCheck" /><label class="theme1" for="HittingAroundCheckInput">Hitting ground</label></td>
				</tr>
				</tbody>
			</table>
		</p>
		
		<p>
			<h3 class="theme1" style="display: inline;">Availability</h3>
			<span id="availabilityErr" class="error"></span><br/> 
			<table id="availabilityTable" style="border-spacing: 0em; width: 30em; height: 4em;">
				<tbody>
				<tr>
					<td style="color: grey;">WEEKDAYS in the</td>
					<td><input type="checkbox" id="weekdayMorningCheckInput" name="weekdayAvailabilityMorningCheck" /><label class="theme1" for="weekdayMorningCheckInput">Morning</label></td>
					<td><input type="checkbox" id="weekdayAfternoonCheckInput" name="weekdayAvailabilityAfternoonCheck" /><label class="theme1" for="weekdayAfternoonCheckInput">Afternoon</label></td>
					<td><input type="checkbox" id="weekdayEveningCheckInput" name="weekdayAvailabilityEveningCheck" /><label class="theme1" for="weekdayEveningCheckInput">Evening</label></td>
				</tr>
				<tr>
					<td style="color: grey;">WEEKENDS in the</td>
					<td><input type="checkbox" id="weekendMorningCheckInput" name="weekendAvailabilityMorningCheck" /><label class="theme1" for="weekendMorningCheckInput">Morning</label></td>
					<td><input type="checkbox" id="weekendAfternoonCheckInput" name="weekendAvailabilityAfternoonCheck"/><label class="theme1" for="weekendAfternoonCheckInput">Afternoon</label></td>
					<td><input type="checkbox" id="weekendEveningCheckInput" name="weekendAvailabilityEveningCheck" /><label class="theme1" for="weekendEveningCheckInput">Evening</label></td>
				</tr>
				</tbody>
			</table>
		</p>
		
		<!-- geolocation -->
		<h3 class="theme1">Location</h3>
		<p>
			<div style="vertical-align: top; display: inline-block; width: 405px;">
				<input class="theme1" type="text" name="geolocation" id="geolocationInput" style="width: 400px;"/>
				<label id="addressErr" for="geolocationInput" class="error"></label>
				<!-- <input type="hidden" name="addressTypes" id="addressTypesInput" /> -->
			</div>
			<div style="width: 400px; height: 200px; border: 1px solid #aaaaaa; margin-top: 5px;">
				<img id="locationStaticMap" src=""/>
			</div>
		</p>
		
		<!-- Favorite Courts -->
		<p>
			<h2 class="theme1" style="display: inline;">Favorite Courts</h2>
			<span id="availabilityErr" class="error"></span><br/> 
			<table id="favouriteCourtsTable" style="border-spacing: 0em; ">
				<tbody>
				<tr>
				<td style="vertical-align: top; padding-right: 1em;">
					<small class="theme1">Find tennis courts and add them to your favourite courts list to the right. You can add as many as you like.</small>
					<br />
					<!-- <input id="courtsInput" type="text"></input> -->
					<select class="theme1" id="courtsInput"></select>
					<button class="theme1" type="button" onclick="addFavouriteCourt()">ADD</button>
				</td>
				<td style="border-left: 1px solid #aaaaaa; vertical-align: top; padding-left: 1em;"><h4 class="theme1" style="white-space: nowrap;">My Favourite Courts</h4>
				<ul class="theme1" id="myFavouriteCourts" style="list-style-image:url(<c:url value='/resources/images/remove_15x15.png' />); color: grey;"></ul>
				</td>
				</tr>
				</tbody>
			</table>
		</p>
		<input id="favouriteCourtsInput" name="favouriteCourts" type="hidden" />
		
		<!-- address -->
		<st:address-form id="addressInputs"/>
		<!-- address END -->
		
		<button class="theme1" type="button" id="submitButton" onclick="submitForm()">Create Profile</button>
	</form>
	</div>
	<st:footer />
	<script>
		//console.log("---> " + rootDomain + "service/profile/update");
		//console.log("here are the options: ");
		//console.log($("#levelOfPlayInput").find("option[value='4.5']"));
		$("#levelOfPlayInput").find("option[value='${levelOfPlay}']").attr("selected","selected");
		$("#handInput").find("option[value='${hand}']").attr("selected","selected");
		
		<c:if test="${singlesCheck}">
			$("#singlesCheckInput").attr("checked","checked");
		</c:if>
		
		<c:if test="${doublesCheck}">
			$("#doublesCheckInput").attr("checked","checked");
		</c:if>

		<c:if test="${fullMatchCheck}">
			$("#fullMatchCheckInput").attr("checked","checked");
		</c:if>
		
		<c:if test="${pointsCheck}">
			$("#pointsCheckInput").attr("checked","checked");
		</c:if>

		<c:if test="${HittingAroundCheck}">
			$("#HittingAroundCheckInput").attr("checked","checked");
		</c:if>

		<c:if test="${weekdayAvailabilityMorningCheck}">
			$("#weekdayMorningCheckInput").attr("checked","checked");
		</c:if>
		
		<c:if test="${weekdayAvailabilityAfternoonCheck}">
			$("#weekdayAfternoonCheckInput").attr("checked","checked");
		</c:if>
	
		<c:if test="${weekdayAvailabilityEveningCheck}">
			$("#weekdayEveningCheckInput").attr("checked","checked");
		</c:if>
		
		<c:if test="${weekendAvailabilityMorningCheck}">
			$("#weekendMorningCheckInput").attr("checked","checked");
		</c:if>
		
		<c:if test="${weekendAvailabilityAfternoonCheck}">
			$("#weekendAfternoonCheckInput").attr("checked","checked");
		</c:if>
		
		<c:if test="${weekendAvailabilityEveningCheck}">
			$("#weekendEveningCheckInput").attr("checked","checked");
		</c:if>
	</script>
</body>

</html>