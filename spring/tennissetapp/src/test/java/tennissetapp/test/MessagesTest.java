package tennissetapp.test;

import java.io.IOException;
import java.util.Date;
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

import com.tennissetapp.args.ScrollByMateAccountIdArgs;
import com.tennissetapp.args.ScrollMessagesArgs;
import com.tennissetapp.args.SearchMatesByNameArgs;
import com.tennissetapp.args.SearchMatesArgs;
import com.tennissetapp.config.DataConfig;
import com.tennissetapp.config.RootConfig;
import com.tennissetapp.config.SecurityConfig;
import com.tennissetapp.config.SocialConfig;
import com.tennissetapp.json.JacksonUtils;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.MateSelect;
import com.tennissetapp.persistence.entities.UserPost;
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
public class MessagesTest {
	protected Logger logger = Logger.getLogger(MessagesTest.class);
	
	@Inject
	MailService mailService;
	
	@Inject
	DaoManager daoManager;
	
	@Test
	public void searchMatesByUsernameOrEmail(){
		
		ScrollByMateAccountIdArgs args = new ScrollByMateAccountIdArgs();
		args.firstResult = 0l;
		args.maxResults = 10;
		
		args.userAccountId = 57l;
		args.mateAccountId = 63l;
//		args.startDate = new Date(1393898742000l);
		args.startDate = new Date(1393898742000l);
		logger.info("ARGS " + args);
		List<UserPost> list = daoManager.scrollMateConversation(args);
//		Assert.assertNotNull(list);
//		Assert.assertTrue(list.size() > 0);
		
		logger.info("list: " + list.size());
		try {
			for(UserPost u : list){
				logger.info("--- " + u.getUserPostId());
			}
//			logger.info("serialized " + JacksonUtils.serialize(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

