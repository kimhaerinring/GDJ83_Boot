package com.sun.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import com.sun.app.members.MemberMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class SecurityLoginFailHandler implements AuthenticationFailureHandler{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
			String message="로그인 실패";
			log.error("Exception : {}",exception);
			
			if(exception instanceof BadCredentialsException) {
				//비밀번호 틀림 BadCredentialsException
				message="비번이 틀림";
			}
			if(exception instanceof InternalAuthenticationServiceException) {
				//아이디가 틀림 InternalAuthenticationServiceException
				message="아이디가 없음";	
			}
			if(exception instanceof AccountExpiredException) {
				/* isAccountNonExpired = false*/
				message="계정 유효기간 만료";
			}
			
			if(exception instanceof LockedException) {
				/* isAccountNonLocked = false */
				message="계정 잠김";
			}

			if(exception instanceof CredentialsExpiredException) {
				/* isCredentialsNonExpired = false */
				message="비밀번호 유효기간 만료";
			}
			
			if(exception instanceof DisabledException) {
				/* isEnabled = false */
				message="휴면 계정";
			}
			
			if(exception instanceof AuthenticationCredentialsNotFoundException) {
				/* 기타 사유 로 인해 */
				message="인증이 안됨";
			}
			message = URLEncoder.encode(message, "UTF-8");
			response.sendRedirect("/member/login?message="+message);
		}
}
