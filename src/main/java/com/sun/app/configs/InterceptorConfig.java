package com.sun.app.configs;
import com.sun.app.home.interceptors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor;
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	//어떤 url이왔을 때 어떤 interceptor을 실행 할 것인가?
	// /qna/list -> loginInterceptor 	
		registry.addInterceptor(loginInterceptor)
			.addPathPatterns("/qna/*")
			.excludePathPatterns("/qna/list");
		
		registry.addInterceptor(adminCheckInterceptor)
		.addPathPatterns("/admin/*");
		
		registry.addInterceptor(localeChangeInterceptor)
		.addPathPatterns("/**");
		
	}
	
}
