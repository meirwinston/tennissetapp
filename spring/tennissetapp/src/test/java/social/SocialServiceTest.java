package social;

import java.io.IOException;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.security.web.util.IpAddressMatcher;
import org.springframework.security.web.util.RequestMatcherEditor;
import org.springframework.social.test.client.MockRestServiceServer;
import org.springframework.social.test.client.RequestMatcher;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

public class SocialServiceTest {

//	@Test
	public void getUserProfile() {
		TwitterTemplate twitter = new TwitterTemplate("consumerKey", "consumerSecret", "accessToken", "accessTokenSecret");
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(twitter.getRestTemplate());
		
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    
	    RequestMatcher m = new RequestMatcher(){
			@Override
			public void match(ClientHttpRequest c) throws IOException,AssertionError {
				
				
			}
	    	
	    };
	    mockServer.expect(m);
	    
//	    mockServer.expect(requestTo("https://api.twitter.com/1/account/verify_credentials.json"))
//	        .andExpect(method(GET))
//	        .andRespond(withResponse(jsonResource("verify-credentials"), responseHeaders));
//
	    TwitterProfile profile = twitter.userOperations().getUserProfile();
//	    assertEquals(161064614, profile.getId());
//	    assertEquals("kdonald", profile.getScreenName());
//	    com.fasterxml.jackson.databind.JavaType
//	    com.fasterxml.jackson.databind.JavaType
	}
	
}
