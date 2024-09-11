package com.sun.app.configs.security;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.authentication.MaximumSessionsContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.sun.app.members.*;
@Configuration
@EnableWebSecurity
public class SecurityConfig{
	@Autowired
	private SecurityLoginSuccessHandler securityLoginSuccessHandler;
	@Autowired
	private SecurityLoginFailHandler securityLoginFailHandler;
	@Autowired
	private MemberUserService memberUserService;
	@Bean
	 WebSecurityCustomizer webSecurityCustomizer()throws Exception{
		//Security 에서 무시할 것들
		return web -> web
						.ignoring()
						.requestMatchers("/images/**")
						.requestMatchers("/css/**")
						.requestMatchers("/js/**")
						.requestMatchers("/vendor/**")
						.requestMatchers("/favicon/**")
						;
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security)throws Exception{
		String message=URLEncoder.encode("로그인 실패","UTF-8");
		security.	
			cors()
			.and()
			.csrf()
			.disable();
		//권한에 관련된 설정
		security.authorizeHttpRequests(
				(authorizeRequest)->
				authorizeRequest
				.requestMatchers("/").permitAll()
				.requestMatchers("/qna/list").permitAll()
				.requestMatchers("/qna/detail","/qna/add","/qna/update","/qna/delete").authenticated()
				.requestMatchers("/notice/list","/notice/detail").permitAll()
				.requestMatchers("/notice/*").hasRole("ADMIN")
				.requestMatchers("/manager/*").hasAnyRole("ADMIN","MANAGER")
				.requestMatchers("/member/add","/member/login","/member/check").permitAll()
				.requestMatchers("/member/*").authenticated()
				.anyRequest().permitAll()

				)//끝부분
		//form login 관련 설정
		.formLogin(
				login ->
						//개발자가 만든 로그인 페이지 사용
						login
						.loginPage("/member/login")
						//.defaultSuccessUrl("/")
						.successHandler(securityLoginSuccessHandler)
						//.failureUrl("/member/login?message="+message)
						.failureHandler(securityLoginFailHandler)
						//파라미터이름이 username이  아니고 'id'로 사용한 경우
						//.usernameParameter("id")
						//파라미터이름이 password  아니고 'pw'로 사용한 경우
						//.passwordParameter("pw")
						.permitAll()
				)
			//logout
			.logout(
					logout -> 
							//RequestMatcher("URL") 로그아웃 URL 
							logout
							.logoutUrl("/member/logout") //로그아웃 URL 지정
							//.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
							.logoutSuccessUrl("/")
							.invalidateHttpSession(true) //true 세션 만료 , false 세션 만료X
							//.deleteCookies("") 쿠키 이름을 넣으면 쿠키 삭제하고 싶을때 사용
					)
			//remember me
			.rememberMe(
					remember -> 
							remember
							//parameter 이름
							.rememberMeParameter("rememberMe")
							//token 유효시간							
							.tokenValiditySeconds(60)
							//token 생성시에 사용되는 값 , 필수값, 개발자가 값 설정
							.key("rememberme")
							//login 할때 인증 절차 진행할 UserDetailService
							.userDetailsService(memberUserService)
							//로그인이 성공했을 경우 진행할 Handler
							.authenticationSuccessHandler(securityLoginSuccessHandler)
							.useSecureCookie(false)
					)
				//동시세션
			.sessionManagement(
					sessionManagement ->
					sessionManagement
						//최대 허용 갯수, -1 이면 무한대  ex)멜론
						.maximumSessions(1)
						//false 라면 기존 사용자만료 킴/ true 새로운 사용자 인증 실패
						.maxSessionsPreventsLogin(true)  
						//session 만료 되었을 경우 redirect 할 URL 경로
						.expiredUrl("/member/check")
						
						
					)
			
		;
		
		return security.build();
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	
	
	
	
	
	
	
}
