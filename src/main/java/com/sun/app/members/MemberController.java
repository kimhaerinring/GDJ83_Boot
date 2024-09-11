 	package com.sun.app.members;

import java.lang.reflect.Member;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.app.validate.MemberAddGroup;

import  org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("check")
	public void check() throws Exception{
		
	}
	
	
	
	@GetMapping("update")
	public String update(HttpSession session, Model model) throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		model.addAttribute("memberVO",memberVO);
		return "member/update";
	}
	@PostMapping("update")
	public String update(@Validated(MemberAddGroup.class) MemberVO memberVO ,BindingResult bindingResult)throws Exception {
		if(bindingResult.hasErrors()) {
			return "member/update";
		}
		
		return "redirect:./mypage";
	}
	
	@GetMapping("mypage")
	public void mypage(HttpSession session)throws Exception{
		Enumeration<String> en = session.getAttributeNames();
		while (en.hasMoreElements()) {
			String name =  en.nextElement();
			log.error("name:{}",name); //SPRING_SECURITY_CONTEXT
		}
		SecurityContextImpl sc=(SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.error("SecurityContextImpl:{}",sc);  //user 정보들이 들어있음
		SecurityContext context =SecurityContextHolder.getContext();
		log.error("SecurityContext:{}",context);
		Authentication authentication =context.getAuthentication();
		log.error("Authentication:{}",authentication);
		MemberVO memberVO=(MemberVO)authentication.getPrincipal();
		log.error("memberVO:{}",memberVO);
		log.error("Name:{}",memberVO.getName());
	}
	
	
	//add
	@GetMapping("add")
	public void add(MemberVO memberVO)throws Exception {
		
	}
	@PostMapping("add")
	public String add(@Validated(MemberAddGroup.class) MemberVO memberVO,BindingResult bindingResult)throws Exception {
		boolean check =memberService.memberValidate(memberVO, bindingResult);
		if(check) {
			return "member/add";
		}
//		if(bindingResult.hasErrors()) {
//			
//			return "member/add";
//		}
//		
		int result = memberService.add(memberVO);
		
		return "redirect:../";
		
	}
	//detail
	@GetMapping("login")
	public String login(String message ,Model model)throws Exception {
			model.addAttribute("message",message);
			//뒤로가기 처리
			SecurityContext context = SecurityContextHolder.getContext();
			
			if(context == null) {
				return "member/login";
			}
			String user=context.getAuthentication().getPrincipal().toString();
			if(user.equals("anonymousUser")) {
				return "member/login";
			}
			log.error(user);
			return "redirect:/";
	}
//	@PostMapping("login")
//	public String login(MemberVO memberVO,HttpSession session)throws Exception {
//		memberVO = memberService.detail(memberVO);
//		if(memberVO !=null) {
//			session.setAttribute("member",memberVO);
//		}
//		return "redirect:../";
//		
//	}
	//logout
	@GetMapping("logout")
	public String logout(HttpSession session)throws Exception {
		session.invalidate();
		return "redirect:../";
	}
}
