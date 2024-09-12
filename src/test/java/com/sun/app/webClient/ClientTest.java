package com.sun.app.webClient;
import com.sun.app.comments.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@SpringBootTest
@Slf4j
class ClientTest {	
	@Test
	void webClientTest() {
		WebClient webCliennt =WebClient.builder()
											.baseUrl("https://jsonplaceholder.typicode.com/")
										
											.build();
		
		Flux<PostVO>res=webCliennt.get() 
									.uri("posts")  //요정에 대한 정보
									.retrieve() //응답에 괸련
									.bodyToFlux(PostVO.class)
									;
		 res.subscribe(v->{
			 	PostVO post =v;
			 log.error("v:{}",post);
			 
		 });
									
	}
	
//	@Test
	void test1() {
		//RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		//parameter 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("postId", "1");
		
		//요청 객체 생성
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, null);
		
		//요청 전송 응답 처리
	//List<PostVO> res=(List<PostVO>) restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", List.class);
		//PostVO[] result=res.getBody();
		//log.error("r:{}",res.size());
		
	}

}
