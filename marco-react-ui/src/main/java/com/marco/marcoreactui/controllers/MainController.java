package com.marco.marcoreactui.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping(value = "/")
	public String mainController() {
		return "index";
	}

	@GetMapping(value = "/app")
	public String getApp() {
		return "app";
	}
	
	@GetMapping(value = "/home")
	public String getAdmin() {
		return "home";
	}
	
	@GetMapping(value = "/logout")
	public String logOut(HttpServletRequest request) throws ServletException {
		request.logout();
		return "redirect:/";
	}
}
