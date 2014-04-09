package tennissetapp.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.tennissetapp.args.FindByLocationArgs;
import com.tennissetapp.args.SearchTennisCourtsArgs;
import com.tennissetapp.config.DataConfig;
import com.tennissetapp.config.RootConfig;
import com.tennissetapp.config.SecurityConfig;
import com.tennissetapp.config.SocialConfig;
import com.tennissetapp.json.JacksonUtils;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.CourtSelect;
import com.tennissetapp.persistence.entities.TennisCenter;
import com.tennissetapp.persistence.entities.TennisCourt;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@ContextConfiguration(
classes={
	RootConfig.class,
	DataConfig.class,
	SecurityConfig.class,
	SocialConfig.class
})
public class TennisCourtTest {
	protected Logger logger = Logger.getLogger(TennisCourtTest.class);
	
	@Autowired
	DaoManager daoManager;
	
	private RestTemplate restTemplate;
	
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
    
//    @Test
	public void testCreateTennisCenter2() throws JsonParseException, JsonMappingException, IOException{
		Long totalAddresses = (Long)daoManager.getSession().createQuery("select COUNT(*) from Address as a").uniqueResult();
		logger.info("testCreateTennisCenter2: total addresses: " + totalAddresses);
		
		//one outdoor, 1 indoor
		String params2 = "courtName=My+Tennis+Court+2&geolocation=548+Ryders+Lane%2C+East+Brunswick%2C+NJ&outdoorSurface=CARPET&numberOfOutdoorCourts=1&indoorSurface=CLAY&numberOfIndoorCourts=2&phoneNumber=732-222-2222&website=www.tennissetapp.com&latitude=40.4244357&longitude=-74.4221905&streetNumber=548&route=Ryders+Ln&locality=East+Brunswick&sublocality=&political=&administrativeAreaLevel2=Middlesex&administrativeAreaLevel1=New+Jersey&country=United+States&postalCode=08816&streetNumberShortName=548&routeShortName=Ryders+Ln&localityShortName=East+Brunswick&sublocalityShortName=&politicalShortName=&administrativeAreaLevel2ShortName=Middlesex&administrativeAreaLevel1ShortName=NJ&countryShortName=US&postalCodeShortName=08816&addressTypes=street_address";
		
		String response = post( "http://localhost:8080/tennissetapp/service/courts/create?" + params2);
		Map<String,Object> map = JacksonUtils.deserializeObjectMap(response);
		TennisCenter tennisCenter = (TennisCenter)daoManager.getNewSession().get(TennisCenter.class, Long.valueOf(map.get("tennisCenterId").toString()));
		Assert.assertNotNull(tennisCenter);
		Assert.assertNotNull(tennisCenter.getName());
		Assert.assertNotNull(tennisCenter.getPhoneNumber());
		Assert.assertNotNull(tennisCenter.getWebsite());
		Assert.assertNotNull(tennisCenter.getAddress());
		Assert.assertNotNull(tennisCenter.getCreatedOn());
		Assert.assertNotNull(tennisCenter.getImage());
		Assert.assertNotNull(tennisCenter.getLatitude());
		Assert.assertNotNull(tennisCenter.getLongitude());
		Assert.assertNotNull(tennisCenter.getTennisCenterId());
		Assert.assertNotNull(tennisCenter.getTennisCourts());
		Assert.assertEquals(tennisCenter.getTennisCourts().size(), 2);
		for(TennisCourt c : tennisCenter.getTennisCourts()){
			Assert.assertTrue("0 courts of this type",c.getNumberOfCourts().intValue() > 0);
			Assert.assertNotNull(c.getPlacement());
			Assert.assertNotNull(c.getSurface());
			Assert.assertEquals(c.getTennisCenter().getTennisCenterId(), tennisCenter.getTennisCenterId());
		}
		
		Long totalAddressesAfter = (Long)daoManager.getSession().createQuery("select COUNT(*) from Address as a").uniqueResult();
		logger.info("testCreateTennisCenter2: total addresses after " + totalAddressesAfter);
		
		Assert.assertEquals(totalAddresses, totalAddressesAfter);
	}

    
//    @Test
	public void testCreateTennisCenter1() throws JsonParseException, JsonMappingException, IOException{
		Long totalAddresses = (Long)daoManager.getSession().createQuery("select COUNT(*) from Address as a").uniqueResult();
		logger.info("testCreateTennisCenter1: total addresses: " + totalAddresses);
		
		//2 outdoor, 2 indoor
		String params1 = "courtName=My+Tennis+Court+1&geolocation=548+Ryders+Lane%2C+East+Brunswick%2C+NJ&outdoorSurface=CARPET&numberOfOutdoorCourts=3&outdoorSurface=CLAY&numberOfOutdoorCourts=2&indoorSurface=CARPET&numberOfIndoorCourts=2&indoorSurface=GRASS&numberOfIndoorCourts=1&phoneNumber=732-111-1111&website=www.tennissetapp.com&latitude=40.4244357&longitude=-74.4221905&streetNumber=548&route=Ryders+Ln&locality=East+Brunswick&sublocality=&political=&administrativeAreaLevel2=Middlesex&administrativeAreaLevel1=New+Jersey&country=United+States&postalCode=08816&streetNumberShortName=548&routeShortName=Ryders+Ln&localityShortName=East+Brunswick&sublocalityShortName=&politicalShortName=&administrativeAreaLevel2ShortName=Middlesex&administrativeAreaLevel1ShortName=NJ&countryShortName=US&postalCodeShortName=08816&addressTypes=street_address";

		String response = post( "http://localhost:8080/tennissetapp/service/courts/create?" + params1);
		Map<String,Object> map = JacksonUtils.deserializeObjectMap(response);
		TennisCenter tennisCenter = (TennisCenter)daoManager.getNewSession().get(TennisCenter.class, Long.valueOf(map.get("tennisCenterId").toString()));
		Assert.assertNotNull(tennisCenter);
		Assert.assertNotNull(tennisCenter.getName());
		Assert.assertNotNull(tennisCenter.getPhoneNumber());
		Assert.assertNotNull(tennisCenter.getWebsite());
		Assert.assertNotNull(tennisCenter.getAddress());
		Assert.assertNotNull(tennisCenter.getCreatedOn());
		Assert.assertNotNull(tennisCenter.getImage());
		Assert.assertNotNull(tennisCenter.getLatitude());
		Assert.assertNotNull(tennisCenter.getLongitude());
		Assert.assertNotNull(tennisCenter.getTennisCenterId());
		Assert.assertNotNull(tennisCenter.getTennisCourts());
		Assert.assertEquals(tennisCenter.getTennisCourts().size(), 4);
		for(TennisCourt c : tennisCenter.getTennisCourts()){
			Assert.assertTrue("0 courts of this type",c.getNumberOfCourts().intValue() > 0);
			Assert.assertNotNull("Placement is null",c.getPlacement());
			Assert.assertNotNull(c.getSurface());
			Assert.assertEquals(c.getTennisCenter().getTennisCenterId(), tennisCenter.getTennisCenterId());
		}
		
		Long totalAddressesAfter = (Long)daoManager.getSession().createQuery("select COUNT(*) from Address as a").uniqueResult();
		logger.info("testCreateTennisCenter1: total addresses after " + totalAddressesAfter);
		
		Assert.assertEquals(totalAddresses, totalAddressesAfter);
	}
    
//	@Test
    public void scrollNearBy(){
    	//maxResults=2, firstResult=0, latitude=-73.92251699999997, longitude=40.4244493, distance=50
    	FindByLocationArgs args = new FindByLocationArgs();
    	args.maxResults = 2;
    	args.firstResult = 0;
    	args.latitude = 40.4244493;
    	args.longitude = -73.92251699999997;
    	args.maxDistance = 50d;
    	
    	List<CourtSelect> list = daoManager.nearbyTennisCenters(args);
    	logger.info("THE COURTS " + list);
    	logger.info("THE SIZE: " + daoManager.countNearbyTennisCenters(args.latitude, args.longitude, args.maxDistance));
    }
    
//    12-25 12:13:07.853: D/Tennis SetApp(2614): THE FORM IS SearchTennisCourtsForm [courtName=null, outdoor=on, indoor=on, hard=on, concrete=null, clay=on, grass=null, synthetic=null, carpet=on, latitude=37.4219988, longitude=-122.083954, distance=50]
    
//SearchTennisCourtsArgs [latitude=37.4219988, longitude=-122.083954, distance=0.0, courtName=null, outdoor=true, indoor=true, hard=true, concrete=false, clay=true, grass=false, synthetic=false, carpet=true]
    @Test
    public void searchTennisCenter(){
    	try {
    		SearchTennisCourtsArgs args = new SearchTennisCourtsArgs();
        	args.firstResult = 0;
        	args.maxResults = 100;
        	args.latitude = 37.4219988;
        	args.longitude = -122.083954;
        	
//        	args.courtName = "s";
        	args.indoor = true;
        	args.outdoor = true;
        	
        	args.carpet = true;
        	args.clay = true;
        	args.hard = true;
//        	args.indoor = true;
        	List<CourtSelect> list = daoManager.searchTennisCenters(args);
        	logger.info("LIST " + list.size());
        	logger.info("COUNT: " + daoManager.countSearchTennisCenters(args));
		} catch (Exception exp) {
			logger.error(exp.getMessage(),exp);
		}
    	
    }
    
	public String post(String url){
		String response = restTemplate.postForObject(url,"" ,String.class);
		return response;
	}
}
