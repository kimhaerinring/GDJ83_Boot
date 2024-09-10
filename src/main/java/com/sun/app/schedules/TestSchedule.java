package com.sun.app.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.sun.app.qna.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	@Autowired
	private QnaMapper qnaMapper;
	
	//@Scheduled(fixedDelay = 1000,initialDelay = 2000)
	public void test1() throws Exception{
		//실행 후 종료 까지 약 2초가 걸린다.
		log.error("Schedule Test");
		
	}
	//@Scheduled(fixedRate = 2000,initialDelay = 3000)
	public void test2()throws Exception{
		log.error("=============Schedule Test");
	}
	//@Scheduled(cron = "*/5 * * * * *")
	public void test3()throws Exception{
		log.error("=============Schedule Test==============");
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardWriter("test");
		qnaVO.setBoardTitle("Title");
		qnaVO.setBoardContents("Contents");
		qnaMapper.add(qnaVO);
		qnaMapper.refUpdate(qnaVO);
	}
}
