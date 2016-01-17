//package com.github.scompo.workcalendar.web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestOperations;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class AuthController {
//
//	@Autowired
//	private OAuth2ClientContext oauth2ClientContext;
//
//	@Autowired
//	private OAuth2RestOperations oAuth2RestOperations;
//
//	@RequestMapping("googleLogin")
//	public String googleLogin(Model model) {
//
//		Object res = oAuth2RestOperations.getForObject("https://www.googleapis.com/calendar/v3/colors", Object.class);
//
//		System.err.println(res);
//		System.err.println(res.getClass());
//
//		return "redirect:/";
//	}
//
//	@RequestMapping("stuff")
//	public String stuff() {
//
//		Object res = oAuth2RestOperations.getForObject("https://www.googleapis.com/calendar/v3/colors", Object.class);
//
//		System.err.println(res);
//
//		return "stuff";
//	}
//}
