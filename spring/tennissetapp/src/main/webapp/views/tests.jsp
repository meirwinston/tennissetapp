<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Home</title>

	<link rel="stylesheet" href="resources/js/jquery-ui-1.10.3/themes/base/jquery.ui.all.css">
	<script src="resources/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
	<script src="resources/js/jquery-ui-1.10.3/ui/jquery.ui.core.js"></script>
	<script src="resources/js/jquery-ui-1.10.3/ui/jquery.ui.widget.js"></script>
	<script src="resources/js/jquery-ui-1.10.3/ui/jquery.ui.position.js"></script>
	<script src="resources/js/jquery-ui-1.10.3/ui/jquery.ui.menu.js"></script>
	<script src="resources/js/jquery-ui-1.10.3/ui/jquery.ui.autocomplete.js"></script>
	<link rel="stylesheet" href="resources/js/jquery-ui-1.10.3/demos.css">
	<style>
	.ui-autocomplete-loading {
		background: white url('images/ui-anim_basic_16x16.gif') right center no-repeat;
	}
	#city { width: 25em; }
	</style>
	<script src="resources/js/tennissetapp/home.js" ></script>
	
</head>
<body>

<div class="ui-widget">
	<label for="city">Your city: </label>
	<input id="city" />
</div>

<br/><br/>------------------<br/><br/>

<form id="signin" action="/signin/authenticate" method="post">
	<fieldset>
		<label for="login">Username</label>
		<input id="login" name="j_username" type="text" size="25" />
		<label for="password">Password</label>
		<input id="password" name="j_password" type="password" size="25" />	
	</fieldset>
	<button type="submit">Sign In</button>
</form>

	<!-- TWITTER SIGNIN -->
	<form id="tw_signin" action="<c:url value='signin/twitter'/>" method="POST">
		<button type="submit">sign in with twitter</button>
	</form>

	<!-- FACEBOOK SIGNIN -->
	<form name="fb_signin" id="fb_signin" action="signin/facebook" method="POST">
        <input type="hidden" name="scope" value="publish_stream,user_photos,offline_access,email" />
		<button type="submit">Sign in with facebook</button>
	</form>
	
	<form action="<c:url value="connect/facebook" />" method="POST">
	<input type="hidden" name="scope" value="publish_stream,user_photos,offline_access" />
		<div class="formInfo">
			<p>You aren't connected to Facebook yet. Click the button to connect Spring Social Showcase with your Facebook account.</p>
		</div>
		<p><button type="submit">Connect With Facebook</button></p>
		<label for="postToWall"><input id="postToWall" type="checkbox" name="postToWall" /> Tell your friends about Spring Social Showcase on your Facebook wall</label>
	</form>

	<!-- LINKEDIN SIGNIN -->
	<form name="li_signin" id="li_signin" action="/signin/linkedin" method="POST">
		<button type="submit">Sign In with LinkedIn</button>
	</form>
	<br/><br/>--Persistence--<br/><br/>
	<form name="newUserAccount" id="newUserAccount" action="useraccount/create" method="POST">
    <input type="text" name="username" value="<%=java.util.UUID.randomUUID().toString() %>"/>
    <input type="text" name="password" value="<%=java.util.UUID.randomUUID().toString() %>"/>
    <input type="text" name="email" value="meirwinston@yahoo.com"/>
		<button type="submit">New User Account</button>
	</form>
	
</body>
</html>