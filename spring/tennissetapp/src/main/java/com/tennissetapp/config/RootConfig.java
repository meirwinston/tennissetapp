package com.tennissetapp.config;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mock.web.MockServletContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import com.tennissetapp.service.MailService;
import com.tennissetapp.util.EnvironmentProperties;

@ComponentScan(basePackages = "com.tennissetapp", excludeFilters = { @Filter(Configuration.class) })
//@PropertySource("classpath:org/springframework/social/showcase/config/application.properties")
//@EnableTransactionManagement //tx:annotation-driven
//@Profile(value="dev")
//@Import(DataConfig.class)
@Configuration
//@EnableAspectJAutoProxy
public class RootConfig implements ApplicationContextAware{
	protected static Logger logger = Logger.getLogger(RootConfig.class);
	protected ApplicationContext applicationContext;
	
//	@Inject 
//	protected DataConfig dataConfig;
	public static Properties props;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	/**
	 * relative path to properties file would not work on test environment
	 * 
	 * keep it static to avoid warning by Spring
	 * @return
	 * @throws IOException
	 */
	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException{
		ClassPathResource resource = new ClassPathResource("config.properties");
		logger.debug("propertyPlaceholderConfigurer, class path: " + new ClassPathResource("").getFile().getAbsolutePath());
		PropertyPlaceholderConfigurer p = new PropertyPlaceholderConfigurer();
		
		Resource[] resources = new Resource[] {resource}; 
		p.setLocations(resources);
		props = PropertiesLoaderUtils.loadProperties(resources[0]);
		return p;
	}
	
	@Bean
	@Scope(value="singleton")
	public EnvironmentProperties environmentProperties(){
		return new EnvironmentProperties();
	}
	
	@Bean
	public SimpleAsyncTaskExecutor simpleAsyncTaskExecutor(){
		SimpleAsyncTaskExecutor b = new SimpleAsyncTaskExecutor();
		return b;
	}
	
	@Bean
	@Scope(value="singleton")
	public MailService mailService(){
		//m.setMailSender((MailSender)applicationContext.getBean("javaMailSender"));
		MailService s = new MailService();
		
		return s;
	}
	
	@Bean
	public JavaMailSender javaMailSender(){
		//using google apps
		JavaMailSenderImpl m = new JavaMailSenderImpl();
//		m.setHost("smtp.gmail.com"); //173.194.68.108
		m.setHost(props.getProperty("tennissetapp.mail.host"));
		m.setPort(Integer.valueOf(props.getProperty("tennissetapp.mail.port")));
		m.setUsername(props.getProperty("tennissetapp.mail.username"));
		m.setPassword(props.getProperty("tennissetapp.mail.password"));

		Properties javaMailProps = new Properties();
		javaMailProps.put("mail.smtp.auth", true);
		javaMailProps.put("mail.smtp.starttls.enable", true);
		m.setJavaMailProperties(javaMailProps);
		return m;
	}
	
//	@Bean
//	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
//	public TestService testService(){
//		TestServiceImpl bean = new TestServiceImpl();
//		bean.setAuthDAO(authDao());
//		return bean;
//	}
	
//	@Bean
//	public VelocityEngineFactoryBean velocityEngineFactoryBean(){
//		VelocityEngineFactoryBean v = new VelocityEngineFactoryBean();
//		Properties props = new Properties();
////        props.put("resource.loader", "class");
////        props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//		
//		props.put("resource.loader", "file");
//		props.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
//		props.put("file.resource.loader.path", "/tennissetapp-env/config/email-templates");
//		props.put("file.resource.loader.cache", "true");
//		props.put("file.resource.loader.modificationCheckInterval", "2");
//        v.setVelocityProperties(props);
//        v.setPreferFileSystemAccess(true);
//        
////        Resource resource = new ClassPathResource("email-templates/activate-new-account.html");
//        logger.debug("created VelocityEngineFactoryBean");
//		return v;
//	}
	
	@Bean
	public VelocityEngine velocityEngine(){
		try {
//			logger.debug("returning VelocityEngine");
//			VelocityEngine e = velocityEngineFactoryBean().createVelocityEngine();
			
			Properties props = new Properties();
			props.put("resource.loader", "file");
			props.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
			props.put("file.resource.loader.path", "/tennissetapp-env/email-templates");
			props.put("file.resource.loader.cache", "true");
			props.put("file.resource.loader.modificationCheckInterval", "2");
	        
			VelocityEngine e = new VelocityEngine(props);
			e.init();
			
			return e;
		} 
		catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	@Bean
	public MockServletContext mockServletContext(){
		return new MockServletContext();
	}
	
//	@Bean
//	public TXAspect txAspect(){
//		return new TXAspect();
//	}
	
}
