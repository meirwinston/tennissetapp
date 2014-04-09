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
	
	@Test
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
	
//	@Test
	public void searchMates(){
		SearchMatesArgs args = new SearchMatesArgs();
		args.playSingles = false;
		args.playDoubles = true;
		args.playFullMatch = false;
		args.playPoints = false;
		args.playHittingAround = false;
		//
		args.latitude = 40.4244357;
		args.longitude = -74.4221905;
		args.levelOfPlayMin = 1.5f;
		args.levelOfPlayMax = 3.5f;
		args.distance = 50d;
		args.firstResult = 0l;
		args.maxResults = 2;
		
//		Object count = daoManager.countNearbyMates(args.latitude,args.longitude,args.distance);
////		Object count = daoManager.countNearbyMates(40.4244357,-74.4221905,50d);
//		logger.info("count is: " + count);
		List<MateSelect> list = daoManager.searchMateItems(args);
		Object count = daoManager.countNearbyMates(args.latitude,args.longitude,args.distance);
		logger.info("size: " + list.size() + ", count is: " + count);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
	}
}
