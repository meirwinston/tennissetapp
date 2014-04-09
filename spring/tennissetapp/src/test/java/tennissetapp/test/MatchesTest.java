package tennissetapp.test;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Sets;
import com.tennissetapp.args.CalendarEventsArgs;
import com.tennissetapp.args.ScrollByUserAccountIdArgs;
import com.tennissetapp.args.SearchMatchesArgs;
import com.tennissetapp.config.DataConfig;
import com.tennissetapp.config.RootConfig;
import com.tennissetapp.config.SecurityConfig;
import com.tennissetapp.config.SocialConfig;
import com.tennissetapp.forms.SearchMatchesForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.Match;
import com.tennissetapp.persistence.entities.MatchSelect;

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
public class MatchesTest {
	protected Logger logger = Logger.getLogger(MatchesTest.class);
	
	
	@Autowired
	DaoManager daoManager;
	
	private RestTemplate restTemplate;
	
//    @Before
    public void setup() {
    	restTemplate = new RestTemplate();
    	
    	DefaultHttpClient client = new DefaultHttpClient();
        Credentials defaultcreds = new UsernamePasswordCredentials("meirwinston@yahoo.com", "111111");
        
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
        client.getCredentialsProvider().setCredentials(AuthScope.ANY, defaultcreds);
        
        //call login
        restTemplate.postForObject("http://localhost:8080/tennissetapp/j_spring_security_check?j_username=meirwinston@yahoo.com&j_password=111111","" ,String.class);
    }
    
    
    
//    @Test
    public void findMatch(){
    	try {
    		logger.info("find match 18: " + daoManager.findMatch(18l));
    		
    		logger.info("Members: " + daoManager.findMatchMembers(18l));
		} catch (Exception exp) {
			logger.error(exp.getMessage(),exp);
			Assert.fail();
		}
    	
    }
	
//    @Test
    public void createMatch(){
		logger.info("createMatch");
		String params = "name=MyMatch1&singlesCheck=on&tennisCenterId=1&levelOfPlay=3.5&visibility=PUBLIC"
				+"&startTime=" + new Date().getTime() + 
				"&endTime=" + new Date(System.currentTimeMillis() + 1000000).getTime();
		Map<?,?> response = restTemplate.postForObject("http://localhost:8080/tennissetapp/service/matches/create?" + params,"",Map.class);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.get("matchId"));
		Long matchId = Long.valueOf(response.get("matchId").toString());
		Match match = daoManager.find(Match.class, matchId);
		Assert.assertNotNull(match);
		Assert.assertNotNull(match.getMatchId());
//		Assert.assertNotNull(match.getName());
		Assert.assertNotNull(match.getEndDate());
		Assert.assertNotNull(match.getLevelOfPlayMax());
		Assert.assertNotNull(match.getLevelOfPlayMin());
		Assert.assertNotNull(match.getStartDate());
		Assert.assertNotNull(match.getTennisCenterId());
		Assert.assertNotNull(match.getTennisCenter());
		Assert.assertNotNull(match.getOrgenizerId());
		Assert.assertNotNull(match.getOrgenizer());
		
		logger.info("createMatch:response: " + response);
	}
	
//	@Test
	public void search(){
		SearchMatchesForm f = new SearchMatchesForm();
//		[playSingles=true, playDoubles=false, 
//		playFullMatch=false, playPoints=true, 
//		playHittingAround=false, 
//		latitude=37.4219988, longitude=-122.083954, startTime=Fri Dec 13 00:00:00 EST 2013, endTime=Fri Dec 13 23:59:00 EST 2013, levelOfPlayMin=2.0, levelOfPlayMax=3.0, distance=50.0, offset=0, maxResults=8]
		
		SearchMatchesArgs args = new SearchMatchesArgs();
		args.playSingles = true;
		args.playPoints = true;
		args.latitude = 40.795813;
		args.longitude = -73.92251699999997;
//		args.distance = 10d;
//		List<MatchSelect> list = daoManager.searchMatchItems(args);
//		logger.info("search, results size: " + list.size());
//		Assert.assertNotNull(list);
//		Assert.assertTrue(list.size() > 0);
	}
	
//	@Test
	public void search2(){
		SearchMatchesArgs args = new SearchMatchesArgs();
		args.playSingles = false;
		args.playDoubles = true;
		args.playFullMatch = false;
		args.playPoints = false;
		args.playHittingAround = false;
		args.latitude = 40.8009744;
		args.longitude = -73.91752550000001;
//		args.latitude = 40.4244357;
//		args.longitude = -74.4221905;
		args.startTime = null;
		args.endTime = null;
		args.levelOfPlayMin = 1.5f;
		args.levelOfPlayMax = 1.5f;
		args.distance = 50d;
		args.firstResult = 0l;
		args.maxResults = 10;
		
		List<MatchSelect> list = daoManager.searchMatchItems(args);
		logger.info("size: " + list.size());
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);

	}
	
//	@Test
	public void search3(){
		//[levelOfPlayMin=1.0, levelOfPlayMax=2.5, distance=50.0, offset=0, maxResults=2](DEBUG)
		
		SearchMatchesArgs args = new SearchMatchesArgs();
		args.playSingles = true;
		args.playDoubles = true;
		args.playFullMatch = false;
		args.playPoints = false;
		args.playHittingAround = false;
		args.latitude = 25.6913776;
		args.longitude = -80.28490490000002;
		args.startTime = null;
		args.endTime = null;
		args.levelOfPlayMin = 1.0f;
		args.levelOfPlayMax = 2.5f;
		args.distance = 60d;
		args.firstResult = 0l;
		args.maxResults = 10;
		
		List<MatchSelect> list = daoManager.searchMatchItems(args);
		logger.info("search3: size: " + list);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);

	}
	
//	@Ignore
//	@Test
	public void testMultipleQueriesInOneExpression(){
		SearchMatchesForm f = new SearchMatchesForm();
		
//		f.setDateYear("2013");
//		f.setDateMonth("08");
//		f.setDateDay("31");
//		
//		f.setStartHour("5");
//		f.setStartMinute("00");
//		f.setStartDayTime("PM");
//		
//		f.setEndHour("10");
//		f.setEndMinute("00");
//		f.setEndDayTime("PM");
//		
//		f.setLevel1("on");
//		f.setLevel2("on");
//
//		//40.8009744,-73.91752550000001 Randalls island
//		f.setLatitude("40.8009744");
//		f.setLongitude("-73.91752550000001");
//		f.setPlayDoubles("on");
//		f.setPlaySingles("on");
//
//		List<?> list = daoManager.test(f.toArgs());
//		logger.info("size: " + list.size());
//		Assert.assertNotNull(list);
//		Assert.assertTrue(list.size() > 0);
	}
	
	
}

