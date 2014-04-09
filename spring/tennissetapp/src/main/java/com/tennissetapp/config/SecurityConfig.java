package com.tennissetapp.config;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.AntPathRequestMatcher;

import com.tennissetapp.auth.AppUserDetailsService;
import com.tennissetapp.auth.TennisSetAppAuthenticationDetailsSource;
import com.tennissetapp.auth.TennisSetAppAuthenticationHandler;
import com.tennissetapp.auth.TennisSetAppLogoutHandler;

@Configuration
public class SecurityConfig implements ApplicationContextAware{
	protected Logger logger = Logger.getLogger(SecurityConfig.class);
	protected ApplicationContext applicationContext;
	
	@Inject 
	protected DataConfig dataConfig;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Bean
    public SessionRegistry sessionRegistry(){
        SessionRegistryImpl sessRegistry=new SessionRegistryImpl();
        return sessRegistry;
    }
	
	@Bean
	public ConcurrentSessionControlStrategy sessionAuthenticationStrategy(SessionRegistry sessionRegistry){
		ConcurrentSessionControlStrategy concSessRegistry=new ConcurrentSessionControlStrategy(sessionRegistry);
		return concSessRegistry;
	}
	 
	@Bean
	public SimpleUrlAuthenticationSuccessHandler postSuccessAuthHandler(){
		TennisSetAppAuthenticationHandler authenticationHandler = new TennisSetAppAuthenticationHandler();
		authenticationHandler.setSessionFactory(dataConfig.sessionFactory());
		return authenticationHandler;
	}

	@Bean
	public SimpleUrlAuthenticationFailureHandler postFailedAuthHandler(){
		SimpleUrlAuthenticationFailureHandler failureHandler=new SimpleUrlAuthenticationFailureHandler("/login?login_error=true");
		return failureHandler;
	}

	@Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
//		JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
//		jdbcDaoImpl.setAuthoritiesByUsernameQuery("select email,groupName from user_accounts where email = ?");
//		jdbcDaoImpl.setUsersByUsernameQuery("select email,password,1 from user_accounts where email = ? and status = 'ACTIVE'");
//		jdbcDaoImpl.setDataSource(dataSource);
//		
//        return jdbcDaoImpl;
		AppUserDetailsService userDetailsService = new AppUserDetailsService();
		userDetailsService.setDataSource(dataSource);
		return userDetailsService;
    }
	
	private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder(); //^^p
	
	//^^p
	@Bean
	public Md5PasswordEncoder passwordEncoder(){
		return passwordEncoder;
	}
	
	@Bean
	public ReflectionSaltSource saltSource(){
		ReflectionSaltSource r = new ReflectionSaltSource();
		
		r.setUserPropertyToUse("email");
		return r;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider(UserDetailsService userService){
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		
		//^^p
		provider.setPasswordEncoder(passwordEncoder());
		provider.setSaltSource(saltSource());
		return provider;
	}
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider){
        List<AuthenticationProvider> providers = new ArrayList<AuthenticationProvider>();
        providers.add(authenticationProvider);
        
        ProviderManager authManager = new ProviderManager(providers);
        
        return authManager;
    }
	
	@Bean
	public SecurityContextLogoutHandler securityContextLogoutHandler(){
		return new TennisSetAppLogoutHandler();
	}
	
	@Bean
	public LogoutFilter logoutFilter(){
//		TennisSetAppLogoutHandler handler = new TennisSetAppLogoutHandler();	
		LogoutFilter filter = new LogoutFilter("/", new LogoutHandler[] {securityContextLogoutHandler()});
		return filter;
	}
	
	@Bean
	public ConcurrentSessionFilter concurrencyFilter(SessionRegistryImpl sessionRegistry){
		ConcurrentSessionFilter concurrencyFilter = new ConcurrentSessionFilter(sessionRegistry);
//		concurrencyFilter.setExpiredUrl("/auth/session-expired");
		return concurrencyFilter;
	}
	
	@Bean
    public SecurityContextPersistenceFilter securityContextFilter(HttpSessionSecurityContextRepository  securitycontextRepository){
        SecurityContextPersistenceFilter secFilter=new SecurityContextPersistenceFilter(securitycontextRepository);
        return secFilter;
    }    

    @Bean
    public SessionManagementFilter sessionMgmtFilter(HttpSessionSecurityContextRepository securitycontextRepository){
        SessionManagementFilter sessFilter=new SessionManagementFilter(securitycontextRepository);
        return sessFilter;
    }
    
    @Bean
    public HttpSessionSecurityContextRepository  securitycontextRepository(){
        HttpSessionSecurityContextRepository repo = new HttpSessionSecurityContextRepository();
        return repo;
    }
    
//    @Bean
//    public SecurityContext securityContext(){
//    	SecurityContextImpl ctx = new SecurityContextImpl();
//    	
//    	return ctx;
//    }
	 
	@Bean(name="springSecurityFilterChain")
	public FilterChainProxy springSecurityFilterChain(
	ConcurrentSessionFilter concurrentSessionFilter,
	SecurityContextPersistenceFilter securityContextPersistenceFilter,
	SessionManagementFilter sessionManagementFilter,
	UsernamePasswordAuthenticationFilter authenticationFilter,
	SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter,
	LogoutFilter logoutFilter){
//		logger.debug("---->SecurityConfig.filterchain proxy......");
		
		SecurityFilterChain chain = new DefaultSecurityFilterChain(new AntPathRequestMatcher("/**"),
		securityContextHolderAwareRequestFilter,
		concurrentSessionFilter,
		securityContextPersistenceFilter,
		sessionManagementFilter,
		authenticationFilter,
		logoutFilter);
		FilterChainProxy filterChain = new FilterChainProxy(chain); 
		return filterChain;
	}
	
	
	@Bean
	public UsernamePasswordAuthenticationFilter formLoginFilter(
	AuthenticationManager authenticationManager,
	ConcurrentSessionControlStrategy sessionAuthenticationStrategy,
	SimpleUrlAuthenticationSuccessHandler postSuccessAuthHandler,
	SimpleUrlAuthenticationFailureHandler postFailedAuthHandler,
	TennisSetAppAuthenticationDetailsSource tennisSetAppAuthenticationDetailsSource){
//		logger.debug("---->SecurityConfig.formLoginFilter.....");
		UsernamePasswordAuthenticationFilter authFilter = new UsernamePasswordAuthenticationFilter();
		authFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
		authFilter.setAuthenticationManager(authenticationManager);
		authFilter.setAuthenticationFailureHandler(postFailedAuthHandler);
		authFilter.setAuthenticationSuccessHandler(postSuccessAuthHandler);
		authFilter.setAuthenticationDetailsSource(tennisSetAppAuthenticationDetailsSource);
		return authFilter;        
	}
	
	@Bean
	@Scope(value="singleton")
	public TennisSetAppAuthenticationDetailsSource tennisSetAppAuthenticationDetailsSource(){
		TennisSetAppAuthenticationDetailsSource details = new TennisSetAppAuthenticationDetailsSource();
		
		return details;
	}
	
	/**
	 * that bean will populate the httpServletRequest with Principal object,
	 * without this it will be null.
	 * @return
	 */
	@Bean
	public SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter(){
		return new SecurityContextHolderAwareRequestFilter();
	}
		
}
