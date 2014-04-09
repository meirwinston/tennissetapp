package tennissetapp.test;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tennissetapp.config.DataConfig;
import com.tennissetapp.config.RootConfig;
import com.tennissetapp.config.SecurityConfig;
import com.tennissetapp.config.SocialConfig;
import com.tennissetapp.forms.PlayerProfileForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.service.MailService;

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
public class ProfileTest {
	protected Logger logger = Logger.getLogger(MatesTest.class);
	
	@Inject
	MailService mailService;
	
	@Inject
	DaoManager daoManager;
	
	public void f(){
		PlayerProfileForm f = new PlayerProfileForm();
//		f.setAddressTypes();;
	}
	
/*
 * addressTypes[]	
addressTypes[]	
administrativeAreaLevel1	
administrativeAreaLevel1S...	
administrativeAreaLevel2	
administrativeAreaLevel2S...	
country	
countryShortName	
doublesCheck	
favouriteCourts	
geolocation	
hand	RIGHT
latitude	
levelOfPlay	1.5
localityShortName	
locality[]	
locality[]	
locality[]	
longitude	
political	
politicalShortName	
postalCode	
postalCodeShortName	
route	
routeShortName	
singlesCheck	
streetNumber	
streetNumberShortName	
userAccountId	
weekdayAvailabilityMornin...	
weekendAvailabilityAftern...	
 */
}
