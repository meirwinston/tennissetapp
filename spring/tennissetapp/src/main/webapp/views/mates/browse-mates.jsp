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
	<script src="<c:url value='/resources/js/tennissetapp/Services.js' />"></script>
	<script src="<c:url value='/resources/js/tennissetapp/mates/browse-mates.js' />"></script>
	<script src="<c:url value='/resources/js/Pagination.js' />" ></script>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/global.css' />" />
	<!-- Geoautocomplete Google Maps -->
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places"></script>
	<script src="<c:url value='/resources/js/jquery-plugins/jquery.geocomplete.js' />"></script>
	
<title>Mates</title>
<style>
#profileDetailsList > li{
	height: 2.5em;
}

#layoutTable{
	margin-left: auto;
	margin-right: auto;
	margin-top: 2.5em;
}

#centerPane{
	padding: 0 2em 0em 2em;
	vertical-align: top;
}

.sideboxTextInput{
	border: 1px solid #DDDDDD;
	border-radius: 5px 5px 5px 5px;
	height: 2em;
	width: 13.5em;
	white-space: nowrap;
}

.sideboxTitle{
	border-bottom: 1px solid #DDDDDD; 
	padding-bottom: 1em; 
	margin-bottom: 1em;
}
.sidebox{
	background-color: #EEEEEE; 
	width: 14em; 
	text-align: center;
	padding-top: 1em;
	padding-bottom: 1em;
	margin-bottom: 1em;
}
input[type="checkbox"]{
	margin-bottom: 1em;
}
label{
	margin-right: 1em;
}
</style>
</head>
<body>
	<st:header />
	<st:menubar />
	<table id="layoutTable">
	<tbody>
	<tr>
		<td style="vertical-align: top;">
			<form id="searchForm" method="get">
			<div class="sidebox">
				<div class="theme1-font-normal-dark sideboxTitle">SEARCH</div>
				<input placeholder="Name or Email" class="sideboxTextInput" type="text" name="nameOrEmail" id="nameOrEmailInput" />
			</div>
			<div class="sidebox">
				<div class="theme1-font-normal-dark sideboxTitle">LOCATION</div>
				<input placeholder="City or Zipcode" class="sideboxTextInput" type="text" name="location" id="geolocationInput" />
			</div>
			<div class="sidebox">
				<div class="theme1-font-normal-dark sideboxTitle">LEVEL OF PLAY</div>
				<div style="text-align: left; padding-left: 2em; color: gray;">
					<input id="levelcheck1" type="checkbox" name="level1"/>
					<label for="levelcheck1">1-1.5</label>
					
					<input id="levelcheck2" type="checkbox" name="level2"/>
					<label for="levelcheck2">2-2.5</label>
					<br/>
					<input id="levelcheck3" type="checkbox" name="level3"/>
					<label for="levelcheck3">3-3.5</label>
					
					<input id="levelcheck4" type="checkbox" name="level4"/>
					<label for="levelcheck4">4-4.5</label>
					<br/>
					<input id="levelcheck5" type="checkbox" name="level5" />
					<label for="levelcheck5">5+</label>
				</div>
			</div>
			<div class="sidebox">
				<div class="theme1-font-normal-dark sideboxTitle">TYPE OF PLAY</div>
				<div style="text-align: left; padding-left: 2em; color: gray;">
					<input id="singlescheck" type="checkbox" name="playSingles" />
					<label for="singlescheck">Singles</label>
					<br/>
					<input id="doublescheck" type="checkbox" name="playDoubles"/>
					<label for="doublescheck">Doubles</label>
					<br/>
					<input id="fullmatchcheck" type="checkbox" name="playFullMatch"/>
					<label for="fullmatchcheck">Full Match</label>
					<br />
					<input id="pointscheck" type="checkbox" name="playPoints" />
					<label for="pointscheck">Points</label>
					<br/>
					<input id="HittingAroundcheck" type="checkbox" name="playHittingAround"/>
					<label for="HittingAroundcheck">Hitting Ground</label>
				</div>
			</div>
			<div class="sidebox">
				<div class="theme1-font-normal-dark sideboxTitle">AVAILABILITY</div>
				<div style="text-align: left; padding-left: 0.5em; color: gray;">
				<table id="availabilityTable" style="border-spacing: 0em;">
					<thead>
						<tr>
						<th>WEEKDAYS</th>
						<th>WEEKENDS</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<td><input type="checkbox" id="weekdayMorningCheckInput" name="weekdayAvailabilityMorningCheck" /><label class="theme1" for="weekdayMorningCheckInput">Morning</label></td>
						<td><input type="checkbox" id="weekendMorningCheckInput" name="weekendAvailabilityMorningCheck" /><label class="theme1" for="weekendMorningCheckInput">Morning</label></td>
						</tr>
						<tr>
						<td><input type="checkbox" id="weekdayAfternoonCheckInput" name="weekdayAvailabilityAfternoonCheck" /><label class="theme1" for="weekdayAfternoonCheckInput">Afternoon</label></td>
						<td><input type="checkbox" id="weekendAfternoonCheckInput" name="weekendAvailabilityAfternoonCheck"/><label class="theme1" for="weekendAfternoonCheckInput">Afternoon</label></td>
						</tr>
						<tr>
						<td><input type="checkbox" id="weekdayEveningCheckInput" name="weekdayAvailabilityEveningCheck" /><label class="theme1" for="weekdayEveningCheckInput">Evening</label></td>
						<td><input type="checkbox" id="weekendEveningCheckInput" name="weekendAvailabilityEveningCheck" /><label class="theme1" for="weekendEveningCheckInput">Evening</label></td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			
			<div style="text-align: center;">
				<button type="button" class="theme1" onclick="submitSearch();" >Search</button>
			</div>
			<input id="latitudeInput" name="latitude" type="hidden" />
			<input id="longitudeInput" name="longitude" type="hidden" />
			<input id="addressTypesInput" name="addressTypes" type="hidden" />
			</form>
		</td>
		<td id="centerPane"></td>
	</tr>
	<tr>
		<td></td>
		<td id="paginationCell"></td>
	</tr>
	</tbody>
	</table>
	<st:footer />
</body>
</html>