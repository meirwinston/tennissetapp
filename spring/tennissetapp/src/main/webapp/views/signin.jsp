<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login</title>

	
</head>
<body>

<form id="signup.loginForm" method="GET" action='j_spring_security_check'>
	<input type="text" name="j_username" id="j_username"  value="meirwinston@yahoo.com"/>
	<input type="text" name="j_password" id="j_password" value="pass"/>
	<button type="submit">Log In</button>
</form>
<br/><br/>
<form id="signup.loginForm" method="POST" action='j_spring_security_logout'>
	<button type="submit">Log Out</button>
</form>
</body>
</html>