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

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
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
	
	
	 private final String regId = "APA91bFAD3zbyURohBfalTwCudULgZgRzjadjbY4ZW9lUZh7jcVrAxM10iWMsalxL0Px18r48NSZElRrMCrs46AGplWqL21HFQc1hI_0646fNtBAiskReP852uM2ahVk5Eb67SZwC6rdOgwRavNOyh6_5ZT6ItrASA";
	  private final String projectNumber = "637067041525";
	  String apiKey="AIzaSyDx9Ja9DFGwTtTdA1PdwXubWc7Uen6SngQ";
	Sender sender = new Sender(apiKey);
	
	
//	05-22 05:56:05.026    1495-1516/com.tennissetapp I/GCM Demo? Received: Bundle[{k3=v3, android.support.content.wakelockid=1, collapse_key=do_not_collapse, from=637067041525, k1=v1, k2=v2}]
//			05-22 05:58:10.122    1495-1495/com.tennissetapp D/GcmBroadcastReceiver? GcmBroadcastReceiver: **********onReceive Intent { act=com.google.android.c2dm.intent.RECEIVE flg=0x10 pkg=com.tennissetapp cmp=com.tennissetapp/.message.GcmBroadcastReceiver (has extras) }
//			05-22 05:58:10.161    1495-1517/com.tennissetapp I/GCM Demo? Working... 1/5 @ 4163952
//			05-22 05:58:15.172    1495-1517/com.tennissetapp I/GCM Demo? Working... 2/5 @ 4168955
//			05-22 05:58:20.176    1495-1517/com.tennissetapp I/GCM Demo? Working... 3/5 @ 4173959
//			05-22 05:58:25.179    1495-1517/com.tennissetapp I/GCM Demo? Working... 4/5 @ 4178962
//			05-22 05:58:30.182    1495-1517/com.tennissetapp I/GCM Demo? Working... 5/5 @ 4183965
//			05-22 05:58:35.185    1495-1517/com.tennissetapp I/GCM Demo? Completed work @ 4188968
//			05-22 05:58:35.225    1495-1517/com.tennissetapp I/GCM Demo? Received: Bundle[{content=This is my message, k3=v3, android.support.content.wakelockid=2, collapse_key=do_not_collapse, from=637067041525, toUserAccountId=123}]

	@Test
	public void sendGcmMessage(){
		try {
			logger.info("sendGcmMessage");
			Message message =
				      new Message.Builder()
//				          .collapseKey(collapseKey)
				          .delayWhileIdle(true)
				          .dryRun(true)
//				          .restrictedPackageName("com.tennissetapp")
				          .timeToLive(108)
//				          .addData("k0", null)
//				          .addData(null, "v0")
				          .addData("content", "This is my message")
				          .addData("toUserAccountId", "123")
				          .addData("k3", "v3")
				          .build();

			Result result = sender.send(message, regId, 5);
			logger.info("THIS IS THE RESULTS: " + result);
		} catch (Exception exp) {
			logger.error(exp.getMessage(), exp);
		}
	}
	
//	@Test
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

