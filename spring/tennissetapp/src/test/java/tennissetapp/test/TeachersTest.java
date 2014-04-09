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

import com.tennissetapp.args.FindByLocationArgs;
import com.tennissetapp.args.SearchByNameOrEmailArgs;
import com.tennissetapp.args.SearchTennisTeachersArgs;
import com.tennissetapp.config.DataConfig;
import com.tennissetapp.config.RootConfig;
import com.tennissetapp.config.SecurityConfig;
import com.tennissetapp.config.SocialConfig;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.TeacherSelect;

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
public class TeachersTest {
	protected Logger logger = Logger.getLogger(TeachersTest.class);
	
	@Inject
	DaoManager daoManager;
	
//	@Test
	public void nearbyTennisTeachers(){
		try {
			FindByLocationArgs args = new FindByLocationArgs();
			args.latitude = 40.4244357;
			args.longitude = -74.4221905;
			args.maxDistance = 50;
			args.firstResult = 0;
			args.maxResults = 10;
			
			
			List<TeacherSelect> list = daoManager.findNearbyTennisTeachers(args);
			Long count = daoManager.countNearbyTeachers(args.latitude, args.longitude, args.maxDistance);
			logger.info("size: " + list.size() + ", count is: " + count);
//			logger.info("size: " + list.size());
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);
			Assert.assertTrue(list.size() == count);
			
			logger.info("list: " + list);
		} catch (Exception exp) {
			logger.error(exp.getMessage(), exp);
			Assert.fail(exp.getMessage());
		}
	}
	
////hersForm [latitude=-74.4221905, longitude=40.4244357, availableWeekendMorning=null, availableWeekendAfternoon=null, availableWeekendEvening=null, availableWeekdayMorning=null, availableWeekdayAfternoon=null, availableWeekdayEvening=null, distance=50, currency=USD, hourlyRate=null, teacherCertified=true, specialtyJuniors=true, specialtyAdults=null, specialtyTurnaments=null]
//	@Test
	public void search2(){
		try {
			SearchTennisTeachersArgs args = new SearchTennisTeachersArgs();
			args.latitude = 40.4244357;
			args.longitude = -74.4221905;
			args.maxDistance = 50d;
			args.firstResult = 0;
			args.maxResults = 10;
			args.specialtyAdults = true;
			
			List<TeacherSelect> list = daoManager.searchTennisTeachers(args);
			Long count = daoManager.countSearchTennisTeachers(args);
			logger.info("size: " + list.size() + ", count is: " + count);
//			logger.info("size: " + list.size());
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);
			Assert.assertTrue(list.size() == count);
			
			logger.info("list: " + list);
		} catch (Exception exp) {
			logger.error(exp.getMessage(), exp);
			Assert.fail(exp.getMessage());
		}
	}
	
	@Test
	public void searchByNameOrEmail(){
		try {
			SearchByNameOrEmailArgs args = new SearchByNameOrEmailArgs();
			args.latitude = 40.4244357;
			args.longitude = -74.4221905;
			args.firstResult = 0;
			args.maxResults = 10;
			args.nameOrEmail = "me";
			
			List<TeacherSelect> list = daoManager.searchTennisTeachers(args);
			Long count = daoManager.countTennisTeachers(args);
			logger.info("size: " + list.size() + ", count is: " + count);
//			logger.info("size: " + list.size());
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);
			Assert.assertTrue(list.size() == count);
			
			logger.info("list: " + list);
		} catch (Exception exp) {
			logger.error(exp.getMessage(), exp);
			Assert.fail(exp.getMessage());
		}
	}
	
//	@Test
	public void search1(){
		try {
			SearchTennisTeachersArgs args = new SearchTennisTeachersArgs();
			args.latitude = 40.4244357;
			args.longitude = -74.4221905;
			args.maxDistance = 50d;
			args.firstResult = 0;
			args.maxResults = 10;
			args.specialtyAdults = true;
			args.currency = "USD";
			args.clinicRates = 56.0;
			
			List<TeacherSelect> list = daoManager.searchTennisTeachers(args);
			Long count = daoManager.countSearchTennisTeachers(args);
			logger.info("size: " + list.size() + ", count is: " + count);
//			logger.info("size: " + list.size());
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);
			Assert.assertTrue(list.size() == count);
			
			logger.info("list: " + list);
		} catch (Exception exp) {
			logger.error(exp.getMessage(), exp);
			Assert.fail(exp.getMessage());
		}
	}
	
//	@Test
//	public void searchMatesByUsernameOrEmail(){
//		SearchMatesByNameArgs args = new SearchMatesByNameArgs();
////		args.latitude = 40.4244357;
////		args.longitude = -74.4221905;
////		args.levelOfPlayMin = 1.5f;
////		args.levelOfPlayMax = 3.5f;
////		args.distance = 50d;
//		args.firstResult = 0l;
//		args.maxResults = 10;
//		
//		args.nameOrEmail = "Meir";
//		
//		List<MateSelect> list = daoManager.searchMateItems(args);
//		Object count = daoManager.countSearchMateByName(args.nameOrEmail);
//		logger.info("size: " + list.size() + ", count is: " + count);
//		Assert.assertNotNull(list);
//		Assert.assertTrue(list.size() > 0);
//		
//		logger.info("list: " + list);
//	}
}

