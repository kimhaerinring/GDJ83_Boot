package com.sun.app.members;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Service								//카카오 로그인						//서버 로그인	
@Slf4j
public class MemberUserService extends DefaultOAuth2UserService implements UserDetailsService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	
	//카카오 로그인
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.error("Token:{}",userRequest.getAccessToken()); //인가코드 엑세스 모두 완료
		ClientRegistration clientRegistration=userRequest.getClientRegistration();
		log.error("clientID:{}",clientRegistration.getClientId());
		String sns=clientRegistration.getRegistrationId();
		OAuth2User auth2User =super.loadUser(userRequest);
		if(sns.equals("kakao")) {
			auth2User=this.useKakao(userRequest);
			
		}
		if(sns.equals("naver")) {
			
		}
		
		return auth2User;
	}
	
	private OAuth2User useKakao(OAuth2UserRequest userRequest)throws OAuth2AuthenticationException{
		OAuth2User auth2User=super.loadUser(userRequest);
		
		log.error("=========================================================");
		log.error("ID:{}",auth2User.getName());
		log.error("Attributes:{}",auth2User.getAttributes());
		log.error("Authorities:{}",auth2User.getAuthorities());
		
		MemberVO memberVO = new MemberVO();
		Map<String, Object>attributes =auth2User.getAttributes();
		Map<String, Object>properties =(Map<String, Object>)attributes.get("properties");
		
		memberVO.setAccessToken(userRequest.getAccessToken().getTokenValue());
		memberVO.setSns(userRequest.getClientRegistration().getRegistrationId());
		memberVO.setAttirbutes(attributes);
		memberVO.setUsername(auth2User.getName());
		memberVO.setName(properties.get("nickname").toString());
		List<RoleVO> list= new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		
		roleVO.setRoleName("ROLE_USER");
		list.add(roleVO);
		memberVO.setVos(list);;
		
		return memberVO;
		
	}
	
	
	
	
	
	
	
	
	
	//서버 로그인 ===============================================================================
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO=memberMapper.detail(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberVO;
	}
}
