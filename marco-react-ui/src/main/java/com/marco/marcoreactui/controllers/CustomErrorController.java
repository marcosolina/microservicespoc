package com.marco.marcoreactui.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String page = "errorPages/";

		if (status != null) {
			HttpStatus httpStatus = HttpStatus.valueOf(Integer.parseInt(status.toString()));
			switch (httpStatus) {
			case FORBIDDEN:
				return page + httpStatus.value();
			default:
				break;
			}
		}
		return page + "/error";
	}

}
