package com.github.scompo.workcalendar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	public static final String INDEX_TEMPLATE_NAME = "index";

	@RequestMapping
	public String index() {

		return INDEX_TEMPLATE_NAME;
	}
}
