package com.sun.app.members;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.sun.app.validate.MemberAddGroup;
import com.sun.app.validate.MemberUpdateGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
public class MemberVO implements UserDetails , OAuth2User{
	   @NotBlank(groups = {MemberAddGroup.class,MemberUpdateGroup.class})
	   private String username;
	   @NotBlank(groups = {MemberAddGroup.class})
	   @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{6,12}")
	   private String password;
	   
	   private String passwordCheck;
	   
	   @NotBlank(groups = {MemberAddGroup.class,MemberUpdateGroup.class})
	   private String name;
	   @Email(groups = {MemberAddGroup.class,MemberUpdateGroup.class})
	   private String email;
	   @Past(groups = {MemberAddGroup.class,MemberUpdateGroup.class})
	   private Date birth;
	   
	   private boolean enabled;
	   
	   private List<RoleVO> vos;
	   
	   //Oauth2User
	   //token 정보 저장
	   private Map<String, Object> attirbutes;
	   
	   private String accessToken;
	   private String sns;//네이버냐 카카오냐 구분하기 위헤서
	   @Override
	   public Map<String, Object> getAttributes() {
		 
		  return this.attirbutes;
	   }
		   
	   
	   
	   
	   
	   
	   
	   
	   
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVO : vos) {	
		GrantedAuthority authority = new SimpleGrantedAuthority(roleVO.getRoleName());
		authorities.add(authority);
		}
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		//false 사용자 계정의 유효 기간이 만료 되었습니다.
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		//사용자 계정이 잠겨 있습니다.
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// 자격 증명 유효 기간이 만료되었습니다.
		return true;
	}
//	   public boolean isEnabled() {
//		 //유효하지 않은 사용자입니다.
//		   return true;
//	   }
	   
}
