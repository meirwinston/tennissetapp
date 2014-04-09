package tennissetapp.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.tennissetapp.args.CreateProfileArgs;
import com.tennissetapp.args.PlayerProfileArgs;
import com.tennissetapp.args.ScrollByUserAccountIdArgs;
import com.tennissetapp.args.SignupArgs;
import com.tennissetapp.config.DataConfig;
import com.tennissetapp.config.RootConfig;
import com.tennissetapp.config.SecurityConfig;
import com.tennissetapp.config.SocialConfig;
import com.tennissetapp.json.JacksonUtils;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.Mate;
import com.tennissetapp.persistence.entities.TennisCenter;
import com.tennissetapp.persistence.entities.TennisCourt;
import com.tennissetapp.persistence.entities.TennisPlayerProfile;
import com.tennissetapp.persistence.entities.TennisPlayerProfile.Hand;
import com.tennissetapp.persistence.entities.UserAccount;
import com.tennissetapp.persistence.entities.UserAccount.AccountStatus;
import com.tennissetapp.util.TennisSetAppUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false) //to avoid automatic rollback after test
@ContextConfiguration(
classes={
	RootConfig.class,
	DataConfig.class,
	SecurityConfig.class,
	SocialConfig.class
})
public class AuthTest {
	protected Logger logger = Logger.getLogger(MatchesTest.class);
	private static final String ROOT = "http://localhost:8080/tennissetapp";
	@Inject
	protected DaoManager daoManager;
	
	@Inject
	protected AuthenticationManager authenticationManager;
	
	@Inject
	protected Md5PasswordEncoder passwordEncoder;
	
	@Inject
	protected ReflectionSaltSource saltSource;
	
	protected RestTemplate restTemplate;
	
	@Before
	public void setup() {
		restTemplate = new RestTemplate();

		DefaultHttpClient client = new DefaultHttpClient();
		Credentials defaultcreds = new UsernamePasswordCredentials("meirwinston@yahoo.com", "111111");

		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
		client.getCredentialsProvider().setCredentials(AuthScope.ANY, defaultcreds);

		//call login
		//	        restTemplate.postForObject("http://localhost:8080/tennissetapp/j_spring_security_check?j_username=meirwinston@yahoo.com&j_password=111111","" ,String.class);

	}
	
	@Test
	public void encrypt(){
//		String pass = passwordEncoder.encodePassword("111111", "meirwinston@yahoo.com");
		String pass = passwordEncoder.encodePassword("111111", "darawinston@yahoo.com");
		logger.info("the encrypted password is: " + pass);
	}
	
//	@Test
	public void getPlayerProfile(){
		String response = get("/service/profile/player");
		logger.info("getPlayerProfile response: " + response);
	}
	
//	@Test
	public void login(){
		Authentication token = new UsernamePasswordAuthenticationToken("meirwinston@yahoo.com","111111");
//		Authentication token = new UsernamePasswordAuthenticationToken(userAccount.getEmail(),userAccount.getPassword());
		Authentication auth = authenticationManager.authenticate(token);
//		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	public String post(String url){
		String response = restTemplate.postForObject(url,"" ,String.class);
		return response;
	}
	
	public String get(String url){
		String response = restTemplate.getForObject(ROOT + url, String.class);
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
	
//	@Test
	public void insert(){
		String[][] names = new String[][]{
			new String[]{"andrea@tennissetapp.com","Andrea","Pedicini"},
			new String[]{"darawinston@yahoo.com","Dara","Winston"},
			new String[]{"greg@yahoo.com","Greg","Tobkin"},
			new String[]{"list@hotmail.com","Lisa","Holt"},
			new String[]{"diane@hotmail.com","Diane","Wilki"},
			new String[]{"top@hotmail.com","Tom","Wilki"},
			new String[]{"renata@hotmail.com","Renata","Bodner"},
			new String[]{"tom@hotmail.com","Tom","Smeraldi"},
		};
		
		//auto generated emails
//		List<String[]> l = Lists.newArrayList();
//		for(int i = 0 ; i < 100 ; i++){
//			l.add(new String[]{"test" + i + "@emailinator.net","Test" + i, "Mill" + i});
//		}
//		names = l.toArray(new String[0][]);
		
		for(String[] a : names){
			SignupArgs args = new SignupArgs();
			args.email = a[0];
			args.password = "111111";
			args.ipAddress = "10.0.2.2";
			args.username = args.email;
			
			UserAccount userAccount = daoManager.createUserAccount(args);
			Assert.assertNotNull(userAccount);
			
			CreateProfileArgs args2 = new CreateProfileArgs();
			args2.agreesToTerms = true;
			args2.birthDay = 6;
			args2.birthMonth = 5;
			args2.birthYear = 1976;
			args2.gender = "MALE";
			args2.firstName = a[1];
			args2.lastName = a[2];
			args2.userAccountId = userAccount.getUserAccountId();
			TennisPlayerProfile profile = null;//daoManager.createPlayerProfile(args2);
			Assert.assertNotNull(profile);
			
			PlayerProfileArgs args3 = new PlayerProfileArgs();
			args3.userAccountId = userAccount.getUserAccountId();
			args3.latitude = 25.6913776;
			args3.longitude = -80.28490490000002;
			args3.doublesCheck = true;
			args3.singlesCheck = true;
			args3.fullMatchCheck = false;
			args3.hand = Hand.RIGHT;
			args3.weekdayAvailabilityAfternoonCheck = true;
			args3.weekendAvailabilityAfternoonCheck = true;
			args3.levelOfPlay = 3.5f;
			userAccount = daoManager.updateAccount(args3);
			Assert.assertNotNull(userAccount);
			userAccount.setStatus(AccountStatus.ACTIVE);
			logger.info("COMPLETED NEW ACCOUNT");
		}
		
	}
	
//	@Test
	public void selectMates(){
		
		ScrollByUserAccountIdArgs args = new ScrollByUserAccountIdArgs();
		args.firstResult = 0;
		args.maxResults = 10;
		args.userAccountId = 57l;
		List<Mate> list = daoManager.scrollUserMates(args);
		logger.info("THE LIST OF MATES: " + list);
		for(Mate m : list){
			Assert.assertNotNull(m.getMateAccount());
			Assert.assertNotNull(m.getMateAccount().getAddress());
			Assert.assertNotNull(m.getMateAccount().getPlayerProfile());
//			logger.info(m.getMateAccount().getPlayerProfile());
			
			logger.info(m.getMateAccount().getPlayerProfile().getProfileImageFile() + ", " + TennisSetAppUtils.extractProfileImageUrl(m.getMateAccount().getPlayerProfile()));
		}
		
				
	}
}
