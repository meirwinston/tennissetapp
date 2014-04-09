<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="/WEB-INF/tld/simple-tags.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Home</title>
	<link rel="stylesheet" href="<c:url value='/resources/js/jquery-ui-1.10.3/themes/base/jquery.ui.all.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/global.css'/>">
	
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/jquery-1.9.1.js' />"></script>
	<script src="<c:url value='/resources/js/tennissetapp/home.js' />" ></script>
	
	<!-- menubar -->
	<%-- <script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.core.js' />" ></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.widget.js' />" ></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.widget.js' />" ></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.position.js' />" ></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.button.js' />" ></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.menu.js' />" ></script> --%>
	
</head>
<body>
	<st:header />
	<st:menubar />
	
	<br/>
	<!-- END -->
	<form id="postForm" action="<c:url value='/service/post' />" method="post">
		<textarea id="userPostInput" name="content" placeholder="Write somthing..."></textarea>
		<button type="button" onclick="submitPost()">Post</button>
	</form>
</body>
</html>