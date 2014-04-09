<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="/WEB-INF/tld/simple-tags.tld" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value='/resources/css/global.css' />" />
	<link rel="stylesheet" href="<c:url value='/resources/js/jquery-ui-1.10.3/themes/base/jquery.ui.all.css' />">
	
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/jquery-1.9.1.js' />"></script>
<title>Account Created</title>
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
	width: 55%;
}
</style>
</head>
<body>
	<st:header />
	<table id="layoutTable">
	<tbody>
	<tr>
		<td>
			<c:if test="${profileImageUrl != null }">
				<img src="${profileImageUrl}" />
			</c:if>
			<c:if test="${profileImageUrl == null }">
				<img src="<c:url value='/resources/images/nophoto_user_thumb_profile_218x218.png' />" />
			</c:if>
			
			<div style="text-align: center; margin-top: 0.5em;">
				<button type="button" class="theme1">Edit Info</button>
				<button type="button" class="theme1">Edit Photo</button>
			</div>
		</td>
		<td id="centerPane">
		<h2 class="theme1">Meir Winston</h2>
		<ul id="profileDetailsList" class="theme1-font-normal">
			<li style="list-style-image: url(<c:url value='/resources/images/place_14x18.png' />)">
				${address}
			</li>
			<li style="list-style-image: url(<c:url value='/resources/images/level_18x14.png' />)">
				${levelOfPlay}
			</li>
			<li style="list-style-image: url(<c:url value='/resources/images/type_of_game_12x20.png' />)">
				${typeOfGame}
			</li>
			<li style="list-style-image: url(<c:url value='/resources/images/best_availability_14x14.png' />)">
				${availability }
			</li>
		</ul>
		</td>
		<td style="background-color: #EEEEEE; width: 200px;">RIGHT</td>
	</tr>
	<tr>
		<td colspan="3">Bottom Pane</td>
	</tr>
	</tbody>
	</table>
	<st:footer />
</body>
</html>