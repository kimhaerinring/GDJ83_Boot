package com.sun.app.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sun.app.aops.main.Start;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
   @Autowired
	private Start start;
   @GetMapping("/")
   public String home() throws Exception {
      
//	   log.trace("Trace");
//	   log.debug("Debug");
//	   
//	   log.info("info");
//	   log.warn("warn");
//	   log.error("error");
	   start.go();
      return "index";
   }
}