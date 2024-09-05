package com.sun.app.aops.pays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class Card {
	
	@Around("execution(* com.sun.app.aops.transfers.Transfer.take*(..))")
	public Object cardCheck(ProceedingJoinPoint joinPoint)throws Throwable {
		
		log.info("===== Before 카드 체크=====");
		log.info("====ARGS :{}",joinPoint.getArgs());
		Object obj=joinPoint.proceed(); //point-cut
		log.info("=====Return:{}",obj);
		log.info("===== After 카드 체크=====");
		return obj;
	}
}
