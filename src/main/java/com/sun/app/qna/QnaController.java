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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController //@ResponseBody 전체 적용됨   @ResponseBody   //json형태로 변경시켜줌
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
	@CrossOrigin
	//model 을 쓰는 이유는 jsp에 가져가기위해서
	public List<QnaVO> getList(Pager pager) throws Exception{
		List<QnaVO>ar =qnaService.getList(pager);
		return ar;
		
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
	
	@GetMapping("detail/{boardNum}/{name}")
	public QnaVO getDetail(@PathVariable(name ="boardNum") Long bn,@PathVariable String name, QnaVO qnaVO) throws Exception{
		log.info("BoardNum : {}", bn);
		log.info("NAme : {}", name);
		qnaVO = qnaService.getDetail(qnaVO);
		return qnaVO;
	}
	@GetMapping("fileDown")
	public String fileDown(QnaFileVO qnaFileVO,Model model) throws Exception{
		qnaFileVO = qnaService.getFileDetail(qnaFileVO);
		model.addAttribute("file",qnaFileVO);
		
		return "fileDownView";
	}
	
	
}
