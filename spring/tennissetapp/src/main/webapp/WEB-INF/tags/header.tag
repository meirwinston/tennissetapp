<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="TennisSetApp header" pageEncoding="UTF-8"%>
<%@attribute name="id"%>
<%@attribute name="page"%>

<style>		 
#header_container{
	background-color: #525252;
	/* background-image: linear-gradient(to bottom, #525252, #3D3D3D); */
	
	border-collapse: collapse;
	border-spacing: 0;
	color: white;
	width: 100%;
	display: table;
}
#header_container > div{
	display: table-cell;
}

#header_logoCell{
	background-image: url("/tennissetapp/resources/images/tennissetapp_logo_444x32.png");
	background-position: 10em center;
	background-repeat: no-repeat;
	height: 50px;
	width: 584px;
	display: table-cell;
}	

#header_button{
	background-color: #2EB0B9;
	color: white;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	white-space: nowrap;
	width: 100px;
	vertical-align: middle;
}

#mainToolbar a{
	text-decoration: none;
	color: grey;
}

#mainToolbar a:hover{
	color: #1ABDC4;
}

</style>

<script>
var rootDomain =  "<c:url value='/' />";

function initMenuBar(){
	function expandMenu(){
		$(this).find("ul").each(function(){
			$(this).css("display","block");
		});
	}
	function collapseMenu(){
		$(this).find("ul").each(function(){
			$(this).css("display","none");
		});
	}
	$("#mainToolbar").css("list-style","none outside none");
	$("#mainToolbar").css("margin","0.9% 13% 0");
	
	$("#mainToolbar > li").each(function(){
		$(this).css("float","left");
		$(this).css("margin-left","2em");
		$(this).css("margin-right","2em");
		
		$(this).hover(expandMenu,collapseMenu);
		
		$(this).find("ul").each(function(){
			$(this).css("position","absolute");
			$(this).css("padding","0em 1em 1em 1em");
			$(this).css("background","none repeat scroll 0 0 #F4F3F1");
			$(this).css("display","none");
			$(this).css("list-style","none outside none");
		});
	});
	
	$("#mainToolbar > li > ul > li ").each(function(){
		$(this).css("padding-top","1em");
	});
}

</script>
<script src="<c:url value='/resources/js/tennissetapp/global.js' />"></script>
<div style="position: fixed; width: 100%;">
<div id="header_container">
	<div id="header_logoCell"></div>
	<div>&nbsp;</div>
	<c:if test="${page != 'login'}">
		<div id="header_button">LOGIN</div>
	</c:if>
	<div>&nbsp;</div>
</div>
<c:if test="${page != 'login' }">
<div style="background: none repeat scroll 0 0 #F4F3F1;border-bottom: 1px solid #F3F3F3; height: 41px; display: inline-block; width: 100%;">
	<ul class="menubar" id="mainToolbar">
		<li>
			<a href="<c:url value='/home' />" >HOME</a>
		</li>
		<li>
			<a href="<c:url value='/profile' />" >PROFILE</a>
		</li>
		<li>
			<a href="<c:url value='/mates' />" >MATES</a>
			<ul id="matesMenu">
				<li><a href="<c:url value='/mates' />">Find a tennis mate</a></li>
				<li><a href="<c:url value='/mates' />">Favorites</a></li>
			</ul>
		</li>
		<li>
			<a href="<c:url value='/matches' />">MATCHES</a>
			<ul>
				<li><a href="<c:url value='/matches' />">Find a tennis match</a></li>
				<li><a href="<c:url value='/matches' />">Create a tennis match</a></li>
			</ul>
		</li>
		<li>
			<a href="<c:url value='/teachers' />">TEACHERS</a>
			<ul>
				<li><a href="<c:url value='/teachers' />">Find a tennis teacher</a></li>
				<li><a href="<c:url value='/teachers' />">Favorites</a></li>
			</ul>
		</li>
		<li>
			<a href="<c:url value='/courts' />">COURTS</a>
			<ul>
				<li><a href="<c:url value='/courts' />">Find a tennis court</a></li>
				<li><a href="<c:url value='/courts/create'  />">Create a tennis court</a></li>
			</ul>
		</li>
		<li>
			<a href="<c:url value='/pages/about' />" >ABOUT</a>
		</li>
	</ul>
</div>
</c:if>

</div>
 
<script>
$(function() {
	console.log("constructing menubar");
	initMenuBar();
});
</script>
