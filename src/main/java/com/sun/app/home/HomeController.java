package com.sun.app.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
   
   @GetMapping("/")
   public String home() throws Exception {
      
	   log.trace("Trace");
	   log.debug("Debug");
	   
	   log.info("info");
	   log.warn("warn");
	   log.error("error");
	   
      return "index";
   }
}