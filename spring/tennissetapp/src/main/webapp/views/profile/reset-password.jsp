<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="/WEB-INF/tld/simple-tags.tld" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value='/resources/css/global.css' />" />
	<link rel="stylesheet" href="<c:url value='/resources/css/profile/reset-password.css' />" />
	<link rel="stylesheet" href="<c:url value='/resources/js/jquery-ui-1.10.3/themes/base/jquery.ui.all.css' />">
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/jquery-1.9.1.js' />"></script>
	<%-- <script src="<c:url value='/resources/js/jquery-plugins/jquery.validate.js' />"></script> --%>
	<script src="<c:url value='/resources/js/tennissetapp/profile/reset-password.js' />"></script>
<title>Tennis SetApp - Welcome</title>
</head>
<body>
	<st:header />
	<div id="centerPane">
		
		<div class="formPane">
		<span>Welcome to tennis SetApp, before we continue , please reset your password</span>
		<form id="resetPasswordForm" method="post" action="<c:url value='/service/profile/resetpassword' />" >
		<fieldset>
			<legend id="formLegend">Create New Password</legend>
			<div id="errorPane" style="text-align: center;"></div>
			<input id="tocketInput" name="token" type="hidden" value="${token}" />
			<table id="formTable">
				<tr>
				<td><label for="passwordInput">Password</label></td>
				<td><input id="passwordInput" name="password" type="password" /></td>
				</tr>
				<tr>
				<td><label for="confirmPasswordInput">Confirm password</label></td>
				<td><input id="confirmPasswordInput" name="confirmPassword" type="password" /></td>
				</tr>
				<tr>
				<td></td>
				<td><input class="pushButton" type="button" onclick="submitForm()" value="Submit" /></td>
				</tr>
			</table>
		</fieldset>
		</form>
		</div>
	</div>
</body>
</html>