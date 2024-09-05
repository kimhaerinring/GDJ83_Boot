package com.sun.app.aops.pays;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Transcation {
	
	@AfterThrowing("execution(* com.sun.app.*.*.set*(..))")
	public void rollBack() {
		
	}
}
