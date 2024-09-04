package com.sun.app.qna;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sun.app.util.FileManager;
import com.sun.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.qna}")
	private String name;
	
	@Autowired
	private FileManager fileManager;
	
	public List<QnaVO> getList(Pager pager) throws Exception{
		pager.setPage(1L);
		pager.makeRow();
		log.info("upload path:{}",upload);
		return qnaMapper.getList(pager);
	}
	
	public int add(QnaVO qnaVO, MultipartFile [] attaches)throws Exception{
		log.info("=================Insert Before BoardNum:{}",qnaVO.getBoardNum());
		int result= qnaMapper.add(qnaVO);
		log.info("=================Insert After BoardNum:{}",qnaVO.getBoardNum());
		result = qnaMapper.refUpdate(qnaVO);
		//파일을 하드 디스크에 저장 후 DB 에 정보를 추가
		for(MultipartFile mf :attaches) {
			if(mf.isEmpty()|| mf==null) {
				continue;
			}
			String fileName =fileManager.fileSave(upload+name,mf); //d:/upload/qna
			
			QnaFileVO qnaFileVO = new QnaFileVO();
			qnaFileVO.setFileName(fileName);
			qnaFileVO.setOriName(mf.getOriginalFilename());
			qnaFileVO.setBoardNum(qnaVO.getBoardNum());
			result = qnaMapper.addFile(qnaFileVO);
				
		}
		
		return result;
	}
	public QnaVO getDetail(QnaVO qnaVO)throws Exception{
		return qnaMapper.getDetail(qnaVO);
	}
	
	public QnaFileVO getFileDetail (QnaFileVO qnaFileVO)throws Exception{
		
		return  qnaMapper.getFileDetail(qnaFileVO);
		
	}
	
}
