package tennissetapp.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;
import com.tennissetapp.config.DataConfig;
import com.tennissetapp.config.RootConfig;
import com.tennissetapp.config.SecurityConfig;
import com.tennissetapp.config.SocialConfig;
import com.tennissetapp.json.JacksonUtils;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.Address;
import com.tennissetapp.persistence.entities.ImageFile;
import com.tennissetapp.persistence.entities.MatchMember;
import com.tennissetapp.persistence.entities.TennisCenter;
import com.tennissetapp.util.ImageMetaData;

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
public class AddressTest {
	protected Logger logger = Logger.getLogger(AddressTest.class);
	
	
	@Autowired
	private DaoManager daoManager;
	
	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	TransactionTemplate tx;
	
	private RestTemplate restTemplate = new RestTemplate();
	
    @Before
    public void setup() {
    	tx = new TransactionTemplate(platformTransactionManager);
        //call login
//        restTemplate.postForObject("http://localhost:8080/tennissetapp/j_spring_security_check?j_username=meirwinston@yahoo.com&j_password=111111","" ,String.class);
    }
    
//    @Test
    public void writeAddressMapObjectToDisk(){
    	try {
    		Session session = daoManager.getNewSession();
        	Query query = session.createSQLQuery("SELECT latitude,longitude FROM engine4_court_courts");
        	List<Object[]> list = query.list();
        	Map<String,Map<?,?>> map = Maps.newHashMap();
        	for(Object[] o : list){
        		String key = o[0] + "," + o[1];
        		if(!map.containsKey(key)){
        			Map<?,?> address = restTemplate.getForObject("http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=" + key, Map.class);
        			map.put(key, address);
        			logger.info("--- " + key);
        			Thread.sleep(50);
        		}
        	}
        	File file = new File("/home/mwinston/courts_java_hashmap_object");
        	if(!file.exists()){
        		file.createNewFile();
        	}
        	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        	out.writeObject(map);
        	out.close();
        	session.close();
     	
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    }
    /*
     * {results=[{address_components=[{long_name=156, short_name=156, types=[street_number]}, {long_name=Aptos Avenue, short_name=Aptos Ave, types=[route]}, {long_name=Balboa Terrace, short_name=Balboa Terrace, types=[neighborhood, political]}, {long_name=San Francisco, short_name=SF, types=[locality, political]}, {long_name=San Francisco County, short_name=San Francisco County, types=[administrative_area_level_2, political]}, {long_name=California, short_name=CA, types=[administrative_area_level_1, political]}, {long_name=United States, short_name=US, types=[country, political]}, {long_name=94127, short_name=94127, types=[postal_code]}], formatted_address=156 Aptos Avenue, San Francisco, CA 94127, USA, geometry={location={lat=37.72912, lng=-122.46719}, location_type=ROOFTOP, viewport={northeast={lat=37.7304689802915, lng=-122.4658410197085}, southwest={lat=37.7277710197085, lng=-122.4685389802915}}}, types=[street_address]}, {address_components=[{long_name=Mount Davidson Manor, short_name=Mount Davidson Manor, types=[neighborhood, political]}, {long_name=San Francisco, short_name=SF, types=[locality, political]}, {long_name=San Francisco County, short_name=San Francisco County, types=[administrative_area_level_2, political]}, {long_name=California, short_name=CA, types=[administrative_area_level_1, political]}, {long_name=United States, short_name=US, types=[country, political]}], formatted_address=Mount Davidson Manor, San Francisco, CA, USA, geometry={bounds={northeast={lat=37.732031, lng=-122.4602752}, southwest={lat=37.7249185, lng=-122.4677137}}, location={lat=37.7283105, lng=-122.4630004}, location_type=APPROXIMATE, viewport={northe
     */
//    @Test
    public void fix(){
    	try {
    		File file = new File("/home/mwinston/courts_java_hashmap_object2");
    		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
    		Map<String,Map<?,?>> map = (Map)in.readObject();
    		in.close();
    		int i = 0;
    		for(String key : map.keySet()){
    			Map<?,?> address = map.get(key);
    			if("OVER_QUERY_LIMIT".equals(address.get("status"))){
    				i++;
    				logger.info(i + ") " + address.get("error_message"));
    				
        			Map<?,?> addressAgain = restTemplate.getForObject("http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=" + key, Map.class);
        			map.put(key, addressAgain);
        			logger.info("--- " + key);
        			Thread.sleep(200);
    			}
    		}
    		
    		file = new File("/home/mwinston/courts_java_hashmap_object3");
        	if(!file.exists()){
        		file.createNewFile();
        	}
    		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        	out.writeObject(map);
        	out.close();
    		
		} 
    	catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	
    }
    
//    @Test
    public void testConnection(){
    	try {
			logger.info("-->" + daoManager.findUserAccountIdByEmail("meirwinston@yahoo.com"));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    }
    
//    @Test
    public void writeAddressesFromMapObject(){
    	try {
    		File file = new File("/home/mwinston/courts_java_hashmap_object3");
    		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
    		Map<String,Map<?,?>> map = (Map)in.readObject();
    		in.close();
    		int i = 0;
    		int j = 0;
    		for(String key : map.keySet()){
    			Map<?,?> address = map.get(key);
    			if("OVER_QUERY_LIMIT".equals(address.get("status"))){
    				i++;
    				logger.info(i + ") " + address.get("error_message"));
    			}
    			else{
    				List<Map> list = (List)address.get("results");
    				if(list.size() <= 0 ) continue;
    				List<Map> comList = (List)list.get(0).get("address_components");
    				Map geo = (Map)((Map)list.get(0).get("geometry")).get("location"); 
    				final Address a = new Address();
    				for(Map<String,Object> m : comList){
    					if(((List)m.get("types")).contains("administrative_area_level_1")){
    						a.setAdministrativeAreaLevel1(m.get("long_name").toString());
            				a.setAdministrativeAreaLevel1ShortName(m.get("short_name").toString());
    					}
    					if(((List)m.get("types")).contains("administrative_area_level_2")){
    						a.setAdministrativeAreaLevel2(m.get("long_name").toString());
            				a.setAdministrativeAreaLevel2ShortName(m.get("short_name").toString());
    					}
        				
    					if(((List)m.get("types")).contains("political")){
    						a.setPolitical(m.get("long_name").toString());
            				a.setPoliticalShortName(m.get("short_name").toString());
    					}
        				
    					if(((List)m.get("types")).contains("country")){
    						a.setCountry(m.get("long_name").toString());
            				a.setCountryShortName(m.get("short_name").toString());
    					}
        				
        				a.setCreatedOn(new Date());
        				
        				a.setLatitude(Double.valueOf(geo.get("lat").toString()));
        				if(((List)m.get("types")).contains("locality")){
        					a.setLocality(m.get("long_name").toString());
            				a.setLocalityShortName(m.get("short_name").toString());
        				}
        				
        				a.setLongitude(Double.valueOf(geo.get("lng").toString()));
        				if(((List)m.get("types")).contains("neighborhood")){
        					a.setNeighborhood(m.get("long_name").toString());
            				a.setNeighborhoodShortName(m.get("short_name").toString());
        				}
        				
        				if(((List)m.get("types")).contains("postal_code")){
        					a.setPostalCode(m.get("short_name").toString());
        				}
        				
        				if(((List)m.get("types")).contains("route")){
        					a.setRoute(m.get("long_name").toString());
            				a.setRouteShortName(m.get("short_name").toString());
        				}
        				
        				if(((List)m.get("types")).contains("street_number")){
        					a.setStreetNumber(m.get("short_name").toString());
        				}
        				
        				if(((List)m.get("types")).contains("sublocality")){
        					a.setSublocality(m.get("long_name").toString());
            				a.setSublocalityShortName(m.get("short_name").toString());        					
        				}
    				}
    					
    				try {
    					logger.info("index: " + (j++));
    					
    					daoManager.persist(a);
						logger.info("address persisted: " + a);
    					
//    					tx.execute(new TransactionCallbackWithoutResult() {
//    						@Override
//    						public void doInTransactionWithoutResult(TransactionStatus status) {
//    							daoManager.persist(a);
//    							logger.info("address persisted: " + a);
//    						}
//    					});
    					
    					Thread.sleep(100);
    					
					} catch (Exception e) {
						logger.error(e.getMessage(),e);
					}
    				
    			}
    		}
		} 
    	catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    }
    
    //-------------------
    
    
    //----------------
    
    //http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=40.7215618,-73.95387
    public GeoCodeResult googleGeocode(String input) {
		GeoCodeResult geoCode = null;
		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try {
			StringBuilder sb = new StringBuilder("http://maps.googleapis.com/maps/api/geocode/json?sensor=false");
			sb.append("&address=" + URLEncoder.encode(input, "utf8"));

			logger.info("URL " + sb);
			URL url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			// Load the results into a StringBuilder
			int read;
			char[] buff = new char[2048];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}
		} catch (MalformedURLException e) {
			logger.error("Error processing Places API URL", e);
			return geoCode;
		} catch (IOException e) {
			logger.error("Error connecting to Places API", e);
			return geoCode;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		try {
			geoCode = JacksonUtils.deserializeAs(jsonResults.toString(), GeoCodeResult.class);
		} catch (Exception e) {
			logger.error("Cannot process JSON results", e);
		}
		return geoCode;
	}
    
    @Test
    public void putAddress(){
    	logger.info("start");
    	try {
    		GeoCodeResult geoCode;
//    		geoCode = googleGeocode("42.374778,-71.072388"); //botson
//    		geoCode = googleGeocode("40.212441,-74.776612"); //trenton
//    		geoCode = googleGeocode("40.446947,-80.006104"); //pitsburg
    		geoCode = googleGeocode("40.730608,-74.007569"); //NY
    		
    		if(geoCode != null){
    			
    			for(GeoCodeResult.Result r : geoCode.results){
    				Address a = new Address();
    				a.setLatitude(r.geometry.location.lat);
    				a.setLongitude(r.geometry.location.lng);
    				a.setCreatedOn(new Date());
    				
    				for(GeoCodeResult.AddressComponent c : r.address_components){
    					
    					if(c.types.contains("administrative_area_level_1")){
        					a.setAdministrativeAreaLevel1(c.long_name);
            				a.setAdministrativeAreaLevel1ShortName(c.short_name);
        				}
        				if(c.types.contains("administrative_area_level_2")){
        					a.setAdministrativeAreaLevel2(c.long_name);
            				a.setAdministrativeAreaLevel2ShortName(c.short_name);
        				}
        				
        				if(c.types.contains("political")){
        					a.setPolitical(c.long_name);
            				a.setPoliticalShortName(c.short_name);
        				}
        				
        				if(c.types.contains("country")){
        					a.setCountry(c.long_name);
            				a.setCountryShortName(c.short_name);
        				}
        				
        				if(c.types.contains("locality")){
        					a.setLocality(c.long_name);
            				a.setLocalityShortName(c.short_name);
        				}
        				
        				if(c.types.contains("neighborhood")){
        					a.setNeighborhood(c.long_name);
            				a.setNeighborhoodShortName(c.short_name);
        				}
        				
        				if(c.types.contains("postal_code")){
        					a.setPostalCode(c.long_name);
        				}
        				
        				if(c.types.contains("route")){
        					a.setRoute(c.long_name);
            				a.setRouteShortName(c.short_name);
        				}
        				
        				if(c.types.contains("street_number")){
        					a.setStreetNumber(c.long_name);
        				}
        				
        				if(c.types.contains("sublocality")){
        					a.setSublocality(c.long_name);
            				a.setSublocalityShortName(c.short_name);        					
        				}	
    				}
    				daoManager.insertAddress(a, 0);	    				
    			}
    				
    		}
    		
		} 
    	catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    }
    
    //-------------
    
//  @Test
    public void countMapObjectSize(){
    	try {
    		File file = new File("/home/mwinston/courts_java_hashmap_object3");
    		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
    		Map<String,Map<?,?>> map = (Map)in.readObject();
    		in.close();
    		logger.info("SIZE " + map.size());
    	} 
    	catch (Exception e) {
    		logger.error(e.getMessage(),e);
    	}
    }
    
//    @Test
    public void insertCourts(){
    	try {
    		Session session = daoManager.getNewSession();
        	Query query = session.createSQLQuery("SELECT * FROM engine4_court_courts");
        	List<Object[]> list = (List)query.list();
        	int i = 0;
        	String s;
        	for(Object[] o : list){
        		
        		try {
        			if(StringUtils.isEmpty((String)o[6]) || StringUtils.isEmpty((String)o[7])){
            			continue;
            		}
            		
            		TennisCenter c = new TennisCenter();
            		c.setCreatedOn(new Date());
            		s = (String)o[2];
            		if(StringUtils.isNotEmpty(s)){
            			c.setName(s);
            		}
            		
            		c.setLatitude(Double.valueOf(o[6].toString()));
            		c.setLongitude(Double.valueOf(o[7].toString()));
            		
            		s = (String)o[16];
            		if(StringUtils.isNotEmpty(s)){
            			c.setPhoneNumber(s.trim());
            		}
            		
            		s = (String)o[17];
            		if(StringUtils.isNotEmpty(s)){
            			c.setWebsite(s.trim());
            		}
        			daoManager.persist(c);
        			logger.info((i++) + ")tennis center persisted" + c);
				} 
        		catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
        		
        		
        		Thread.sleep(100);
        	}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	
    }
    
//    @Test
    public void storeCourtsImages(){
    	try {
    		final Session session = daoManager.getNewSession();
    		Query query = session.createQuery("SELECT c.latitude,c.longitude FROM TennisCenter AS c where c.imageFileId is null");
    		List<Object[]> list = query.list();
    		for(final Object[] c : list){
    			final Double lat = Double.valueOf(c[0].toString());
    			final Double lng = Double.valueOf(c[1].toString());
    			ImageMetaData meta = storeCourtMapFromGoogle(lat,lng);
    			
    			
    			if(meta != null){
    				final ImageFile imageFile = new ImageFile();
    				imageFile.setCreatedOn(new Date());
    				imageFile.setDirPath(meta.getDirPath());
    				imageFile.setFileName(meta.getFileName());
    				imageFile.setFormat(ImageFile.Format.valueOf(meta.getFormat()));
    				imageFile.setHeight(meta.getHeight());
    				imageFile.setWidth(meta.getWidth());
    				imageFile.setSize(meta.getSize());
    				
    				session.persist(imageFile);
    				session.flush();
    				
    				Query q = session.createQuery("update TennisCenter as c set c.imageFileId = " + imageFile.getImageFileId() + " where c.latitude=" + lat + " and c.longitude=" + lng);
    				int updatedCount = q.executeUpdate();
    				logger.info("Persisted " + c[0] +", " + c[1] + ", " + updatedCount);
    				session.flush();
    			}
    			Thread.sleep(500);
//    			break;
    		}
    		
        	session.close();
		} 
    	catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    }
    public ImageMetaData storeCourtMapFromGoogle(Double lat, Double lng) throws MalformedURLException, IOException{
    	int width = 512, height = 512;
		String url = "http://maps.googleapis.com/maps/api/staticmap?center=" + 
				lat + "," + lng + "&zoom=17&size=" + width + "x" + height + 
				"&maptype=roadmap&markers=color:red%7Ccolor:red%7Clabel:C%7C" + lat + "," + lng + "&sensor=false&format=jpg";
		//-23.6817851, -46.7098259
		
		String dirPath = "/data/tennissetapp_env/diskresources/images/TENNIS_COURTS";
		String fileFullPath = dirPath;
				
//		String dirPath = RootConfig.props.getProperty("tennissetapp.diskresources.imagesPath") + "/" + ImageFile.SystemFolder.TENNIS_COURTS;
//		String fileFullPath = RootConfig.props.getProperty("tennissetapp.rootDir") + "/" + dirPath;
		
		File file = new File(fileFullPath + "/" + UUID.randomUUID() + "." + ImageFile.Format.JPG);
		FileOutputStream out = new FileOutputStream(file);
		logger.debug("FETCHING IMAGE FROM GOOGLE URL: " + url);
		org.apache.commons.io.IOUtils.copy(new URL(url).openStream(),out);
		
		out.close();
		
		ImageMetaData m = new ImageMetaData();
		m.width = width;
		m.height = height;
		m.format = ImageFile.Format.JPG.toString();
		m.fileName = file.getName();
		m.absolutePath = file.getAbsolutePath();
		m.dirPath = dirPath;
		m.size = file.length();
		
		return m;
	}
}