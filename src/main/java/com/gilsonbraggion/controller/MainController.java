package com.gilsonbraggion.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping(value = {"/", "/login"})
	public String login() {
		return "login";
	}

	@GetMapping(value = "/admin")
	public String admin() {
		return "admin/admin";
	}
	
	@GetMapping(value = "/403")
	public String error403() {
		return "error/403";
	}
	
	@Scheduled(fixedRate = 480000)
	public void scheduleFixedDelayTask() {
	    System.out.println(
	      "Fixed delay task - " + System.currentTimeMillis() / 1000);
	}
		
}
