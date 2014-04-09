package tennissetapp.test;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.tennissetapp.args.CreateFavoriteTeacherArgs;
import com.tennissetapp.args.ScrollByUserAccountIdArgs;
import com.tennissetapp.config.DataConfig;
import com.tennissetapp.config.RootConfig;
import com.tennissetapp.config.SecurityConfig;
import com.tennissetapp.config.SocialConfig;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.FavoriteTeacher;
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
public class FavoriteTeacherTest {
	protected Logger logger = Logger.getLogger(TeachersTest.class);
		
	@Inject
	DaoManager daoManager;

//	@Test
	@Transactional
	public void create() throws Exception{
		try {
			CreateFavoriteTeacherArgs args = new CreateFavoriteTeacherArgs();
			args.userAccountId = 57l;
			args.teacherProfileId = 63l;
			args.createdOn = new Date();
			FavoriteTeacher t = daoManager.createFavoriteTeacher(args);
			
			logger.info("the new favorite teacher is " + t);
			Assert.notNull(t);
			
		} 
		catch (Exception exp) {
			logger.error(exp.getMessage(),exp);
			throw exp;
		}
	}
	
	@Test
	@Transactional
	public void find() throws Exception{
		try {
			ScrollByUserAccountIdArgs args = new ScrollByUserAccountIdArgs();
			args.firstResult = 0;
			args.maxResults = 10;
			args.userAccountId = 57l;
			List<TeacherSelect> list = daoManager.findFavoriteTeachers(args);
			
			logger.info("favorite teachers " + list);
			Assert.isTrue(list.size() > 0);
			
		} 
		catch (Exception exp) {
			logger.error(exp.getMessage(),exp);
			throw exp;
		}
		
	}
	
//	@Test
	@Transactional
	public void delete() throws Exception{
		try {
			
			int rowsUpdated = daoManager.removeFavoriteTeacher(57l,62l);
			
			logger.info("delete favorite teacher " + rowsUpdated);
			Assert.isTrue(rowsUpdated > 0);
			
		} 
		catch (Exception exp) {
			logger.error(exp.getMessage(),exp);
			throw exp;
		}
		
	}
}
