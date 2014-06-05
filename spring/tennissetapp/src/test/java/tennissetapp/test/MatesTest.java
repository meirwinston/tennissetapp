package tennissetapp.test;

import java.util.List;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tennissetapp.args.SearchMatesByNameArgs;
import com.tennissetapp.args.SearchMatesArgs;
import com.tennissetapp.config.DataConfig;
import com.tennissetapp.config.RootConfig;
import com.tennissetapp.config.SecurityConfig;
import com.tennissetapp.config.SocialConfig;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.MateSelect;
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
public class MatesTest {
	protected Logger logger = Logger.getLogger(MatesTest.class);
	
	@Inject
	MailService mailService;
	
	@Inject
	DaoManager daoManager;
	
//	@Test
	public void searchFunc(){
		logger.debug("::::" + daoManager.testScalar());
	}
	
//	@Test
	public void searchMatesByUsernameOrEmail(){
		SearchMatesByNameArgs args = new SearchMatesByNameArgs();
//		args.playSingles = false;
//		args.playDoubles = true;
//		args.playFullMatch = false;
//		args.playPoints = false;
//		args.playHittingAround = false;
//		//
//		args.latitude = 40.4244357;
//		args.longitude = -74.4221905;
//		args.levelOfPlayMin = 1.5f;
//		args.levelOfPlayMax = 3.5f;
//		args.distance = 50d;
		args.firstResult = 0l;
		args.maxResults = 10;
		
		args.nameOrEmail = "Meir";
		
		List<MateSelect> list = daoManager.searchMateItems(args);
		Object count = daoManager.countSearchMateByName(args.nameOrEmail);
		logger.info("size: " + list.size() + ", count is: " + count);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
		
		logger.info("list: " + list);
	}
	
	//SELECT * FROM addresses WHERE latitude=42.3753392 AND longitude=-71.0731512;
	@Test
	public void searchMates(){
		SearchMatesArgs a1 = new SearchMatesArgs();
		a1.playSingles = false;
		a1.playDoubles = true;
		a1.playFullMatch = false;
		a1.playPoints = false;
		a1.playHittingAround = false;
		//
		a1.latitude = 42.3753392;
		a1.longitude = -71.0731512;
		a1.levelOfPlayMin = 1.5f;
		a1.levelOfPlayMax = 3.5f;
		a1.distance = 50d;
		a1.firstResult = 0l;
		a1.maxResults = 10;
		
//		Object count = daoManager.countNearbyMates(args.latitude,args.longitude,args.distance);
////		Object count = daoManager.countNearbyMates(40.4244357,-74.4221905,50d);
//		logger.info("count is: " + count);
		List<MateSelect> list = daoManager.searchMateItems(a1);
		Object count = daoManager.countNearbyMates(a1.latitude,a1.longitude,a1.distance);
		logger.info("size: " + list.size() + ", count is: " + count);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
	}
}
