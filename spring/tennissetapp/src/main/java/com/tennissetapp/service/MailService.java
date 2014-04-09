package com.tennissetapp.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class MailService {
	protected Logger logger = Logger.getLogger(MailService.class);
	
	@Inject
	protected JavaMailSender mailSender;
	
	@Inject
	protected VelocityEngine velocityEngine;
	
	@Value("${tennissetapp.mail.username}")
	protected String systemEMail;
	
	@Inject 
	protected ServletContext servletContext;
	
	@Inject
	protected SimpleAsyncTaskExecutor simpleAsyncTaskExecutor;
	
	public MailService(){
//		logger.debug("systemEMail: " + systemEMail);
	}
	
//	public void setMailSender(MailSender mailSender) {
//		this.mailSender = (JavaMailSender)mailSender;
//	}
	public void sendTestMail(){
		this.simpleAsyncTaskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				sendMail("meirwinston@gmail.com","meirwinston@yahoo.com","my subject","my email body");
			}
		});
	}
	private void sendMail(String from, String to, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
		
		logger.info("-COMPLETE--->sendMail " + from + ", " + to + ", " + subject + " , " + msg);
	}
	
	public void sendAsyncMail(final String from, final String to, final String subject, final String msg) {
		this.simpleAsyncTaskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				sendMail(from,to,subject,msg);
			}
		});
	}
	
	public void sendSystemMail(final String to, final String subject, final String msg) {
		this.simpleAsyncTaskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				sendMail(systemEMail,to,subject,msg);
//				sendMail("meirwinston@gmail.com",to,subject,msg);
			}
		});
	}
	
//	public void sendSystemHtmlMail(String to, String subject, String msg) {
//		int attempts = 0;
//		System.out.println("-----> BEFORE attempts: " + attempts);
//		while(attempts < 3){
//			try {
//				sendHTMLMail(Constants.SYSTEM_EMAIL_USERNAME,to,subject,msg);
//				//logger.log(Priority.INFO, "email was sent to " + to);
//				attempts = 3;
//				System.out.println("-----> attempts: " + attempts);
//			} 
//			catch (MessagingException e) {
//				logger.error(e.getMessage(), e);
//			}
//			finally{
//				attempts++;				
//			}
//		}
//			
//	}
	
	public void sendSystemHtmlMail(final String to, final String subject, final String msg) {
		this.simpleAsyncTaskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					sendHTMLMail(systemEMail,to,subject,msg);
				} 
				catch (MessagingException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}
	
//	public void sendWallMessageNotification(NewUserWallRecordArgs args){
//		UserAccount userAccount = StatelessDAO.findUserAccount(args.session, args.senderAccountId);
//		String toEmail = StatelessDAO .selectUserEmail(args.session, args.userAccountId);//
//		if(userAccount != null && toEmail != null){
//			String fullName = userAccount.getFirstName() + " " + userAccount.getLastName();
//			StringBuilder body = new StringBuilder();
//			body.append("<a href='");
//			body.append(Utils.getProperty("branchitup.globalUrl"));
//			body.append("/userprofile?userAccountId=");
//			body.append(args.userAccountId);
//			body.append("'>");
//			body.append(fullName);
//			body.append("</a> left you a message on your wall<br/><br/>");
//			body.append(args.message);
//			
//			sendSystemHtmlMail(toEmail,fullName + " left you a message on your wall",body.toString());
//		}
//		else{
//			logger.error("MailService.sendWallMessageNotification userAccount or toEmail is null. cannot send mail!");
//		}
//	}
	
//	public void sendBookCommentNotification(SubmitBookCommentArgs args){
//		PublishedBook book = StatelessDAO.findPublishedBook(args.session, args.bookId);
//		UserAccount userAccount = StatelessDAO.findUserAccount(args.session, args.userAccountId);
//		if(book != null && userAccount != null){
//			String fullName = userAccount.getFirstName() + " " + userAccount.getLastName();
//			StringBuilder body = new StringBuilder();
//			body.append("<a href='");
//			body.append(Utils.getProperty("branchitup.globalUrl"));
//			body.append("/userprofile?userAccountId=");
//			body.append(args.userAccountId);
//			body.append("'>");
//			body.append(fullName);
//			body.append("</a> commented on your book <a href='");
//			body.append(Utils.getProperty("branchitup.globalUrl"));
//			body.append("/publishedbook?bookId=");
//			body.append(args.bookId);
//			body.append("'>");
//			body.append(book.getTitle());
//			body.append("</a>");
//			
//			body.append("<br/><br/>");
//			body.append(args.comment);
//			sendSystemHtmlMail(book.getPublisherAccount().getEmail(),fullName + " commented on your book '" + book.getTitle() + "'",body.toString());
//		}
//		else{
//			logger.error("MailService.sendBookCommentNotification book or userAccount is null. cannot send mail!");
//		}
//	}
	/**
	 * velocityEngine is configured inRootConfig with root dir '/tennissetapp-env/email-templates' 
	 */
	private void sendEmailByTemplate(String templateFile, String to, String subject,Map<String,Object> model) {
//		logger.debug("sending via velocity...");
		VelocityContext context = new VelocityContext(model);
		StringWriter writer = new StringWriter();
		velocityEngine.getTemplate(templateFile).merge(context, writer);
		
		logger.debug("sendEmailByTemplate evaluated email: \n" + writer.toString());
		
		sendSystemHtmlMail(to, subject, writer.toString());
	}
	
//	private void sendEmailByTemplate(String filePath, String to, String subject, String fullName) {
//		File file = new File(filePath);
//		sendEmailByTemplate(file, to, subject, fullName);
//	}
//	private void sendJoinUsWriterEmail(final String to, final String fullName) {
//		//servletContext.getRealPath("/resources/email-templates/joinus-writer.html")
//		this.simpleAsyncTaskExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
//				sendEmailByTemplate("src/main/webapp/resources/email-templates/joinus-writer.html", to,"New Network for Writers", fullName);
//			}
//		});
//	}
	
	public void sendActivateAccount(final String to, final Map<String,Object> map) {
		this.simpleAsyncTaskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					sendEmailByTemplate("activate-new-account.vm", to,"Welcome to Tennis SetApp", map);
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}
		});
	}
	
//	public void sendResetPassword(final String to, final Map<String,Object> map) {
//		this.simpleAsyncTaskExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					sendEmailByTemplate("activate-new-account.vm", to,"Welcome to Tennis SetApp", map);
//				} catch (Exception e) {
//					logger.error(e.getMessage(),e);
//				}
//			}
//		});
//	}
	
	public synchronized void sendHTMLMail(final String from,final String to,final String subject,final String msg) 
	throws MessagingException {
		MimeMessage mime = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime,true,"UTF-8");
		
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(msg,true);
//		helper.setBcc("meirwinston@yahoo.com");
		
		mailSender.send(mime);
		
	}
	
	public void sendPasswordToUser(String email, String password){
		this.sendSystemMail(email, "Branch It Up Password", "Your password is " + password);
	}
	
	/*
	 * Title of e-mail:  BanchItUp update: NAME OF THE WORK THAT WAS BRANCHED has been branched
Inside the e-mail:
USERNAME has branched up your work, NAME OF WORK!  Click Here to view the new publication.  

	 */
//	public void sendBranchNotification(String to, String parentBookName, String brancherFullName, Long newBookId){
//		String subject = "BanchItUp update: '" + parentBookName + "' has been branched";
//		String content = (brancherFullName + " has branched up your work, " + parentBookName + "! Click <a href='" + Utils.getProperty("branchitup.globalUrl") + "/publishedbook?bookId=" + newBookId + "'>Here</a> to view the new publication.");
//		
//		sendSystemHtmlMail(to,subject,content);
//	}
	
	/*
	 * PublishedBook parentBook = newBook.getParentBook();
				
				System.out.println("The Book ID is :: " + newBook.getBookId() + 
						", parent Book: " + newBook.getParentBook() + ", " + newBook.getParentId());
				if(parentBook != null){
					mailService.sendBranchNotification(parentBook.getPublisherAccount().getEmail(),
							parentBook.getTitle(), 
							newBook.getPublisherAccount().getFirstName() + " " + newBook.getPublisherAccount().getLastName(),
							newBook.getBookId());
				}
	 */
//	public void sendBranchNotification(PublishedBook newBook){
//		PublishedBook parentBook = newBook.getParentBook();
//		while(parentBook != null){
//			StringBuilder subject = new StringBuilder();
//			StringBuilder body = new StringBuilder();
//			
//			subject.append("Your book '");
//			subject.append(parentBook.getTitle());
//			subject.append("' was branched!");
//			
//			body.append("Your book '");
//			body.append(parentBook.getTitle());
//			body.append("' was branched by <a href='http://www.branchitup.com/userprofile?userAccountId=");
//			body.append(newBook.getPublisherAccount().getUserAccountId());
//			body.append("'>");
//			body.append(newBook.getPublisherAccount().getFirstName());
//			body.append(" ");
//			body.append(newBook.getPublisherAccount().getLastName());
//			body.append("</a>. The new version is: <a href='http://www.branchitup.com/publishedbook?bookId=");
//			body.append(newBook.getBookId());
//			body.append("'>");
//			body.append(newBook.getTitle());
//			body.append(" (");
//			body.append(newBook.getVersion());
//			body.append(")</a> ");
//			if(!StringUtils.isEmpty(newBook.getPublisherComment())){
//				body.append("<br/><br/>Publisher Comments: ");
//				body.append(newBook.getPublisherComment());
//			}
////			System.out.println("----SENDING MAIL to " + parentBook.getPublisherAccount().getEmail());
//			sendSystemHtmlMail(parentBook.getPublisherAccount().getEmail(),subject.toString(),body.toString());
//			
//			
//			parentBook = parentBook.getParentBook();
//		}
//	}
	
	public void sendInviteFriend(String to, String brancherFullName, String message){
		String subject = (brancherFullName + " invites you to join Branch It Up.");
		String content = (message + "<br><br>BranchItUp a collaborative web application for writers<br>To sign up for a free account click <a href='http://www.branchitup.com/signup'>here</a> or go to http://www.branchitup.com/signup");
		
		sendSystemHtmlMail(to,subject,content);
	}
}
