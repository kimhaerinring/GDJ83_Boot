package com.sun.app.qna;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.app.util.Pager;

public class QnaMapperTest {

	@Autowired
	private QnaMapper qnaMapper;
	

	@Test
	void getListTest() throws Exception {
		Pager pager = new Pager();
		pager.setPage(1L);
		pager.setKind("k1");
		pager.setSearch("2");
		pager.makeRow();
		
		
		List<QnaVO>ar =qnaMapper.getList(pager);
		assertNotEquals(0, ar.size());
	}
	

}
