package com.sun.app.qna;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sun.app.util.Pager;

@SpringBootTest
public class QnaMapperTest {

	@Autowired
	private QnaMapper qnaMapper;
	@Test
	void getDetailTest()throws Exception {
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardNum(5L);
		qnaVO=qnaMapper.getDetail(qnaVO);
		
		assertNotNull(qnaVO);
	}
	
	
	
//	@Test
//	void getListTest() throws Exception {
//		Pager pager = new Pager();
//		pager.setPage(1L);
//		pager.setKind("k1");
//		pager.setSearch("2");
//		pager.makeRow();
//		
//		
//		List<QnaVO>ar =qnaMapper.getList(pager);
//		assertNotEquals(0, ar.size());
//	}
	

}
