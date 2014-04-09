package com.tennissetapp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//@Controller
public class TestController {
	Logger logger = Logger.getLogger(getClass());
	
//	@Override
//	public ModelAndView securitytest(HttpServletRequest request)
//	throws IOException {
//		logger.debug("TTTTTTTTTTTTTTTTTTTTTT securitytest called");
//		ModelAndView mv = new ModelAndView("mates/browse-mates.jsp");
//		return mv;
//	}
}
