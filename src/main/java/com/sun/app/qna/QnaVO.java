package com.sun.app.qna;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// DTO랑 같은 역할임 그냥 데이터 운반 역할
// DTO   Data Transfer Object
// VO   Value Object
// 근데 굳이 구분하자면 VO는 읽기 전용임. 데이터를 한번 넣으면 수정할 수 없음
// VO는 그래서 setter를 안만들고 getter만 만듦.
// 그냥 그정도의 차이

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
@Data //위에 5개 다 들어간 것
public class QnaVO {
   
	   private Long boardNum;
	   private String boardWriter;
	   private String boardTitle;
	   private String boardContents;
	   private Date createDate;
	   private long ref;
	   private long step;
	   private long dex;

}
