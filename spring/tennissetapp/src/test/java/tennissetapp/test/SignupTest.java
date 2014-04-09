package tennissetapp.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class SignupTest {
	protected Logger logger = Logger.getLogger(MatchesTest.class);
	private static final String ROOT = "http://localhost:8080/tennissetapp";
	
	protected RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void signup(){
		String response = restTemplate.postForObject(ROOT + "/service/signup?email=a@yahoo.com&username=a&password=aaaaaa&confirmPassword=aaaaaa","", String.class);
		logger.info("THE RESPONSE IS " + response);
	}
}
