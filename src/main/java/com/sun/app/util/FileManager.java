package com.sun.app.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	public String fileSave(String path,MultipartFile multipartFile) throws Exception{
		//어디에 저장??
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//저장할 파일명 생성
		String fileName = UUID.randomUUID().toString()+"-"+multipartFile.getOriginalFilename();
		
		//파일을 HDD에 저장
		file = new File(file,fileName);
		multipartFile.transferTo(file);
		//저장된 파일명 리턴
		return fileName;	
	}
}
