package tennissetapp.test;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tennissetapp.args.PlayerProfileArgs;
import com.tennissetapp.config.DataConfig;
import com.tennissetapp.config.RootConfig;
import com.tennissetapp.config.SecurityConfig;
import com.tennissetapp.config.SocialConfig;
import com.tennissetapp.config.WebMvcConfig;
import com.tennissetapp.forms.FindByLocationForm;
import com.tennissetapp.forms.PlayerProfileForm;
import com.tennissetapp.persistence.dao.DaoManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
classes={
	DataConfig.class,
	SecurityConfig.class,
	RootConfig.class,
	SocialConfig.class,
	WebMvcConfig.class
})
@Transactional
public class TestDao {
	protected Logger logger = Logger.getLogger(TestMail.class);
	
	@Autowired
	DaoManager daoManager;
	
//	@Test
	public void scrollCourts(){
		FindByLocationForm form = new FindByLocationForm();
//		form.setTerm("Ba");
//		form.setMaxRows(12);
		form.setLatitude("31.75541089999999");
		form.setLongitude("35.20205480000004");
		form.setDistance("200");
		form.setMaxResults("10");
		form.setFirstResult("1");
//		System.out.println(daoManager.scrollTennisCenters(form.toArgs()));
//		System.out.println("size: " + daoManager.scrollTennisCenters(form.toArgs()).size());
		
	}
	
	/*
	 * PlayerProfileForm
	 */
//	@Test
	public void testAccount(){
		PlayerProfileForm f = new PlayerProfileForm();
//		f.setEmail("meirwinston@yahoo.com");
		f.setUserAccountId("1");
		f.setLevelOfPlay("1.5");
		f.setHand("RIGHT");
		f.setSinglesCheck("");
		f.setWeekdayAvailabilityMorningCheck("");
		f.setLatitude("40.4244357");
		f.setLongitude("-74.4221905");
		f.setStreetNumber("548");
		f.setRoute("Ryders Ln");
		f.setRouteShortName("Ryders Ln");
		f.setLocality("East Brunswick");
		f.setLocalityShortName("East Brunswick");
		f.setAdministrativeAreaLevel2("Middlesex");
		f.setAdministrativeAreaLevel2ShortName("Middlesex");
		f.setAdministrativeAreaLevel1("New Jersey");
		f.setAdministrativeAreaLevel1ShortName("NJ");
		f.setCountry("United States");
		f.setCountryShortName("US");
		f.setPostalCode("08816");
//		f.setAddressTypes(new ArrayList<String>(){}); //addressTypes=[street_address]
		
		daoManager.updateAccount((PlayerProfileArgs)f.getArguments(PlayerProfileArgs.class));
	}
	
	
}
