<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="/WEB-INF/tld/simple-tags.tld" %>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<c:url value='/resources/css/signup.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/global.css' />">
<script src="<c:url value='/resources/js/jquery-ui-1.10.3/jquery-1.9.1.js' />"></script>
<script src="<c:url value='/resources/js/tennissetapp/Services.js' />"></script>
<script src="<c:url value='/resources/js/tennissetapp/signup.js' />"></script>
<title>Sign Up</title>
</head>
<body>
	<st:header />
	<div>
	<form id="signupForm" method="POST" action='signup'>
		<table id="formBox">
			<tr>
				<td id="formHeaderCell">SIGNUP</td>
			</tr>
			<tr>
				<td class="inputWrapper">
					<input type="text" placeholder="E-mail address" autofocus="autofocus" tabindex="1" value="meirwinston@yahoo.com" id="emailInput" name="email">
				</td>
			</tr>
			<tr>
				<td class="inputWrapper">
					<input type="password" placeholder="Password" tabindex="2" value="111111" id="passwordInput" name="password">
				</td>
			</tr>
			<tr>
				<td class="inputWrapper">
					<input type="password" placeholder="Confirm your password" tabindex="2" value="111111" id="retypePasswordInput" name="confirmPassword">
				</td>
			</tr>
		
			<tr>
				<td id="formErr" class="inputWrapper" style="color: red;"></td>
			</tr>
		
			<tr>
				<td class="submitButtonWrapper">
						<button tabindex="3" type="button" id="submitButton" onclick="signup()">Join</button>
				</td>
			</tr>
			<tr>
				<td id="orCell"></td>
			</tr>
			<tr>
				<td style="text-align: center; padding-top: 2em;">
					<%-- <button id="facebookSignupButton" type="button" onclick="facebookSignup()"><img src='<c:url value="/resources/images/signup_fb_292x44.png" />' /></button> --%>
					<img id="facebookSignupButton" onclick="facebookSignup()" src='<c:url value="/resources/images/signup_fb_292x44.png" />' />
				</td>
			</tr>
			<!-- <tr>
				<td style="text-align: center;">
					<button id="twitterSignupButton" type="button" onclick="twitterSignup()">Sign up with Twitter</button>
				</td>
			</tr> -->
			<tr>
				<td style="color: #898989; text-align: center; font-size: 0.9em; height: 6em;">I accept Tennis SetApp's<br/>
				<a class="theme1" href='<c:url value="/pages/legal" />'>Terms & Conditions</a> and <a class="theme1" href='<c:url value="/pages/legal" />'>Privacy Policy</a>
				</td>
			</tr>
			<tr><td style="background-color: white; height: 1px;"></td></tr>
			<tr>
				<td style="text-align: center; font-size: 0.9em; height: 3em;" >ALREADY A MEMBER? <a class="theme1" href="login">LOGIN HERE</a></td>
			</tr>
		</table>
	</form>
	</div>
</body>

</html>