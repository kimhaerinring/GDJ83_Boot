package com.sun.app.aops.main;
import com.sun.app.aops.pays.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sun.app.aops.transfers.*;


@Component
public class Start {
	@Autowired
	private Transfer transfer;
	@Autowired
	private Card card;	
	
	public void go() {
		
		transfer.takeBus(50);	
		transfer.takeSubway(15L);
		transfer.walk();
	
	}
}
