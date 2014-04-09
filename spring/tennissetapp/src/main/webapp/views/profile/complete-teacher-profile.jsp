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
	<script src="<c:url value='/resources/js/jquery-plugins/bday-picker.js' />"></script>
	<script src="<c:url value='/resources/js/tennissetapp/global.js' />"></script>
	<script src="<c:url value='/resources/js/tennissetapp/profile/complete-teacher-profile.js' />"></script>
	
	<!-- Geoautocomplete Google Maps -->
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places"></script>
	<script src="<c:url value='/resources/js/jquery-plugins/jquery.geocomplete.js' />"></script>
	

<title>Profile</title>
</head>
<body>
	<st:header />
	<div id="centerPane" style="margin-left: 10em;">
	<form id="personalDetailsForm" method="POST" action="<c:url value='/account/create' />">
		<h2 class="theme1">Personal Details</h2>
		<h5 class="theme1">You can update your personal details at any time</h5>
		<h3 class="theme1" style="margin-top: 10px;">Experience &amp; Speciality</h3>
		<p>
			<h4 class="theme1" style="display: table;">Are you a certified teacher?</h4>
			<input type="radio" id="certifiedInput" name="certified" />
			<label class="theme1" for="certifiedInput" style="padding-right: 3em;">YES</label>
			
			<input type="radio" id="noncertifiedInput" name="certified" />
			<label class="theme1" for="noncertifiedInput">NO</label>
		</p>
		<p>
			<h4 class="theme1" style="display: table;">Certifying organization</h4>
			<input id="usptaInput" name="usptaCertified" type="checkbox" class="theme1" /> 
			<label class="theme1" for="usptaInput">USPTA</label>
			
			<input id="usptrInput" name="usptrCertified" type="checkbox" class="theme1" style="margin-left: 50px;" /> 
			<label class="theme1" for="usptrInput">USPTR</label>
			
			<input id="otherCheckInput" type="checkbox" style="margin-left: 50px;" />
			<label class="theme1" for="otherInput">Other:</label>
			<input id="otherInput" name="certifyingOrganization" type="text" class="theme1"/>
			<span class="error" id="certifyingOrganizationErr"></span>
		</p>
		<p>
			<!-- <label class="theme1" style="display: table;" for="yearsOfExperienceInput">Years of experience</label> -->
			<h4 class="theme1" style="display: table;">Years of experience</h4>
			<input class="theme1" type="text" name="yearsOfExperience" id="yearsOfExperienceInput" />
			<span class="error" id="yearsOfExperienceErr"></span>
		</p>
		<h4 class="theme1" style="display: table; margin-bottom: 10px;">Speciality</h4>
		<p>
			<small class="theme1">You can select multiple options</small><br/>
			<input id="specialityAdultsInput" name="specialityAdults" type="checkbox" class="theme1" /> 
			<label class="theme1" for="specialityAdults" style="padding-right: 3em;" >Adults</label>
			
			<input id="specialityJuniorsInput" name="specialityJuniors" type="checkbox" class="theme1" /> 
			<label class="theme1" for="specialityJuniorsInput" style="padding-right: 3em;">Juniors</label>
			
			<input id="specialityTurnamentsInput" name="specialityTurnaments" type="checkbox" class="theme1" /> 
			<label class="theme1" for="specialityTurnamentsInput">Turnaments</label>
			
			<span class="error" id="specialityErr"></span>
		</p>
		<h3 class="theme1" style="border-top: 1px solid #aaaaaa; padding-top: 1em;">Hours & Rates</h3>
		<h4 class="theme1" style="margin-top: 10px;">Availability</h4>
		<span class="error" id="availabilityErr"></span>
		<p>
				<table style="border-spacing: 0em; width: 30em; height: 4em; ">
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
		<p>
			<!-- <label class="theme1" style="display: table;" for="clinicRatesInput">Lesson/Clinic rates</label> -->
			<h4 class="theme1" style="display: table;">Lesson/Clinic rates</h4>
			<input class="theme1" style="width: 300px;" type="text" name="clinicRates" id="clinicRatesInput" />
			<span class="error" id="clinicRatesErr"></span>
		</p>
		<p>
			<!-- <label class="theme1" style="display: table;" for="currencyInput">Currency</label> -->
			<h4 class="theme1" style="display: table;">Currency</h4>
			<select style="width: 100px;" class="theme1" name="currency" id="currencyInput">
				<option label="USD" value="348">USD</option>
		    <option label="EUR" value="258">EUR</option>
		    <option label="INR" value="276">INR</option>
		    <option label="CAD" value="240">CAD</option>
		    <option label="ADP" value="217">ADP</option>
		    <option label="AED" value="218">AED</option>
		    <option label="AFA" value="219">AFA</option>
		    <option label="ALL" value="220">ALL</option>
		    <option label="ANG" value="221">ANG</option>
		    <option label="AOK" value="222">AOK</option>
		    <option label="ARS" value="223">ARS</option>
		    <option label="AUD" value="224">AUD</option>
		    <option label="AWG" value="225">AWG</option>
		    <option label="BBD" value="226">BBD</option>
		    <option label="BDT" value="227">BDT</option>
		    <option label="BGN" value="228">BGN</option>
		    <option label="BHD" value="229">BHD</option>
		    <option label="BIF" value="230">BIF</option>
		    <option label="BMD" value="231">BMD</option>
		    <option label="BND" value="232">BND</option>
		    <option label="BOB" value="233">BOB</option>
		    <option label="BRL" value="234">BRL</option>
		    <option label="BSD" value="235">BSD</option>
		    <option label="BTN" value="236">BTN</option>
		    <option label="BUK" value="237">BUK</option>
		    <option label="BWP" value="238">BWP</option>
		    <option label="BZD" value="239">BZD</option>
		    <option label="CHF" value="241">CHF</option>
		    <option label="CLF" value="242">CLF</option>
		    <option label="CLP" value="243">CLP</option>
		    <option label="CNY" value="244">CNY</option>
		    <option label="COP" value="245">COP</option>
		    <option label="CRC" value="246">CRC</option>
		    <option label="CZK" value="247">CZK</option>
		    <option label="CUP" value="248">CUP</option>
		    <option label="CVE" value="249">CVE</option>
		    <option label="CYP" value="250">CYP</option>
		    <option label="DKK" value="251">DKK</option>
		    <option label="DOP" value="252">DOP</option>
		    <option label="DZD" value="253">DZD</option>
		    <option label="ECS" value="254">ECS</option>
		    <option label="EGP" value="255">EGP</option>
		    <option label="EEK" value="256">EEK</option>
		    <option label="ETB" value="257">ETB</option>
		    <option label="FJD" value="259">FJD</option>
		    <option label="FKP" value="260">FKP</option>
		    <option label="GBP" value="261">GBP</option>
		    <option label="GHC" value="262">GHC</option>
		    <option label="GIP" value="263">GIP</option>
		    <option label="GMD" value="264">GMD</option>
		    <option label="GNF" value="265">GNF</option>
		    <option label="GTQ" value="266">GTQ</option>
		    <option label="GWP" value="267">GWP</option>
		    <option label="GYD" value="268">GYD</option>
		    <option label="HKD" value="269">HKD</option>
		    <option label="HNL" value="270">HNL</option>
		    <option label="HTG" value="271">HTG</option>
		    <option label="HUF" value="272">HUF</option>
		    <option label="IDR" value="273">IDR</option>
		    <option label="IEP" value="274">IEP</option>
		    <option label="ILS" value="275">ILS</option>
		    <option label="IQD" value="277">IQD</option>
		    <option label="IRR" value="278">IRR</option>
		    <option label="JMD" value="279">JMD</option>
		    <option label="JOD" value="280">JOD</option>
		    <option label="JPY" value="281">JPY</option>
		    <option label="KES" value="282">KES</option>
		    <option label="KHR" value="283">KHR</option>
		    <option label="KMF" value="284">KMF</option>
		    <option label="KPW" value="285">KPW</option>
		    <option label="KRW" value="286">KRW</option>
		    <option label="KWD" value="287">KWD</option>
		    <option label="KYD" value="288">KYD</option>
		    <option label="LAK" value="289">LAK</option>
		    <option label="LBP" value="290">LBP</option>
		    <option label="LKR" value="291">LKR</option>
		    <option label="LRD" value="292">LRD</option>
		    <option label="LSL" value="293">LSL</option>
		    <option label="LYD" value="294">LYD</option>
		    <option label="MAD" value="295">MAD</option>
		    <option label="MGF" value="296">MGF</option>
		    <option label="MNT" value="297">MNT</option>
		    <option label="MOP" value="298">MOP</option>
		    <option label="MRO" value="299">MRO</option>
		    <option label="MTL" value="300">MTL</option>
		    <option label="MUR" value="301">MUR</option>
		    <option label="MVR" value="302">MVR</option>
		    <option label="MWK" value="303">MWK</option>
		    <option label="MXP" value="304">MXP</option>
		    <option label="MYR" value="305">MYR</option>
		    <option label="MZM" value="306">MZM</option>
		    <option label="NAD" value="307">NAD</option>
		    <option label="NGN" value="308">NGN</option>
		    <option label="NIO" value="309">NIO</option>
		    <option label="NOK" value="310">NOK</option>
		    <option label="NPR" value="311">NPR</option>
		    <option label="NZD" value="312">NZD</option>
		    <option label="OMR" value="313">OMR</option>
		    <option label="PAB" value="314">PAB</option>
		    <option label="PEN" value="315">PEN</option>
		    <option label="PGK" value="316">PGK</option>
		    <option label="PHP" value="317">PHP</option>
		    <option label="PKR" value="318">PKR</option>
		    <option label="PLN" value="319">PLN</option>
		    <option label="PYG" value="320">PYG</option>
		    <option label="QAR" value="321">QAR</option>
		    <option label="RON" value="322">RON</option>
		    <option label="RWF" value="323">RWF</option>
		    <option label="SAR" value="324">SAR</option>
		    <option label="SBD" value="325">SBD</option>
		    <option label="SCR" value="326">SCR</option>
		    <option label="SDP" value="327">SDP</option>
		    <option label="SEK" value="328">SEK</option>
		    <option label="SGD" value="329">SGD</option>
		    <option label="SHP" value="330">SHP</option>
		    <option label="SLL" value="331">SLL</option>
		    <option label="SOS" value="332">SOS</option>
		    <option label="SRG" value="333">SRG</option>
		    <option label="STD" value="334">STD</option>
		    <option label="RUB" value="335">RUB</option>
		    <option label="SVC" value="336">SVC</option>
		    <option label="SYP" value="337">SYP</option>
		    <option label="SZL" value="338">SZL</option>
		    <option label="THB" value="339">THB</option>
		    <option label="TND" value="340">TND</option>
		    <option label="TOP" value="341">TOP</option>
		    <option label="TPE" value="342">TPE</option>
		    <option label="TRY" value="343">TRY</option>
		    <option label="TTD" value="344">TTD</option>
		    <option label="TWD" value="345">TWD</option>
		    <option label="TZS" value="346">TZS</option>
		    <option label="UGX" value="347">UGX</option>
		    <option label="UYU" value="349">UYU</option>
		    <option label="VEF" value="350">VEF</option>
		    <option label="VND" value="351">VND</option>
		    <option label="VUV" value="352">VUV</option>
		    <option label="WST" value="353">WST</option>
		    <option label="XAF" value="354">XAF</option>
		    <option label="XAG" value="355">XAG</option>
		    <option label="XAU" value="356">XAU</option>
		    <option label="XCD" value="357">XCD</option>
		    <option label="XDR" value="358">XDR</option>
		    <option label="XOF" value="359">XOF</option>
		    <option label="XPD" value="360">XPD</option>
		    <option label="XPF" value="361">XPF</option>
		    <option label="XPT" value="362">XPT</option>
		    <option label="YDD" value="363">YDD</option>
		    <option label="YER" value="364">YER</option>
		    <option label="YUD" value="365">YUD</option>
		    <option label="ZAR" value="366">ZAR</option>
		    <option label="ZMK" value="367">ZMK</option>
		    <option label="ZRZ" value="368">ZRZ</option>
		    <option label="ZWD" value="369">ZWD</option>
		    <option label="SKK" value="370">SKK</option>
		    <option label="AMD" value="371">AMD</option>
			</select>
		</p>
		<h3 class="theme1" style="border-top: 1px solid #aaaaaa; padding-top: 1em;">Location & Reference</h3>
		<p>
			<!-- <label class="theme1" style="display: table;" for="clubInput">Club or location name</label> -->
			<h4 class="theme1" style="display: table;">Club or location name</h4>
			<input class="theme1" style="width: 300px;" type="text" name="club" id="clubInput" />
			<span id="clubErr" class="error" ></span>
		</p>
		<p>
			<div style="vertical-align: top; display: inline-block; width: 405px;">
				<input class="theme1" type="text" name="geolocation" id="geolocationInput" style="width: 400px;"/>
				<label id="addressErr" for="geolocationInput" class="error"></label>
				<input type="hidden" name="addressTypes" id="addressTypesInput" />
			</div>
			<div style="width: 400px; height: 200px; border: 1px solid #aaaaaa; margin-top: 5px;">
				<img id="locationStaticMap" src=""/>
			</div>
		</p>
		<p>
			<input type="checkbox" name="agreesToTerms" id="agreesToTermsInput" checked="checked" />
			<label class="theme1" for="agreesToTermsInput" id="agreesToTermsLabel">I have read and agree to the  <a class="theme1" href='<c:url value="/pages/legal" />'>Terms &amp; Conditions</a> and to the <a class="theme1" href='<c:url value="/pages/legal" />'>Privacy Policy</a></label>
			<span id="agreesToTermsErr" class="errorLabel"></span>
		</p>
		
		
		<!-- address -->
		<input id="latitude" type="hidden" name="latitude" value="0" />
		<input id="longitude" type="hidden" name="longitude" value="0" />
		
		<input id="street_number" type="hidden" name="streetNumber" value="" />
		<input id="route" type="hidden" name="route" value="" />
		<input id="locality" type="hidden" name="locality" value="" />
		<input id="political" type="hidden" name="political" value="" />
		<input id="administrative_area_level_2" type="hidden" name="administrativeAreaLevel2" value="" />
		<input id="administrative_area_level_1" type="hidden" name="administrativeAreaLevel1" value="" />
		<input id="country" type="hidden" name="country" value="" />
		<input id="postal_code" type="hidden" name="postalCode" value="" />
		
		<input id="street_number_short" type="hidden" name="streetNumberShortName" value="" />
		<input id="route_short" type="hidden" name="routeShortName" value="" />
		<input id="locality_short" type="hidden" name="localityShortName" value="" />
		<input id="political_short" type="hidden" name="politicalShortName" value="" />
		<input id="administrative_area_level_2_short" type="hidden" name="administrativeAreaLevel2ShortName" value="" />
		<input id="administrative_area_level_1_short" type="hidden" name="administrativeAreaLevel1ShortName" value="" />
		<input id="country_short" type="hidden" name="countryShortName" value="" />
		<input id="postal_code_short" type="hidden" name="postalCodeShortName" value="" />
		<!-- address END -->
		<button class="pushButton" type="button" id="submitButton" onclick="submitForm()">Create Profile</button>
	</form>
	</div>
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
	<st:footer />
</body>

</html>