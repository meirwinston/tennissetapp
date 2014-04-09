package tennissetapp.test;

import java.util.Calendar;
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
import com.tennissetapp.args.CalendarEventsArgs;
import com.tennissetapp.config.DataConfig;
import com.tennissetapp.config.RootConfig;
import com.tennissetapp.config.SecurityConfig;
import com.tennissetapp.config.SocialConfig;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.CalendarEventSelect;

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
public class CalendarTest {
	Logger logger = Logger.getLogger(getClass());
	
	@Inject
	DaoManager daoManager;
	
	@Test
    @Transactional
    public void findCalendarEvents(){
    	try {
    		Calendar cal1 = Calendar.getInstance();
    		cal1.set(2000, 5, 1);
    		
    		Calendar cal2 = Calendar.getInstance();
    		cal2.set(2020, 2, 1);
    		
    		CalendarEventsArgs args = new CalendarEventsArgs();
    		
    		args.fromDate = cal1.getTime();
    		args.toDate = cal2.getTime();
    		args.userAccountId = 57l;
    		
    		List<CalendarEventSelect> list = daoManager.getCalendarEvents(args);
    		logger.info("calendar list " + list);
    		
    		logger.info("next " + daoManager.getNextCalendarEvent(args));
		} catch (Exception exp) {
			logger.error(exp.getMessage(),exp);
			Assert.fail();
		}
    	
    }
}
