package com.sun.app.qna;

import java.util.List;
import com.sun.app.util.*;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/qna/*")
@Slf4j
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	@Value("${board.qna}")
	private String board;
	
	
	@ModelAttribute("board")
	public String getBooard() {
		return this.board;
	}
	
	@GetMapping("list")
	public void getList(Pager pager,Model model) throws Exception{
		List<QnaVO>ar =qnaService.getList(pager);
		model.addAttribute("list",ar);
		model.addAttribute("pager",pager);
		
		log.info("Pager:{}:{}", pager,pager.getKind());
		
		
	}
	@GetMapping("add")
	public void add(QnaVO qnaVO) throws Exception{}
		
	@PostMapping("add")
	public String add(@Valid QnaVO qnaVO,BindingResult bindingResult ,MultipartFile [] attaches )   throws Exception{
		if(bindingResult.hasErrors()) {
			log.error("Writer가 비어있음");
			return "qna/add";
		}
		
		int result = qnaService.add(qnaVO, attaches);
		return "redirect:./list";
	}
	
	@GetMapping("detail")
	public void getDetail(QnaVO QnaVO,Model model) throws Exception{
		QnaVO = qnaService.getDetail(QnaVO);
		model.addAttribute("vo",QnaVO);
	}
	@GetMapping("fileDown")
	public String fileDown(QnaFileVO qnaFileVO,Model model) throws Exception{
		qnaFileVO = qnaService.getFileDetail(qnaFileVO);
		model.addAttribute("file",qnaFileVO);
		
		return "fileDownView";
	}
	
	
}
