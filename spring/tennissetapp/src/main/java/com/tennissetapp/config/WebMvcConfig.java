package com.tennissetapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver r = new InternalResourceViewResolver();
		r.setPrefix("/views/");
		return r;
	}
	
	
	//throws exception, moved to RootConfig
//	@Bean
//	public MockServletContext mockServletContext(){
//		return new MockServletContext();
//	}
	
	/**
	 * Handles HTTP GET requests for /resources/** by efficiently serving up static 
	 * resources in the ${webappRoot}/resources directory
	 * <resources mapping="/resources/**" location="/resources/" />
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/**").addResourceLocations("/external/");
	}


//	@Inject
//	private ConnectionRepository connectionRepository;

//	@Bean
//	public ViewResolver viewResolver() {
//		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
//		viewResolver.setViewClass(TilesView.class);
//		return viewResolver;
//	}
//
//	@Bean
//	public TilesConfigurer tilesConfigurer() {
//		TilesConfigurer configurer = new TilesConfigurer();
//		configurer.setDefinitions(new String[] {
//				"/WEB-INF/layouts/tiles.xml",
//				"/WEB-INF/views/**/tiles.xml"                           
//		});
//		configurer.setCheckRefresh(true);
//		return configurer;
//	}
//
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//	}
//	
//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("/WEB-INF/messages/messages");
//        return messageSource;
//    }
}
