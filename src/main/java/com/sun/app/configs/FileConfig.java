package com.sun.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//설정 class,XML
@Configuration
//WebMVCConfigure 구현
public class FileConfig implements WebMvcConfigurer{
	@Value("${app.url.path}")
	private String url;
	@Value("${app.upload.location}")
	private String file;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//<resources mapping="/resources/**" location="/resources/"/>
		
		//<resources mapping="/files/**"location="D:/upload/" />
		registry.addResourceHandler(url)
				.addResourceLocations(file);
		
	}		
	}

