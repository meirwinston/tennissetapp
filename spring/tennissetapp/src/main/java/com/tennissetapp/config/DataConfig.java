package com.tennissetapp.config;

import java.sql.Types;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;
import org.springframework.transaction.support.TransactionTemplate;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.dao.DaoManagerImpl;
import com.tennissetapp.persistence.entities.Address;

@EnableTransactionManagement
@Configuration
public class DataConfig {
	protected Logger logger = Logger.getLogger(DataConfig.class);
	static int count = 0;
	@Value("${tennissetapp.jdbc.url}")
	private String jdbcUrl;
	
	@Value("${tennissetapp.jdbc.username}")
	private String jdbcUsername;
	
	@Value("${tennissetapp.jdbc.password}")
	private String jdbcPassword;
	
	public DataConfig(){
		logger.debug(count++);
	}
	
	@Bean(name="dataSource")
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		return dataSource;
	}

	public static class CustomDialect extends MySQLDialect{
		public CustomDialect(){
			super.registerHibernateType(Types.BIGINT, StandardBasicTypes.LONG.getName());
//			super.registerFunction("func1", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "?1 + ?2",true));
			
//			super.registerFunction("geodist", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE, "SQRT(POW(111 * (?1 - ?3), 2) + POW(111 * (?4 - ?2) * COS(?1 / 57.29577951308232), 2))",true));
			
			//levelDistance(playerLevel FLOAT, levelMin FLOAT, levelMax FLOAT)
//			super.registerFunction("leveldist", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE, "LEAST(ABS(?1 - ?2),ABS(?1 - ?3))"));
//			
//			//NOTE: if you call this function with an argument e.g. :playerLevel 
//			//it will be rendered as ? wich will cause "No Value specified for parameter..."
//			super.registerFunction("func9", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE, "LEAST(ABS(?1 - ?2),ABS(?1 - ?3))"));
//			
//			//`distanceWeight`(geoDistance DOUBLE)
//			super.registerFunction("distweight", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE, "IF(?1 < 10,3,IF(?1 < 20,2, IF(?1 < 50, 1,0)))"));
//			
//			//playerLevelWeight(level DOUBLE)
//			super.registerFunction("levelweight", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE, "IF( ?1 < 1,4,IF(?1 <= 2, 3,IF(?1 <= 3, 2,IF(?1 <= 4, 1,0))))"));
//			
//			//typeOfPlayWeight(playSingles BOOL, playDoubles BOOL, playPoints BOOL, playFullMatch BOOL,playHittingAround BOOL,matchSingles BOOL, matchDoubles BOOL, matchPoints BOOL, matchFullMatch BOOL, matchHittingAround BOOL)
//			super.registerFunction("playweight", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE, "IF(((?1 & ?6) + (?2 & ?7) + (?3 & ?8) + (?4 & ?9) + (?5 & ?10)) >= 1,1,0)"));
		}
	}
	
	@Bean(name="localSessionFactoryBean")
	public LocalSessionFactoryBean localSessionFactoryBean(){
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(dataSource());
//		bean.setPackagesToScan("com.tennissetapp.persistence.entities");
		bean.setPackagesToScan(Address.class.getPackage().getName());
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", CustomDialect.class.getName());
		hibernateProperties.put("hibernate.show_sql", false);
		bean.setHibernateProperties(hibernateProperties);
		return bean;
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory sessionFactory(){
		return localSessionFactoryBean().getObject();
	}
	
	/**
	 return transactionTemplate.execute(new TransactionCallback() {
      public Object doInTransaction(TransactionStatus status) {
        updateOperation1();
        return resultOfUpdateOperation2();
      }
    });
	 * @return
	 */
	@Bean
	@Scope(value="singleton")
	public TransactionTemplate transactionTemplate(){
		return new TransactionTemplate(transactionManager());
	}
	

	@Bean
	public HibernateTransactionManager transactionManager(){
//		logger.debug("--->DataConfig.transactionManager");
		HibernateTransactionManager m = new HibernateTransactionManager();
		m.setSessionFactory(sessionFactory());
		
		//false means Spring will use openSession and not getCurrentSession 
		//true will throw an exception Could not obtain Hibernate-managed Session 
		//for Spring-managed transaction
		m.setHibernateManagedSession(false);
	
		AnnotationTransactionAspect.aspectOf().setTransactionManager(m);
		return m;
	}
	
	@Bean
	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
	public DaoManager daoManager(){
		DaoManager m = new DaoManagerImpl();
		m.setSessionFactory(sessionFactory());
		return m;
	}
	
	@DependsOn(value="flyway")
	@Bean(name="persistenceUnitManager")
	public DefaultPersistenceUnitManager persistenceUnitManager(){
		return new DefaultPersistenceUnitManager();
	}
	
	@Bean(name="flyway",initMethod="migrate")
	public FlywayMigrate flyway(){
		FlywayMigrate bean =  new FlywayMigrate();
		bean.setDataSource(dataSource());
		return bean;
	}
}
