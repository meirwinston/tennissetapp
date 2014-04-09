package tennissetapp.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.RestTemplate;

public class RestfulTest {
	protected Logger logger = Logger.getLogger(MatchesTest.class);
	private static final String URL = "http://localhost:8080/tennissetapp";

	@Inject
	protected AuthenticationManager authenticationManager;
	
	protected RestTemplate restTemplate;
	
	@Before
	public void setup() {
		restTemplate = new RestTemplate();

		DefaultHttpClient client = new DefaultHttpClient();
		Credentials defaultcreds = new UsernamePasswordCredentials("meirwinston@yahoo.com", "111111");

		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
		client.getCredentialsProvider().setCredentials(AuthScope.ANY, defaultcreds);

		//call login
		restTemplate.postForObject("http://localhost:8080/tennissetapp/j_spring_security_check?j_username=meirwinston@yahoo.com&j_password=111111","" ,String.class);

	}
	
	
//	@Test
	public void getPlayerProfile(){
		try {
			Map<?,?> response = get("/service/profile/player");
			logger.info("getPlayerProfile " + response);
			
			Map<?,?> profile = (Map<?,?>)response.get("profile");
			Assert.assertNotNull(profile);
			Assert.assertTrue(profile.get("firstName").equals("Meir"));
			Assert.assertTrue(profile.get("lastName").equals("Winston"));
		} 
		catch (Exception exp) {
			logger.error(exp.getMessage(),exp);
			Assert.fail(exp.getMessage());
		}
		//{"profile":{"availableWeekendAfternoon":false,"lastName":"Winston","availableWeekdayMorning":true,"playDoubles":true,"playHittingAround":true,"availableWeekdayEvening":false,"availableWeekendMorning":true,"attendance":0.8,"profileImageUrl":"images/PROFILE_PHOTOS/73400763-e0a5-4300-b103-dcd378d217cb.JPEG","punctuality":0.2,"levelOfPlay":1.5,"availableWeekdayAfternoon":false,"playFullMatch":true,"country":"United States","playPoints":true,"tennisLevel":0.6,"locality":"East Brunswick,,","availableWeekendEvening":false,"firstName":"Meir","administrativeAreaLevel1":"New Jersey","playSingles":true}}
	}
	
	public void searchTeachers(){
		try {
			Map<?,?> response = get("/service/teachers/search?");
			logger.info("searchTeachers " + response);
			
		} 
		catch (Exception exp) {
			logger.error(exp.getMessage(),exp);
			Assert.fail(exp.getMessage());
		}
	}
	
//	@Test
	public void login(){
		Authentication token = new UsernamePasswordAuthenticationToken("meirwinston@yahoo.com","111111");
//		Authentication token = new UsernamePasswordAuthenticationToken(userAccount.getEmail(),userAccount.getPassword());
		Authentication auth = authenticationManager.authenticate(token);
//		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	public String post(String url){
		String response = restTemplate.postForObject(URL + url,"" ,String.class);
		return response;
	}
	
	public Map<?,?> get(String url){
		HashMap<?,?> response = restTemplate.getForObject(URL + url ,HashMap.class);
		return response;
	}
	
//	@Test
	public void loginService(){
		String params1;
		String response;
			//2 outdoor, 2 indoor
		params1 = "j_username=meirwinston@yahoo.com&j_password=111111";

		response = post( "http://localhost:8080/tennissetapp/service/login?" + params1);

		logger.info("loginService response" + response + ", ");
		
		response = post( "http://localhost:8080/tennissetapp/service/testAuth");
		
		logger.info("loginService response" + response + ", ");
	}
	
}