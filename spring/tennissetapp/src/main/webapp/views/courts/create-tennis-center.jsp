<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="/WEB-INF/tld/simple-tags.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tennis SetApp - The smart way to meet and play - Create Court</title>
	<link rel="stylesheet" href="<c:url value='/resources/js/jquery-ui-1.10.3/themes/base/jquery.ui.all.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/global.css'/>">
	
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/jquery-1.9.1.js' />"></script>
	<script src="<c:url value='/resources/js/tennissetapp/courts/create-tennis-center.js' />" ></script>
	<script src="<c:url value='/resources/js/tennissetapp/Services.js' />" ></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery-ui.js' />"></script>
	<script src="<c:url value='/resources/js/jquery.numeric.js' />"></script>
	
	<!-- Geoautocomplete Google Maps -->
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places"></script>
	<script src="<c:url value='/resources/js/jquery-plugins/jquery.geocomplete.js' />"></script>
	
	<style>
		
		#layoutTable{
		}
		#centerPane{
		}
		#formTable{
			background: none repeat scroll 0 0 #F4F3F1;
			margin-left: auto;
			margin-right: auto;
			width: 450px;
			/* text-align: center; */
			padding: 0 1.2em 1.2em 1.2em;
			margin-top: 1.5em;
		}
		
		td.devidor{
			border-bottom: 1px solid #BBBBBB;
			height: 40px;
			color: #989797;
			text-align: left;
		}
		#imgPanel{
			background: none repeat scroll 0 0 white;
			border: 1px solid #BBBBBB;
			border-radius: 5px 5px 5px 5px;
			height: 200px;
			margin-left: auto;
			margin-right: auto;
			width: 403px;
		}
		#buttonPanel{
			display: table;
			margin-left: auto;
			margin-right: auto;
			margin-top: 2em;
		}
		div.dropdownWrapper{
			background-image: url("/tennissetapp/resources/images/dropdown-icon_17x18.png");
			background-position: right;
			background-repeat: no-repeat;
		}
		/* select.theme1{
			 width:100px;
    		-webkit-appearance: none;
    		-moz-appearance: none;
    		appearance: none;
    		padding: 2px 2px 2px 20px;
    		border: none;
			
		} */
	</style>
</head>
<body>
	<st:header />
	<st:menubar />
	<div id="centerPane">
		<form id="createForm" method="post" action="<c:url value='/service/courts/create' />">
			<table id="formTable">
			<tr>
				<td style="height: 3.2em; text-align: center;"><h2 class="theme1">CREATE A TENNIS CENTER</h2></td>
			</tr>
			<tr>
				<td >
					<input name="courtName" class="long" type="text" placeholder="Tennis center name" onblur="capitalizeInput(this)" ></input>
					<div id="courtNameErr" class="error" style="padding: 6px 0px 10px 0px; display: none;"></div>
				</td>
			</tr>
			<tr>
				<td style="padding-top: 6px;">
					<input class="long" type="text" name="geolocation" id="geolocationInput" placeholder="Enter a location"/>
					<div  id="addressErr" class="error" style="padding: 6px 0px 10px 0px; display: none"></div>
				</td>
			</tr>
			<tr>
				<td style="padding-top: 6px;">
					<div id="imgPanel">
						<img style="visibility: hidden;" id="locationStaticMap" src=""/>
					</div>
				</td>
			</tr>
			<tr>
				<td class="devidor">OUTDOOR</td>
			</tr>
			<tr>
				<td>
					<table id="outdoorTableLayout" style="width: 100%;">
						<tbody>
						<tr>
							<td>
							<select class="theme1" name="outdoorSurface" style="width: 10em;">
								<option label="" value="" selected="selected"></option>
								<option label="Hard" value="HARD">Hard</option>
								<option label="Clay" value="CLAY">Clay</option>
								<option label="Synthetic" value="SYNTHETIC">Synthetic</option>
								<option label="Grass" value="GRASS">Grass</option>
								<option label="Carpet" value="CARPET">Carpet</option>
							</select>
							</td>
							<td>
								<label class="theme1" for="outdoorNumberInput">Number of courts:</label>
								<input onfocus="$(this).numeric({decimal: false, negative: false});" maxlength="1" style="width: 4em; margin-left: 10px;" class="theme1" id="outdoorNumberInput" type="text" name="numberOfOutdoorCourts">
							</td>
						</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td>
				<a class="theme1" href="javascript:addOutdoorCourt()">Add more outdoor courts</a>
				<div style="padding: 6px 0px 10px 0px;display: none;" id="outdoorSurfaceErr" class="error"></div>
				</td>
			</tr>
			<tr>
				<td class="devidor">INDOOR</td>
			</tr>
			<tr>
				<td>
					<table id="indoorTableLayout" style="width: 100%;">
						<tr>
						<td>
							<select class="theme1" name="indoorSurface" style="width: 10em;">
								<option label="" value="" selected="selected"></option>
								<option label="Hard" value="HARD">Hard</option>
								<option label="Clay" value="CLAY">Clay</option>
								<option label="Synthetic" value="SYNTHETIC">Synthetic</option>
								<option label="Grass" value="GRASS">Grass</option>
								<option label="Carpet" value="CARPET">Carpet</option>
							</select>
						</td>
						<td>
							<label class="theme1" for="indoorNumberInput0">Number of courts:</label>
							<input onfocus="$(this).numeric({decimal: false, negative: false});" maxlength="1" id="indoorNumberInput0" class="theme1" style="width: 4em;  margin-left: 10px;" type="text" name="numberOfIndoorCourts">
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
				<a class="theme1" href="javascript:addIndoorCourt()">Add more indoor courts</a>
				<div id="indoorSurfaceErr" style="padding: 6px 0px 10px 0px; display: none;" class="error"></div>
				</td>
			</tr>
			<tr>
				<td style="padding-top: 15px;">
					<input class="long" type="text" name="phoneNumber" placeholder="Phone number"></input>
					<div style="padding: 6px 0px 10px 0px; display: none;"><label id="phoneNumberErr" class="error"></label></div>
				</td>
			</tr>
			<tr>
				<td style="padding-top: 6px;">
					<input class="long" type="text" name="website" placeholder="Website"></input>
					<div style="padding: 6px 0px 10px 0px; display: none;"><label id="websiteErr" class="error"></label></div>
				</td>
			</tr>
			<tr>
			<td style="text-align: center; padding-top: 20px;">
				<button type="button" onclick="submitForm();" class="theme1" style="height: 2em;">Create</button>
			</td>
			</tr>
		</table>
		<st:address-form />
		</form>
	</div>
	<st:footer />
</body>
</html>