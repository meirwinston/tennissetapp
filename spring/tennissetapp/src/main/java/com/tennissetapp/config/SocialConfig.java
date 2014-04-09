package com.tennissetapp.config;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.config.annotation.EnableFacebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import com.tennissetapp.auth.SignInAdapterImpl;
import com.tennissetapp.service.FacebookService;
import com.tennissetapp.service.TwitterService;

import org.springframework.social.config.annotation.EnableJdbcConnectionRepository;
import org.springframework.social.twitter.config.annotation.EnableTwitter;

@EnableJdbcConnectionRepository
@EnableTwitter(appId="${tennissetapp.twitter.consumerKey}", appSecret="${tennissetapp.twitter.consumerSecret}")
@EnableFacebook(appId="${tennissetapp.facebook.clientId}", appSecret="${tennissetapp.facebook.clientSecret}")
@Configuration
public class SocialConfig {
	protected Logger logger = Logger.getLogger(getClass());
	
	@Inject 
	protected DataConfig dataConfig;
	
//	@Inject 
//	protected DaoConfig daoConfig;
	
	@Value("${tennissetapp.twitter.consumerKey}")
	private String twitterCustomerKey;
	
	@Value("${tennissetapp.twitter.consumerSecret}")
	private String twitterCustomerSecret;
	
	@Value("${tennissetapp.facebook.clientId}")
	private String facebookClientId;
	
	@Value("${tennissetapp.facebook.clientSecret}")
	private String facebookClientSecret;
	
	@Value("${tennissetapp.facebook.appNamespace}")
	private String facebookAppNamespace;
	
	@Bean
	public UserIdSource userIdSource() {
		return new UserIdSource() {			
			@Override
			public String getUserId() {
//				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//				if (authentication == null) {
//					throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
//				}
//				return authentication.getName();
				
				return "meirwinston"; //TODO
			}
		};
	}
	
	@Bean
	public FacebookConnectionFactory facebookConnectionFactory(){
		return new FacebookConnectionFactory(facebookClientId, facebookClientSecret);
	}
	
	@Bean
	public TwitterConnectionFactory twitterConnectionFactory(){
		return new TwitterConnectionFactory(twitterCustomerKey,twitterCustomerSecret);
	}
	
	@Bean
	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES) 
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(twitterConnectionFactory());
		registry.addConnectionFactory(facebookConnectionFactory());
		return registry;
	}
	
	@Bean
	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
	public UsersConnectionRepository usersConnectionRepository(){
		return new JdbcUsersConnectionRepository(dataConfig.dataSource(),connectionFactoryLocator(),textEncryptor());
	}
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public ConnectionRepository connectionRepository() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
		}
		return usersConnectionRepository().createConnectionRepository(authentication.getName());
	}
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public Facebook facebook() {
		logger.debug("--->facebook.connection ");
		Connection<Facebook> facebook = connectionRepository().findPrimaryConnection(Facebook.class);
		return facebook != null ? facebook.getApi() : new FacebookTemplate();
	}
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public Twitter twitter() {
		logger.debug("--->twitter.connection ");
//		Connection<Twitter> twitter = usersConnectionRepository().createConnectionRepository("meirwinston@yahoo.com").findPrimaryConnection(Twitter.class);
		Connection<Twitter> twitter = connectionRepository().findPrimaryConnection(Twitter.class);
		
		
		return twitter != null ? twitter.getApi() : new TwitterTemplate();
	}
	
	@Bean
	public ConnectController connectController() {
//		logger.debug("--->SocialConfig.connectController");
		ConnectController connectController = new ConnectController(connectionFactoryLocator(), connectionRepository());
//		connectController.addInterceptor(new PostToWallAfterConnectInterceptor());
//		connectController.addInterceptor(new TweetAfterConnectInterceptor());
		return connectController;
	}
	
	@Bean 
	public FacebookService facebookService(){
		FacebookService fs = new FacebookService();
		return fs;
	}
	
	@Bean 
	public TwitterService twitterService(){
		TwitterService ts = new TwitterService();
		return ts;
	}
	
	@Bean
	public SignInAdapter signInAdapter() {
		RequestCache requestCache = new HttpSessionRequestCache();  
		SignInAdapterImpl signInAdapter = new SignInAdapterImpl(requestCache);
		signInAdapter.setFacebookService(facebookService());
		signInAdapter.setTwitterService(twitterService());
		return signInAdapter;
	}

	@Bean
	public ProviderSignInController providerSignInController() {
//		logger.debug("--->SocialConfig.providerSignInController ");
		return new ProviderSignInController(connectionFactoryLocator(), usersConnectionRepository(), signInAdapter());
	}
	
//	@Bean
//	public DisconnectController disconnectController() {
//		return new DisconnectController(usersConnectionRepository(), facebookClientSecret);
//	}
	
	@Bean
	public TextEncryptor textEncryptor(){
		return Encryptors.noOpText();
	}
}
