<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="/WEB-INF/tld/simple-tags.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tennis SetApp - The smart way to meet and play - Court Browse</title>
	<link rel="stylesheet" href="<c:url value='/resources/js/jquery-ui-1.10.3/themes/base/jquery.ui.all.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/global.css'/>">
	
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/jquery-1.9.1.js' />"></script>
	<script src="<c:url value='/resources/js/tennissetapp/courts/browse.js' />" ></script>
	<script src="<c:url value='/resources/js/Pagination.js' />" ></script>
	<!-- menubar -->
	<%-- <script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.core.js' />" ></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.widget.js' />" ></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.widget.js' />" ></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.position.js' />" ></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.button.js' />" ></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.3/ui/jquery.ui.menu.js' />" ></script> --%>
	<style>
		#courtsList{
			list-style-type: none;
			margin: 0;
			padding: 0;
		}
		
		#courtsList > li{
			float: left;
			height: 12em;
			padding-top: 2em;
			border-bottom: 1px solid #eeeeee;
		}
		#courtsList > li > div{
			float: left;
			margin-right: 1em;
		}
		#courtsList > li > div:nth-child(2){
			width: 300px;
		}
		#courtsList > li > div > img{
			width: 115px;
			height: 115px;
		}
		#courtsList > li > div > p{
			color: #989797;
			font-size: 10pt;
			font-weight: 300;
		}
		#layoutTable{
			width: 100%;
			margin-left: auto;
			margin-right: auto;
		}
	</style>
	
	<!-- header style -->
		
</head>
<body>
	<!-- start of header -->
	<st:header page="login"/>
	
	<!-- end of header -->
	
	
	<table id="layoutTable" style="padding-top: 100px;">
	<tr>
	<td style="width: 150px;">
		LEFT
	</td>
	<td>
		<h2 class="theme1" style="padding-bottom: 1em;">Tennis Courts</h2>
		<small class="theme1">Here are some courts in your area. You can also add a new court to our site here.</small>
		
		<ul id="courtsList">
			<li>
				<div>
					<img src="http://www.tennissetapp.com/public/court/07/09/08fe_8e9f.png?c=acb2">
				</div>
				<div>
					<h2 class="theme1">Wolfes Pond Park</h2>
					 <p>718-667-3545</p>
					 <p>Cornelia Ave & Highland Blvd, New York, United States<p/>
					 <p>Outdoor: Hard (2)</p> 
				</div>
			</li>
			
			<li>
				<div>
					<img src="http://www.tennissetapp.com/public/court/07/09/08fe_8e9f.png?c=acb2">
				</div>
				<div>
					<h2 class="theme1">Wolfes Pond Park</h2>
					 <p>718-667-3545</p>
					 <p>Cornelia Ave & Highland Blvd, New York, United States<p/>
					 <p>Outdoor: Hard (2)</p> 
				</div>
			</li>
			
			<li>
				<div>
					<img src="http://www.tennissetapp.com/public/court/07/09/08fe_8e9f.png?c=acb2">
				</div>
				<div>
					<h2 class="theme1">Wolfes Pond Park</h2>
					 <p>718-667-3545</p>
					 <p>Cornelia Ave & Highland Blvd, New York, United States<p/>
					 <p>Outdoor: Hard (2)</p> 
				</div>
			</li>
			
			<li>
				<div>
					<img src="http://www.tennissetapp.com/public/court/07/09/08fe_8e9f.png?c=acb2">
				</div>
				<div>
					<h2 class="theme1">Wolfes Pond Park</h2>
					 <p>718-667-3545</p>
					 <p>Cornelia Ave & Highland Blvd, New York, United States<p/>
					 <p>Outdoor: Hard (2)</p> 
				</div>
			</li>
		</ul>
	</td>
	</tr>
	<tr>
		<td></td>
		<td id="paginationCell"></td>
	</tr>
	</table>



<!-- TESTTTTTTTTTTTTTTTTTTTTTTT -->
	<input type="text" id="geolocationInput"></input>
	<!-- address -->
	<form id="storeAddressForm">
		<input id="latitude" type="hidden" name="latitude" value="0" />
		<input id="longitude" type="hidden" name="longitude" value="0" />
		
		<input id="street_number" type="hidden" name="streetNumber" value="" />
		<input id="route" type="hidden" name="route" value="" />
		<input id="locality" type="hidden" name="locality" value="" />
		<input id="sublocality" type="hidden" name="locality" value="" />
		<input id="political" type="hidden" name="political" value="" />
		<input id="administrative_area_level_2" type="hidden" name="administrativeAreaLevel2" value="" />
		<input id="administrative_area_level_1" type="hidden" name="administrativeAreaLevel1" value="" />
		<input id="country" type="hidden" name="country" value="" />
		<input id="postal_code" type="hidden" name="postalCode" value="" />
		
		<input id="street_number_short" type="hidden" name="streetNumberShortName" value="" />
		<input id="route_short" type="hidden" name="routeShortName" value="" />
		<input id="locality_short" type="hidden" name="localityShortName" value="" />
		<input id="sublocality_short" type="hidden" name="locality" value="" />
		<input id="political_short" type="hidden" name="politicalShortName" value="" />
		<input id="administrative_area_level_2_short" type="hidden" name="administrativeAreaLevel2ShortName" value="" />
		<input id="administrative_area_level_1_short" type="hidden" name="administrativeAreaLevel1ShortName" value="" />
		<input id="country_short" type="hidden" name="countryShortName" value="" />
		<input id="postal_code_short" type="hidden" name="postalCodeShortName" value="" />
		
		<input type="hidden" name="addressTypes" id="addressTypesInput" />
	</form>
		<!-- address END -->
	<br/><br/><br/><br/>
	<!-- Geoautocomplete Google Maps -->
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places"></script>
	<script src="<c:url value='/resources/js/jquery-plugins/jquery.geocomplete.js' />"></script>
	
	<script>
	var arr = [
{latitude:-23.6817424,longitude:-46.7097809},
{latitude:-19.9252565,longitude:-43.934682699999},
{latitude:23.7642945,longitude:90.42876},
{latitude:25.6913771,longitude:-80.2849225},
{latitude:25.7255797,longitude:-80.2435376},
{latitude:25.7364997,longitude:-80.218363599999},
{latitude:25.7415184,longitude:-80.276946899999},
{latitude:25.7463307,longitude:-80.1893175},
{latitude:25.7611406,longitude:-80.231928999999},
{latitude:25.768197,longitude:-80.193129},
{latitude:25.7802919,longitude:-80.162955899999},
{latitude:25.7818629,longitude:-80.137375599999},
{latitude:25.7858303,longitude:-80.201365899999},
{latitude:25.793797,longitude:-80.235978999999},
{latitude:25.7938696,longitude:-80.186304899999},
{latitude:25.7947567,longitude:-80.262296499999},
{latitude:25.808705,longitude:-80.186794999999},
{latitude:25.8100649,longitude:-80.207653999999},
{latitude:25.8134551,longitude:-80.1865369},
{latitude:25.8156733,longitude:-80.135671899999},
{latitude:25.819406,longitude:-80.237063999999},
{latitude:25.8208668,longitude:-80.219599},
{latitude:25.8250483,longitude:-80.230676799999},
{latitude:25.8269946,longitude:-80.180550999999},
{latitude:25.8284361,longitude:-80.272765699999},
{latitude:25.8287986,longitude:-80.2491261},
{latitude:25.831368,longitude:-80.227162},
{latitude:25.8333275,longitude:-80.203889399999},
{latitude:25.8356629,longitude:-80.182426},
{latitude:25.846877,longitude:-80.228774},
{latitude:25.8570662,longitude:-80.123720499999},
{latitude:25.8596192,longitude:-80.2377078},
{latitude:25.8655349,longitude:-80.183333199999},
{latitude:25.8667622,longitude:-80.284603699999},
{latitude:25.8780477,longitude:-80.2875396},
{latitude:30.1686295,longitude:-97.808629699999},
{latitude:30.2274742999999,longitude:-97.7909958},
{latitude:30.2274742999999,longitude:-97.7909958},
{latitude:30.2366353,longitude:-97.789471899999},
{latitude:30.2366353,longitude:-97.789471899999},
{latitude:30.2385673,longitude:-97.6876295},
{latitude:30.2406307,longitude:-97.767949199999},
{latitude:30.2406307,longitude:-97.767949199999},
{latitude:30.2406307,longitude:-97.767949199999},
{latitude:30.2431237,longitude:-97.7511071},
{latitude:30.2512436,longitude:-97.7744208},
{latitude:30.2512436,longitude:-97.7744208},
{latitude:30.267153,longitude:-97.743060799999},
{latitude:30.267153,longitude:-97.743060799999},
{latitude:30.267153,longitude:-97.743060799999},
{latitude:30.267153,longitude:-97.743060799999},
{latitude:30.267153,longitude:-97.743060799999},
{latitude:30.267153,longitude:-97.743060799999},
{latitude:30.267153,longitude:-97.743060799999},
{latitude:30.267153,longitude:-97.743060799999},
{latitude:30.267153,longitude:-97.743060799999},
{latitude:30.267153,longitude:-97.743060799999},
{latitude:30.2704749999999,longitude:-97.714241},
{latitude:30.273597,longitude:-97.690926499999},
{latitude:30.2746009,longitude:-97.770085999999},
{latitude:30.2746009,longitude:-97.770085999999},
{latitude:30.2752887,longitude:-97.8130021},
{latitude:30.2775753,longitude:-97.750211299999},
{latitude:30.2776965,longitude:-97.757600599999},
{latitude:30.2779786,longitude:-97.8143327},
{latitude:30.286365,longitude:-97.7647392},
{latitude:30.286365,longitude:-97.7647392},
{latitude:30.2965869,longitude:-97.709588},
{latitude:30.3016655,longitude:-97.747253699999},
{latitude:30.3078462,longitude:-97.726781},
{latitude:30.3124,longitude:-97.655811},
{latitude:30.3141189,longitude:-97.6813336},
{latitude:30.3294046,longitude:-97.7604532},
{latitude:30.3370945,longitude:-97.741392599999},
{latitude:30.3444584,longitude:-97.7300209},
{latitude:30.3644899,longitude:-97.710188899999},
{latitude:31.9820486,longitude:-102.0920095},
{latitude:31.9832887,longitude:-102.1179943},
{latitude:32.6815558,longitude:-117.1753684},
{latitude:32.696141,longitude:-117.18948599999},
{latitude:32.700231,longitude:-117.10804289999},
{latitude:32.7056843,longitude:-96.854456299999},
{latitude:32.7209659,longitude:-117.15335829999},
{latitude:32.7211526,longitude:-117.2327764},
{latitude:32.7254013,longitude:-117.24474029999},
{latitude:32.735687,longitude:-97.108065599999},
{latitude:32.7399906,longitude:-117.22359419999},
{latitude:32.7503257,longitude:-117.1344955},
{latitude:32.7540562,longitude:-117.1388397},
{latitude:32.755215,longitude:-96.751099},
{latitude:32.7553984,longitude:-117.24044229999},
{latitude:32.7587608,longitude:-96.780393199999},
{latitude:32.7801399,longitude:-96.800451099999},
{latitude:32.7828739,longitude:-117.2502301},
{latitude:32.7907615,longitude:-117.1458637},
{latitude:32.792863,longitude:-117.1694521},
{latitude:32.797252,longitude:-96.7434905},
{latitude:32.8004297,longitude:-117.15939639999},
{latitude:32.8012513,longitude:-117.24520849999},
{latitude:32.8021463,longitude:-117.22009809999},
{latitude:32.80376,longitude:-117.1674636},
{latitude:32.806563,longitude:-96.750770999999},
{latitude:32.808861,longitude:-117.2022286},
{latitude:32.8120756,longitude:-96.7937878},
{latitude:32.8120769,longitude:-96.843548399999},
{latitude:32.8205487,longitude:-117.21412229999},
{latitude:32.8244918,longitude:-117.10412559999},
{latitude:32.8328923,longitude:-117.19620279999},
{latitude:32.83428,longitude:-117.2716113},
{latitude:32.8353952,longitude:-117.2121389},
{latitude:32.841225,longitude:-117.27788099999},
{latitude:32.8445841,longitude:-96.812969599999},
{latitude:32.8473359,longitude:-96.805387399999},
{latitude:32.8502676,longitude:-96.7899989},
{latitude:32.8586392,longitude:-96.789650199999},
{latitude:32.8588986,longitude:-96.8018384},
{latitude:32.8593671,longitude:-117.20678199999},
{latitude:32.8764713,longitude:-96.7437762},
{latitude:32.878062,longitude:-117.24267099999},
{latitude:32.8800886,longitude:-96.823945299999},
{latitude:32.8822411,longitude:-96.784812999999},
{latitude:32.8824546,longitude:-96.8433287},
{latitude:32.8863653,longitude:-96.724169299999},
{latitude:33.3534437,longitude:-111.98598579999},
{latitude:33.3705345,longitude:-112.05723089999},
{latitude:33.3722112,longitude:-112.1374215},
{latitude:33.3860466,longitude:-111.9851517},
{latitude:33.3903843,longitude:-112.06469979999},
{latitude:33.3997313,longitude:-112.0082909},
{latitude:33.4051234,longitude:-112.06018819999},
{latitude:33.4361077999999,longitude:-112.08045319999},
{latitude:33.4471683,longitude:-112.0481356},
{latitude:33.4687854,longitude:-112.05634099999},
{latitude:33.4703355,longitude:-111.9827232},
{latitude:33.4717051,longitude:-112.09135459999},
{latitude:33.4859996,longitude:-112.08899389999},
{latitude:33.4886737,longitude:-112.0563683},
{latitude:33.4943399,longitude:-86.845457099999},
{latitude:33.4984564,longitude:-112.0518202},
{latitude:33.5176609,longitude:-112.0955662},
{latitude:33.5296219,longitude:-112.10458679999},
{latitude:33.5476624,longitude:-112.1270901},
{latitude:33.662427,longitude:-84.444439999999},
{latitude:33.6661027,longitude:-84.360530999999},
{latitude:33.6829798,longitude:-84.4348547},
{latitude:33.7014063,longitude:-84.3826247},
{latitude:33.7041558,longitude:-84.355803199999},
{latitude:33.705537,longitude:-84.461619999999},
{latitude:33.7117263,longitude:-84.395076899999},
{latitude:33.7119982,longitude:-84.410956899999},
{latitude:33.7181173,longitude:-84.386911899999},
{latitude:33.7189242,longitude:-84.3749851},
{latitude:33.7191954,longitude:-84.3880085},
{latitude:33.7195026,longitude:-84.42786},
{latitude:33.7336854,longitude:-84.447485299999},
{latitude:33.7366207,longitude:-84.3841022},
{latitude:33.7380033,longitude:-84.347247799999},
{latitude:33.7396187,longitude:-84.361792799999},
{latitude:33.7409904,longitude:-84.3983023},
{latitude:33.741183,longitude:-84.374861799999},
{latitude:33.7422761,longitude:-84.4239233},
{latitude:33.7441336,longitude:-84.769631699999},
{latitude:33.7441336,longitude:-84.769631699999},
{latitude:33.745087,longitude:-84.328111999999},
{latitude:33.7456189,longitude:-84.380479499999},
{latitude:33.7462247,longitude:-84.339764199999},
{latitude:33.747166,longitude:-84.3013869},
{latitude:33.7479932,longitude:-84.4011143},
{latitude:33.7524779,longitude:-84.355608599999},
{latitude:33.752512,longitude:-84.420556499999},
{latitude:33.7527053,longitude:-84.4392558},
{latitude:33.7528296,longitude:-84.375508299999},
{latitude:33.7552598,longitude:-84.321364899999},
{latitude:33.7567032,longitude:-84.4248377},
{latitude:33.7588845,longitude:-84.4505026},
{latitude:33.7602419,longitude:-84.4694984},
{latitude:33.7648671,longitude:-84.338544899999},
{latitude:33.7711857,longitude:-84.3424994},
{latitude:33.77402,longitude:-84.440318999999},
{latitude:33.7815385,longitude:-84.391945099999},
{latitude:33.7842017,longitude:-84.4263075},
{latitude:33.7868106,longitude:-84.3728591},
{latitude:33.7923183,longitude:-84.3156132},
{latitude:33.793798,longitude:-84.374111999999},
{latitude:33.8046377,longitude:-84.417740999999},
{latitude:33.807076,longitude:-84.3178858},
{latitude:33.813697,longitude:-84.407702299999},
{latitude:33.8179061,longitude:-84.3773337},
{latitude:33.830032,longitude:-84.350969999999},
{latitude:33.8326102,longitude:-84.407387999999},
{latitude:33.8366036,longitude:-84.374010099999},
{latitude:33.8463002,longitude:-84.436869099999},
{latitude:33.8463002,longitude:-84.436869099999},
{latitude:33.8477749,longitude:-84.433407699999},
{latitude:33.8488195,longitude:-84.417035599999},
{latitude:33.8507965,longitude:-84.3291926},
{latitude:33.8559327,longitude:-84.380972199999},
{latitude:33.9364006,longitude:-118.30466309999},
{latitude:33.936529,longitude:-118.247739},
{latitude:33.9442646,longitude:-118.2491449},
{latitude:33.9461595,longitude:-118.2285444},
{latitude:33.9481925,longitude:-118.309026},
{latitude:33.9548119,longitude:-118.20859669999},
{latitude:33.9555963,longitude:-118.26826399999},
{latitude:33.956328,longitude:-118.32648169999},
{latitude:33.9588630999999,longitude:-118.3112036},
{latitude:33.9674719,longitude:-118.2422121},
{latitude:33.974595,longitude:-118.19158099999},
{latitude:33.9832541,longitude:-118.3035517},
{latitude:33.9870828,longitude:-118.2192524},
{latitude:33.9981581,longitude:-118.32879689999},
{latitude:33.99821,longitude:-118.269918},
{latitude:33.99821,longitude:-118.269918},
{latitude:34.0277149,longitude:-118.2627581},
{latitude:34.0277149,longitude:-118.26275809999},
{latitude:34.0277149,longitude:-118.26275809999},
{latitude:34.028741,longitude:-118.26212509999},
{latitude:34.028741,longitude:-118.26212509999},
{latitude:34.0297536,longitude:-118.2611647},
{latitude:34.030873,longitude:-118.30333789999},
{latitude:34.030873,longitude:-118.30333789999},
{latitude:34.0370434,longitude:-118.1592365},
{latitude:34.0392219,longitude:-118.2112052},
{latitude:34.0399694,longitude:-118.21064849999},
{latitude:34.0399694,longitude:-118.21064849999},
{latitude:34.0443628,longitude:-118.2239608},
{latitude:34.0443628,longitude:-118.22396079999},
{latitude:34.0443628,longitude:-118.22396079999},
{latitude:34.0492951,longitude:-118.1794853},
{latitude:34.0492951,longitude:-118.1794853},
{latitude:34.0497287,longitude:-118.27538859999},
{latitude:34.0522342,longitude:-118.2436849},
{latitude:34.0522342,longitude:-118.2436849},
{latitude:34.0522342,longitude:-118.2436849},
{latitude:34.055126,longitude:-118.332757},
{latitude:34.0604068,longitude:-118.28389329999},
{latitude:34.0611544,longitude:-118.2621691},
{latitude:34.0611544,longitude:-118.2621691},
{latitude:34.0611544,longitude:-118.2621691},
{latitude:34.0642607,longitude:-118.28564929999},
{latitude:34.0642607,longitude:-118.28564929999},
{latitude:34.0643763,longitude:-118.2856509},
{latitude:34.0666313,longitude:-118.1654947},
{latitude:34.0670544,longitude:-118.28969089999},
{latitude:34.0670544,longitude:-118.28969089999},
{latitude:34.0681396,longitude:-118.2610033},
{latitude:34.0685,longitude:-118.261319},
{latitude:34.0685,longitude:-118.261319},
{latitude:34.0689494,longitude:-118.32656529999},
{latitude:34.0689494,longitude:-118.32656529999},
{latitude:34.0706884,longitude:-118.18627129999},
{latitude:34.0706884,longitude:-118.18627129999},
{latitude:34.0756471,longitude:-118.1817495},
{latitude:34.0756471,longitude:-118.1817495},
{latitude:34.0792569,longitude:-118.2355775},
{latitude:34.0792569,longitude:-118.23557749999},
{latitude:34.0792569,longitude:-118.23557749999},
{latitude:34.0951323,longitude:-118.20453609999},
{latitude:34.0951323,longitude:-118.20453609999},
{latitude:34.1145129,longitude:-118.19908709999},
{latitude:34.1157194,longitude:-118.1608287},
{latitude:34.1161995,longitude:-118.2336288},
{latitude:34.1315798,longitude:-118.16066639999},
{latitude:34.1332681,longitude:-118.2069541},
{latitude:34.1335501,longitude:-118.2069569},
{latitude:34.1385549,longitude:-118.24460999999},
{latitude:34.1425078,longitude:-118.255075},
{latitude:34.1515297,longitude:-118.20939799999},
{latitude:34.1572157,longitude:-118.26948549999},
{latitude:34.163255,longitude:-118.238868},
{latitude:34.528455,longitude:69.1717029},
{latitude:35.9626899,longitude:-78.719645799999},
{latitude:36.0297921,longitude:-115.15059639999},
{latitude:36.0325676,longitude:-115.1433369},
{latitude:36.0714961,longitude:-115.11578839999},
{latitude:36.1023602,longitude:-115.1121863},
{latitude:36.1078801,longitude:-115.14778189999},
{latitude:36.1105892,longitude:-115.23807469999},
{latitude:36.11135,longitude:-86.783430899999},
{latitude:36.1126489,longitude:-115.2320755},
{latitude:36.114646,longitude:-115.172816},
{latitude:36.114646,longitude:-115.172816},
{latitude:36.114646,longitude:-115.172816},
{latitude:36.114646,longitude:-115.172816},
{latitude:36.114646,longitude:-115.172816},
{latitude:36.1184992,longitude:-80.0805166},
{latitude:36.1203096,longitude:-115.13106149999},
{latitude:36.1300344,longitude:-115.20327259999},
{latitude:36.1516131,longitude:-115.17975369999},
{latitude:36.1525284,longitude:-115.2621783},
{latitude:36.1904597,longitude:-115.217959},
{latitude:36.2032052,longitude:-115.09244319999},
{latitude:36.2172133,longitude:-115.2603672},
{latitude:36.2176555,longitude:-115.09870439999},
{latitude:36.2269464,longitude:-115.17763079999},
{latitude:37.09024,longitude:-95.712891},
{latitude:37.09024,longitude:-95.712891},
{latitude:37.09024,longitude:-95.712891},
{latitude:37.09024,longitude:-95.712891},
{latitude:37.09024,longitude:-95.712891},
{latitude:37.09024,longitude:-95.712891},
{latitude:37.09024,longitude:-95.712891},
{latitude:37.09024,longitude:-95.712891},
{latitude:37.6621454,longitude:-122.4711991},
{latitude:37.6646064,longitude:-122.4458061},
{latitude:37.6648096,longitude:-122.4246431},
{latitude:37.666935,longitude:-122.441183},
{latitude:37.669959,longitude:-122.41705139999},
{latitude:37.6860487,longitude:-122.48193149999},
{latitude:37.6929638,longitude:-122.4142714},
{latitude:37.6947802,longitude:-122.45920999999},
{latitude:37.7066248,longitude:-122.4859296},
{latitude:37.7094777999999,longitude:-122.4478126},
{latitude:37.7138731999999,longitude:-122.4331951},
{latitude:37.7143086,longitude:-122.4495099},
{latitude:37.7159988,longitude:-122.4561105},
{latitude:37.7174865,longitude:-122.4698538},
{latitude:37.7205349,longitude:-122.4336041},
{latitude:37.7216936,longitude:-122.44203729999},
{latitude:37.7225231,longitude:-122.41243209999},
{latitude:37.7240831,longitude:-122.48507789999},
{latitude:37.7249667,longitude:-122.4449117},
{latitude:37.7253361,longitude:-122.4734789},
{latitude:37.7266375,longitude:-122.44502139999},
{latitude:37.7287679,longitude:-122.4095507},
{latitude:37.7290334,longitude:-122.46694479999},
{latitude:37.7293607999999,longitude:-122.47458069999},
{latitude:37.7323017,longitude:-122.40252169999},
{latitude:37.7332043,longitude:-122.4467414},
{latitude:37.7340852,longitude:-122.38942109999},
{latitude:37.7346951,longitude:-122.47813819999},
{latitude:37.7350727,longitude:-122.42021629999},
{latitude:37.7364085,longitude:-122.439745},
{latitude:37.7368783999999,longitude:-122.42109069999},
{latitude:37.7392827,longitude:-122.44931869999},
{latitude:37.7393186,longitude:-122.47591439999},
{latitude:37.7394867,longitude:-122.3859517},
{latitude:37.7417974,longitude:-122.46547729999},
{latitude:37.7428108,longitude:-122.42783029999},
{latitude:37.7432147,longitude:-122.481085},
{latitude:37.7449289,longitude:-122.4399341},
{latitude:37.7476661,longitude:-122.49859249999},
{latitude:37.7478738,longitude:-122.43826209999},
{latitude:37.7498979,longitude:-122.4056659},
{latitude:37.7501704,longitude:-122.4683985},
{latitude:37.7510211,longitude:-122.43885929999},
{latitude:37.7520701,longitude:-122.4654615},
{latitude:37.756932,longitude:-122.48635489999},
{latitude:37.7572535,longitude:-122.41554739999},
{latitude:37.7574024,longitude:-122.3970039},
{latitude:37.7628972,longitude:-122.45170229999},
{latitude:37.7644669,longitude:-122.43993069999},
{latitude:37.7703578,longitude:-122.460255},
{latitude:37.7736594,longitude:-122.4874785},
{latitude:37.7749295,longitude:-122.4194155},
{latitude:37.7756506,longitude:-122.43285559999},
{latitude:37.7763044999999,longitude:-122.4274136},
{latitude:37.7792441,longitude:-122.44212449999},
{latitude:37.7795548,longitude:-122.45705559999},
{latitude:37.7795999999999,longitude:-122.47824709999},
{latitude:37.7798087,longitude:-122.49009949999},
{latitude:37.779895,longitude:-122.4463243},
{latitude:37.78272,longitude:-122.4913592},
{latitude:37.7843283,longitude:-122.45072419999},
{latitude:37.7846911,longitude:-122.47111719999},
{latitude:37.7848686,longitude:-122.48401289999},
{latitude:37.7849308,longitude:-122.43478859999},
{latitude:37.7851866,longitude:-122.4776031},
{latitude:37.7903121,longitude:-122.4375059},
{latitude:37.7905451,longitude:-122.4542195},
{latitude:37.7906884,longitude:-122.4274707},
{latitude:37.7941248,longitude:-122.40754679999},
{latitude:37.7960324,longitude:-122.4201504},
{latitude:37.7960324,longitude:-122.4201504},
{latitude:37.8008839,longitude:-122.4200352},
{latitude:37.8013917,longitude:-122.4318698},
{latitude:37.8020045,longitude:-122.4121344},
{latitude:37.8392051999999,longitude:-94.354671899999},
{latitude:37.8506103,longitude:-122.4825164},
{latitude:37.8621493,longitude:-122.58136669999},
{latitude:37.8814637,longitude:-122.4668585},
{latitude:38.7595488,longitude:-77.1346949},
{latitude:38.7705413,longitude:-77.0998716},
{latitude:38.773914,longitude:-77.102278},
{latitude:38.7757473,longitude:-77.013367099999},
{latitude:38.776986,longitude:-77.070295999999},
{latitude:38.7774971,longitude:-77.189016499999},
{latitude:38.77986,longitude:-77.169797},
{latitude:38.7997011,longitude:-77.092977799999},
{latitude:38.8040803,longitude:-77.1515195},
{latitude:38.8118081,longitude:-77.184662399999},
{latitude:38.8123355,longitude:-77.0614676},
{latitude:38.8137121,longitude:-77.041472699999},
{latitude:38.8147409,longitude:-77.056538999999},
{latitude:38.822339,longitude:-77.082587999999},
{latitude:38.8252157,longitude:-77.193372299999},
{latitude:38.8282594,longitude:-77.073699199999},
{latitude:38.8311433999999,longitude:-77.0593205},
{latitude:38.8346666,longitude:-77.128423399999},
{latitude:38.8412916,longitude:-77.1267609},
{latitude:38.842223,longitude:-77.1921256},
{latitude:38.8424932,longitude:-77.1354372},
{latitude:38.8437841,longitude:-77.087533399999},
{latitude:38.8448609,longitude:-77.0992097},
{latitude:38.8481797,longitude:-77.0692181},
{latitude:38.848298,longitude:-77.107629999999},
{latitude:38.8532066,longitude:-77.1242958},
{latitude:38.8556201,longitude:-77.149664299999},
{latitude:38.8556201,longitude:-77.149664299999},
{latitude:38.8572439,longitude:-77.085490499999},
{latitude:38.8621459,longitude:-77.1818521},
{latitude:38.8624422,longitude:-77.070853399999},
{latitude:38.8696168,longitude:-77.093295799999},
{latitude:38.8745682,longitude:-77.1341171},
{latitude:38.8763735,longitude:-77.0123618},
{latitude:38.8775262,longitude:-77.0135718},
{latitude:38.8793876,longitude:-77.1791281},
{latitude:38.8819206,longitude:-77.150637599999},
{latitude:38.8820774,longitude:-77.1023743},
{latitude:38.8823752,longitude:-77.138329199999},
{latitude:38.8896317,longitude:-77.103362699999},
{latitude:38.8913953,longitude:-77.1583653},
{latitude:38.8948225,longitude:-77.0942845},
{latitude:38.9004942,longitude:-77.1135367},
{latitude:38.9096240999999,longitude:-77.021151},
{latitude:38.9097734,longitude:-77.0666668},
{latitude:38.9098997,longitude:-77.1726888},
{latitude:38.9108981,longitude:-77.1087158},
{latitude:38.914747,longitude:-77.192267},
{latitude:38.9160321,longitude:-77.139237499999},
{latitude:38.9186587,longitude:-77.041670399999},
{latitude:38.921929,longitude:-77.1262582},
{latitude:38.9230433,longitude:-77.1865814},
{latitude:38.9242008,longitude:-77.1533719},
{latitude:38.925277,longitude:-77.1032966},
{latitude:38.930124,longitude:-77.036374},
{latitude:38.9348941,longitude:-77.074174999999},
{latitude:38.9369371,longitude:-77.0271771},
{latitude:38.9388868,longitude:-77.0746704},
{latitude:38.940514,longitude:-77.071625999999},
{latitude:38.946693,longitude:-77.0660429},
{latitude:38.9507094,longitude:-77.0658025},
{latitude:38.9507363,longitude:-77.0793676},
{latitude:38.950822,longitude:-77.165587},
{latitude:38.9516808,longitude:-77.1082478},
{latitude:38.9518255,longitude:-77.120807899999},
{latitude:38.9642547,longitude:-77.081272399999},
{latitude:38.9674308,longitude:-77.121910299999},
{latitude:38.9679623,longitude:-77.016195299999},
{latitude:38.9686239,longitude:-77.0668732},
{latitude:38.9699247,longitude:-77.0332933},
{latitude:38.9736983,longitude:-77.1310285},
{latitude:38.9752422,longitude:-77.093740099999},
{latitude:38.9796745,longitude:-77.067151799999},
{latitude:38.9819784,longitude:-77.1608658},
{latitude:38.9830101,longitude:-77.086772399999},
{latitude:38.9837,longitude:-77.102542999999},
{latitude:38.984652,longitude:-77.0947092},
{latitude:38.984696,longitude:-77.024575},
{latitude:38.9851078,longitude:-77.061873699999},
{latitude:38.985337,longitude:-77.089529999999},
{latitude:38.9872416,longitude:-77.0210894},
{latitude:38.9880512,longitude:-77.1508628},
{latitude:38.9909317,longitude:-77.0844491},
{latitude:38.991454,longitude:-77.125375299999},
{latitude:38.9924372,longitude:-77.0997267},
{latitude:38.9929681,longitude:-77.1075922},
{latitude:38.996248,longitude:-77.0561452},
{latitude:39.830391,longitude:-75.240460699999},
{latitude:39.8968129,longitude:-92.484487899999},
{latitude:39.9181686,longitude:-75.071283999999},
{latitude:39.952335,longitude:-75.163789},
{latitude:39.952335,longitude:-75.163789},
{latitude:39.952335,longitude:-75.163789},
{latitude:39.952335,longitude:-75.163789},
{latitude:39.968808,longitude:18.0211515999999},
{latitude:40.0112288,longitude:-75.1297422},
{latitude:40.0361682,longitude:-75.2233873},
{latitude:40.522611,longitude:-74.190037599999},
{latitude:40.5779329,longitude:-73.9429819},
{latitude:40.579585,longitude:-73.99696},
{latitude:40.5936488,longitude:-74.135536699999},
{latitude:40.5949648,longitude:-74.0012444},
{latitude:40.5963112,longitude:-74.001367299999},
{latitude:40.6004042,longitude:-73.9727457},
{latitude:40.601211,longitude:-73.819666999999},
{latitude:40.6024346,longitude:-73.762495},
{latitude:40.6034564,longitude:-73.957509599999},
{latitude:40.6039471,longitude:-73.781615399999},
{latitude:40.6040402,longitude:-73.9576572},
{latitude:40.6046289,longitude:-74.1622821},
{latitude:40.6102573999999,longitude:-73.9485775},
{latitude:40.6118435,longitude:-74.024055299999},
{latitude:40.6119911,longitude:-73.9330429},
{latitude:40.6120808,longitude:-74.0140275},
{latitude:40.6123791,longitude:-74.035691999999},
{latitude:40.6173752,longitude:-73.974605399999},
{latitude:40.6189896,longitude:-73.900075799999},
{latitude:40.6215043,longitude:-73.980709199999},
{latitude:40.6223817,longitude:-73.9755635},
{latitude:40.6252792,longitude:-74.0172731},
{latitude:40.6274015,longitude:-74.0403092},
{latitude:40.6281052,longitude:-74.039933099999},
{latitude:40.6319215,longitude:-74.013295799999},
{latitude:40.6321326,longitude:-73.9527243},
{latitude:40.6344459,longitude:-73.916918},
{latitude:40.644081,longitude:-74.108515},
{latitude:40.6510157,longitude:-73.9714234},
{latitude:40.6589543,longitude:-73.8871125},
{latitude:40.659979,longitude:-73.7455792},
{latitude:40.6610734,longitude:-73.761759499999},
{latitude:40.6672766,longitude:-73.812374299999},
{latitude:40.6673435,longitude:-73.9264296},
{latitude:40.6755827,longitude:-73.7852922},
{latitude:40.6755827,longitude:-73.7852922},
{latitude:40.6758076,longitude:-73.9974191},
{latitude:40.6785956,longitude:-73.932966099999},
{latitude:40.6805943,longitude:-73.927631599999},
{latitude:40.6807227,longitude:-73.7809315},
{latitude:40.6811093,longitude:-73.9364595},
{latitude:40.6839472,longitude:-73.972534399999},
{latitude:40.6880777,longitude:-73.770172699999},
{latitude:40.6883698,longitude:-73.8874799},
{latitude:40.6896329,longitude:-73.9730812},
{latitude:40.6904049,longitude:-73.999530999999},
{latitude:40.6914808,longitude:-73.8854013},
{latitude:40.69155,longitude:-73.974921999999},
{latitude:40.6989194,longitude:-74.0817844},
{latitude:40.7000227,longitude:-73.856608699999},
{latitude:40.7015856,longitude:-73.7842172},
{latitude:40.7152937,longitude:-73.975276099999},
{latitude:40.7169743,longitude:-74.011466999999},
{latitude:40.7211252,longitude:-73.8845994},
{latitude:40.723731,longitude:-74.0888719},
{latitude:40.7275956,longitude:-73.904750999999},
{latitude:40.7354247,longitude:-74.0104026},
{latitude:40.7357684,longitude:-73.8742859},
{latitude:40.7367631,longitude:-73.7683784},
{latitude:40.7367631,longitude:-73.7683784},
{latitude:40.7400174,longitude:-73.735281999999},
{latitude:40.7428053999999,longitude:-73.8448469},
{latitude:40.7428053999999,longitude:-73.8448469},
{latitude:40.7461756,longitude:-73.808086899999},
{latitude:40.747316,longitude:-73.7429555},
{latitude:40.7537964,longitude:-73.889317399999},
{latitude:40.7593894,longitude:-74.0431176},
{latitude:40.761386,longitude:-74.0676283},
{latitude:40.7616814,longitude:-73.7328251},
{latitude:40.7686679,longitude:-73.7469892},
{latitude:40.7718307,longitude:-73.767643},
{latitude:40.7737505,longitude:-73.8713099},
{latitude:40.7765701,longitude:-73.923729299999},
{latitude:40.7923636,longitude:-73.964215299999},
{latitude:40.795813,longitude:-73.922516999999},
{latitude:40.7961095,longitude:-73.976658899999},
{latitude:40.7980022,longitude:-73.999282499999},
{latitude:40.8010447,longitude:-73.917471499999},
{latitude:40.8110256,longitude:-73.9643166},
{latitude:40.8122741,longitude:-73.914073},
{latitude:40.8188073,longitude:-73.922117099999},
{latitude:40.820099,longitude:-73.911537399999},
{latitude:40.8205375,longitude:-73.930816},
{latitude:40.8209202,longitude:-73.9489945},
{latitude:40.822522,longitude:-73.955461},
{latitude:40.8240049,longitude:-73.8766722},
{latitude:40.8249658,longitude:-73.9365119},
{latitude:40.8366558,longitude:-73.892195899999},
{latitude:40.8494748,longitude:-73.946673299999},
{latitude:40.8524228,longitude:-73.8687104},
{latitude:40.8655654,longitude:-73.89731},
{latitude:40.8666005,longitude:-73.806503},
{latitude:40.8715417,longitude:-73.925694799999},
{latitude:40.8725333,longitude:-73.8395362},
{latitude:40.875363,longitude:-72.508198999999},
{latitude:40.879138,longitude:-73.882062},
{latitude:40.884709,longitude:-73.890542699999},
{latitude:40.885941,longitude:-73.9165092},
{latitude:40.8886522,longitude:-72.536011},
{latitude:40.937522,longitude:-72.294836},
{latitude:40.961885,longitude:-72.186360999999},
{latitude:40.970728,longitude:-72.200795999999},
{latitude:40.972907,longitude:-72.148273799999},
{latitude:41.4399936,longitude:-81.7392934},
{latitude:41.5045982,longitude:-81.6765161},
{latitude:41.7806042,longitude:-87.584597199999},
{latitude:41.7811084,longitude:-87.6762201},
{latitude:41.7938445,longitude:-87.6849829},
{latitude:41.7955022,longitude:-87.626810999999},
{latitude:41.7979895,longitude:-87.594170599999},
{latitude:41.802465,longitude:-87.611009},
{latitude:41.8110101,longitude:-87.633138199999},
{latitude:41.8230359999999,longitude:-87.6812959},
{latitude:41.8264565,longitude:-87.6010202},
{latitude:41.830053,longitude:-87.609628999999},
{latitude:41.8415983,longitude:-87.6479742},
{latitude:41.8731529999999,longitude:-87.6205959},
{latitude:41.8736053,longitude:-87.647019899999},
{latitude:41.8757571,longitude:-87.715736699999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8781136,longitude:-87.629798199999},
{latitude:41.8853527,longitude:-87.6652386},
{latitude:41.9067696,longitude:-87.701287799999},
{latitude:41.9111519,longitude:-87.631359999999},
{latitude:41.925223,longitude:-87.678173},
{latitude:41.9271334,longitude:-87.838778199999},
{latitude:41.9594104,longitude:-87.698404999999},
{latitude:41.9631007,longitude:-87.6862352},
{latitude:41.9817028999999,longitude:-87.7179865},
{latitude:41.9858948,longitude:-87.7284869},
{latitude:41.9884897,longitude:-87.7114685},
{latitude:41.9899211,longitude:-87.667550699999},
{latitude:41.9904085,longitude:-87.703056399999},
{latitude:42.3189984,longitude:-83.092336499999},
{latitude:42.367781,longitude:-71.057919999999},
{latitude:42.3736158,longitude:-71.1097335},
{latitude:42.3776582,longitude:-71.069986299999},
{latitude:43.16103,longitude:-77.6109219},
{latitude:44.9238552,longitude:-92.9593797},
{latitude:44.9238552,longitude:-92.9593797},
{latitude:44.9396341,longitude:-93.2727565},
{latitude:44.9401737,longitude:-93.253726499999},
{latitude:44.9401737,longitude:-93.253726499999},
{latitude:44.9628536,longitude:-93.2512772},
{latitude:44.9677167,longitude:-93.2011319},
{latitude:44.9688633,longitude:-93.2593279},
{latitude:44.9713104,longitude:-93.2827112},
{latitude:44.9771828,longitude:-93.3109255},
{latitude:44.9788445,longitude:-93.231623799999},
{latitude:44.9826088,longitude:-93.2988439},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.983334,longitude:-93.266669999999},
{latitude:44.996917,longitude:-93.243384699999},
{latitude:44.9983948,longitude:-93.301995499999},
{latitude:44.9992477,longitude:-93.252459499999},
{latitude:45.0125208,longitude:-93.234603199999},
{latitude:45.0295097,longitude:-93.235828599999},
{latitude:45.0350013,longitude:-93.2944218},
{latitude:45.4659599,longitude:9.26286570000002},
{latitude:45.4785487,longitude:9.2366143},
{latitude:45.4912762,longitude:9.2427112},
{latitude:45.4942593,longitude:9.1972608},
{latitude:45.4944187,longitude:9.231472},
{latitude:45.881293,longitude:-95.3822721},
{latitude:45.9858407,longitude:12.7035513},
{latitude:46.2174355999999,longitude:10.1750296},
{latitude:47.5152229,longitude:-122.2593507},
{latitude:47.5396758,longitude:-122.3963501},
{latitude:47.5526115,longitude:-122.3223411},
{latitude:47.5631768999999,longitude:-122.3633681},
{latitude:47.5676485,longitude:-122.2718102},
{latitude:47.5706548,longitude:-122.22206729999},
{latitude:47.5736423,longitude:-122.3043842},
{latitude:47.5809164,longitude:-122.2886379},
{latitude:47.5894635,longitude:-122.2944666},
{latitude:47.6051268,longitude:-122.3022871},
{latitude:47.6116231,longitude:-122.28934659999},
{latitude:47.6317608,longitude:-122.31664319999},
{latitude:47.6346869,longitude:-122.27713019999},
{latitude:47.6649436999999,longitude:-122.31974009999},
{latitude:47.6686942,longitude:-122.3038823},
{latitude:47.6704999,longitude:-122.34262109999},
{latitude:47.6816428,longitude:-122.3275386},
{latitude:47.7074919999999,longitude:-122.29562299999},
{latitude:50.003685,longitude:36.2474936999999},
{latitude:51.5124441,longitude:-0.1786359999999},
{latitude:64.8324588,longitude:-147.4617266},
{latitude:64.8324588,longitude:-147.4617266}
];
	
	arr = [{latitude:-19.9252565,longitude:-43.934682699999}];
	
	var index = 0;
	var lastIndex = 1;
	function storeAddress(){
		$.ajax({
			url: rootDomain + "/service/util/storeaddress",
			//dataType: "json",
			method: "post",
			data: $("#storeAddressForm").serialize(),
			error: function() {
				console.log("error " + index);
				console.log(arguments);
			},
			success: function( response ) {
				console.log("Success " + index);
				console.log(response);
			}
		});
	}
	var resultArr = [];
	
	function initGeolocationInput(){
		//geolocation--
		$("#geolocationInput").geocomplete({
	    }).bind("geocode:result", function(event, result){
	    		$('input').val(null);
	    		
	    		document.getElementById("latitude").value = result.geometry.location.lat();
	    	  document.getElementById("longitude").value = result.geometry.location.lng(); 
	    	  document.getElementById("addressTypesInput").value = result.types;
	    	  
	    	  for( var i = 0 ; i <  result.address_components.length ; i++){
	    		  var c = result.address_components[i];
	    		  var input = document.getElementById(c.types[0]);
	    		  if(input){
	    			  input.value = c.long_name;
	    			  
	    			  var inputShort = document.getElementById(c.types[0] + "_short");
	    			  if(inputShort){
	    				  inputShort.value = c.short_name;
	    			  }
	    		  }
	    	  }
	    	  
	    	  
	    	  storeAddress();  
	    })
	    .bind("geocode:error", function(event, status){
	  	  console.log("ERROR: ");
	  	  console.log(status);
	    })
	    .bind("geocode:multiple", function(event, results){
	      console.log("Multiple: ");
	      console.log(result);
	    });
	}
	initGeolocationInput();
		 
		
	function myLoop(){
		setTimeout(function () {    //  call a 3s setTimeout when the loop is called
			var l = arr[index].latitude + " " + arr[index].longitude;
		 $("#geolocationInput").geocomplete("find", l);
	      index++;
	      if (index < lastIndex && index < arr.length) {
	         myLoop(); 
	      }
	      else{
	    	  //console.log(resultArr.length);
	    	  //console.log(JSON.stringify(resultArr, null, 2));
	      }
	   }, 2000);
	}
	//myLoop();
	</script>
</body>
</html>