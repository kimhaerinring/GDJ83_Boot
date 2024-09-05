package com.sun.app.aops.transfers;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Transfer {
	
	public String takeBus(int num) {
		log.info("=====버스타기=====");
		return "BUS";
	}
	
	public void takeSubway(Long m) {
		log.info("=====지하철타기=====");
	}
	
	public void walk() {
		log.info("=====걸어가기=====");
	}
}
