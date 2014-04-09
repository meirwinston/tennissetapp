<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="/WEB-INF/tld/simple-tags.tld" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value='/resources/css/global.css' />" />
	<link rel="stylesheet" href="<c:url value='/resources/css/profile/create-account.css' />" />
	<link rel="stylesheet" href="<c:url value='/resources/js/jquery-ui-1.10.3/themes/base/jquery.ui.all.css' />">
	
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/jquery-1.9.1.js' />"></script>
	
	<!-- JQuery UI -->
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.core.js' />"></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.widget.js' />"></script>
	<%-- <script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.mouse.js' />"></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.button.js' />"></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.draggable.js' />"></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.position.js' />"></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.resizable.js' />"></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.button.js' />"></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.dialog.js' />"></script> --%>
	
	<script src="<c:url value='/resources/js/jquery-plugins/bday-picker.js' />"></script>
	<script src="<c:url value='/resources/js/tennissetapp/global.js' />"></script>
	<script src="<c:url value='/resources/js/tennissetapp/profile/create-account.js' />"></script>
	
	<!-- ////////File Upload////////// -->
	
	<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<!-- <script src="<c:url value='/resources/js/jQuery-File-Upload-8.5.0/js/vendor/jquery.ui.widget.js' />"></script> -->

<!-- The File Upload image preview & resize plugin -->
<%-- <script src="<c:url value='/resources/js/jQuery-File-Upload-8.5.0/js/jquery.fileupload-image.js' />"></script> --%>

<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="<c:url value='/resources/js/jQuery-File-Upload-8.5.0/js/jquery.iframe-transport.js' />"></script>
<!-- The basic File Upload plugin -->
<script src="<c:url value='/resources/js/jQuery-File-Upload-8.5.0/js/jquery.fileupload.js' />"></script>

<script src="<c:url value='/resources/js/jQuery-File-Upload-8.5.0/js/jquery.iframe-transport.js' />"></script>
<!-- The File Upload processing plugin -->
<script src="<c:url value='/resources/js/jQuery-File-Upload-8.5.0/js/jquery.fileupload-process.js' />"></script>

<!-- The File Upload validation plugin -->
<script src="<c:url value='/resources/js/jQuery-File-Upload-8.5.0/js/jquery.fileupload-validate.js' />"></script> 



<!-- The File Upload audio preview plugin -->
<!-- <script src="<c:url value='/resources/js/jQuery-File-Upload-8.5.0/js/jquery.fileupload-audio.js' />"></script> -->
<!-- The File Upload video preview plugin -->
<!-- <script src="<c:url value='/resources/js/jQuery-File-Upload-8.5.0/js/jquery.fileupload-video.js' />"></script> -->
<!-- The File Upload Angular JS module -->
<!-- <script src="<c:url value='/resources/js/jQuery-File-Upload-8.5.0/js/jquery.fileupload-angular.js' />"></script> -->

<!-- ////////File Upload END////////// -->

<title>Create Account</title>
</head>
<body>
	<st:header />
	<div id="centerPane">
	<form id="createAccountForm" method="POST" action='account/create'>
		<h2 class="theme1">Profile Type</h2>
		<input type="hidden" name="userAccountId" value="${userAccountId}"/>
		<p>
		<select class="theme1" name="profileType" id="profileTypeInput">
			<option value="TENNIS_PLAYER">Tennis Player</option>
			<option value="TENNIS_TEACHER">Tennis Teacher</option>
		</select>
		</p>
		<h2 class="theme1">Profile Information</h2>
		
		<p>
		<label class="theme1" for="profileInfo.firstName">First Name</label><br/> 
		<input style="text-transform: capitalize;" class="theme1" type="text" id="profileInfo.firstName" name="firstName" value="${firstName}" placeholder="First Name" />
		<span id="firstNameErr" class="errorLabel"></span>
		</p>
		
		<p>
		<label class="theme1" for="profileInfo.lastName">Last Name</label><br/>
		<input style="text-transform: capitalize;" class="theme1" type="text" id="profileInfo.lastName" name="lastName" value="${lastName}" placeholder="Last Name" />
		<span id="lastNameErr" class="errorLabel"></span>
		</p>
		
		<p>
		<label class="theme1" for="genderInput">Gender</label><br/>
		<select class="theme1" id="genderInput" name="gender">
			<!-- <option value="">Unspecified</option> -->
			<option value="MALE">Male</option>
			<option value="FEMALE">Female</option>
		</select>
		<span id="genderErr" class="errorLabel"></span>
		</p>
		
		<p>
			<label class="theme1" for="profileInfo.gender" style="display: table;">Birth Date<span id="birthDayErr" class="errorLabel"></span></label>
			<span id="birthdateInput"></span>
			<span class="inputNote">We won't show your age to public</span>
		</p>
		
		<p>
			<%-- <img id="profileImage" src="<c:url value='/resources/images/tennis-player-icon_178x178.png' />" /> --%>
			<c:if test="${profilePhotoUrl == null}">
				<img id="profileImage" src="<c:url value='/resources/images/nophoto_user_thumb_profile_218x218.png' />" />
			</c:if>
			<c:if test="${profilePhotoUrl != null}">
				<img id="profileImage" src="${profilePhotoUrl}" />
			</c:if>
			
			<span class="btn btn-success fileinput-button">
	        <!-- <i class="icon-plus icon-white"></i> -->
	        <span>Profile Photo...</span>
	        <!-- The file input field used as target for the file upload widget -->
	        <input class="theme1" id="fileupload" type="file" name="files[]" multiple>
	    </span>
		</p>
		<p>
			<input type="checkbox" name="agreesToTerms" /><span id="agreesToTermsLabel">I have read and agree to the  Terms &amp; Conditions and to the Privacy Policy</span>
			<span id="agreesToTermsErr" class="errorLabel"></span>
		</p>
		
		<!-- image -->
		<input id="profileFileItemIdInput" type="hidden" name="profileFileItemId" value="" />
		
		<!-- address -->
		<st:address-form />
		<button class="theme1" type="button" id="submitButton" onclick="submitForm()">Continue</button>
	</form>
	</div>
	<st:footer />
	<script>
	$(document).ready(function(){
		$("#genderInput").find("option[value='${gender}']").attr("selected","selected");
		
		var yearSelect = $('#birthdateInput').find("select[name='birthYear']")[0];
		var monthSelect = $('#birthdateInput').find("select[name='birthMonth']")[0];
		var daySelect = $('#birthdateInput').find("select[name='birthDay']")[0];
		//console.log("create-account.ready() ${birthYear} , ${birthMonth} , ${birthDay}");
		var birthYear = parseInt("${birthYear}");
		var birthMonth = parseInt("${birthMonth}");
		var birthDay = parseInt("${birthDay}");
		$(yearSelect).find('option[value='+ birthYear + ']').attr("selected","selected");
		$(monthSelect).find('option[value=' + birthMonth + ']').attr("selected","selected");
		$(daySelect).find('option[value=' + birthDay + ']').attr("selected","selected");
		
	});
	</script>
	<%-- <input type="hidden" value="<c:url value='/profile/player/complete?userId=${userAccountId }' />" id="completeUrlInput" /> --%>
</body>

</html>