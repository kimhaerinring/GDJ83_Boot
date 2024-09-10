package com.sun.app.memebrs;

import static org.junit.jupiter.api.Assertions.*;
import com.sun.app.members.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
@SpringBootTest
class memberServiceTest {
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void test()throws Exception {
		MemberVO memberVO= new MemberVO();
		memberVO.setUsername("user");
		memberVO.setPassword(passwordEncoder.encode("user"));
		int result = memberMapper.pwUpdate(memberVO);
		assertEquals(1, result);
	}

}
