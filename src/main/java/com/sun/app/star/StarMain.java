package com.sun.app.star;

import org.springframework.beans.factory.annotation.Autowired;

public class StarMain {
	@Autowired
	private Hydra hydra;
	@Autowired
	private Unit marine;
	
	
	public void go() throws Exception {
		hydra.attack();
		hydra.move();
		
		marine.attack();
		marine.move();
	}
}
