<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="/WEB-INF/tld/simple-tags.tld" %>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<c:url value='/resources/css/login.css' />">
<script src="<c:url value='/resources/js/jquery-ui-1.10.3/jquery-1.9.1.js' />"></script>
<script src="<c:url value='/resources/js/tennissetapp/login.js' />"></script>
<title>Login</title>
</head>
<body>
	<st:header page="login" />
	
	<div id="showcase" style="padding-top: 50px;">
		<div><img src="<c:url value='/resources/images/splash_icons_973x365.jpg' />" /></div>
	</div>
	<div>
	<form id="loginForm" method="POST" action='j_spring_security_check'>
		<table id="formBox">
			<tr>
				<td id="formHeaderCell">LOGIN</td>
			</tr>
			<tr>
				<td class="inputWrapper">
					<input type="email" placeholder="E-mail address" autofocus="autofocus" tabindex="1" value="" id="emailInput" name="j_username">
				</td>
			</tr>
			<tr>
				<td class="inputWrapper">
					<input type="password" placeholder="Password" tabindex="2" value="" id="passwordInput" name="j_password">
				</td>
			</tr>
			<tr>
				<td id="checkboxWrapper">
					<input type="checkbox"><span>Keep me logged in</span>
				</td>
			</tr>
			<tr>
				<td id="submitButtonWrapper">
					<button type="submit" id="submitButton">Login</button>
				</td>
			</tr>
			<tr>
				<td id="orCell"></td>
			</tr> 
			<tr>
				<td style="text-align: center;">
					<button id="facebookSigninButton" type="button" onclick="facebookSignin()">Login with Facebook</button>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<button id="twitterSigninButton" type="button" onclick="twitterSignin()">Login with Twitter</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
	
	<br/><br/>
	<form id="logoutForm" method="POST" action='j_spring_security_logout'>
		<button type="submit">Log Out</button>
	</form>
	
	
	<script>
	function twitterSignin(){
		var form = $("form#loginForm");
		console.log("signin with twitter ");
		console.log(form);
		form.attr("action","<c:url value='/signin/twitter'/>");
		form.submit();
	}

	function facebookSignin(){
		var form = $("form#loginForm");
		form.attr("action","<c:url value='/signin/facebook'/>");
		form.submit();
	}
	</script>
</body>

</html>