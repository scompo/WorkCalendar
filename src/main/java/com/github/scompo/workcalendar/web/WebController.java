package com.github.scompo.workcalendar.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.scompo.workcalendar.services.google.GoogleCalendarService;
import com.google.api.services.calendar.model.CalendarList;

@Controller
public class WebController {

	public static final String INDEX_TEMPLATE_NAME = "index";
	
	@Autowired
	private GoogleCalendarService googleCalendarService;

	@RequestMapping(value = "/")
	public String index(Model model) {
		
		CalendarList list = googleCalendarService.getCalendars();
		
		list.getItems().forEach(x -> System.err.println(x));
		
		model.addAttribute("calendarList", list);
		
		return INDEX_TEMPLATE_NAME;
	}
}
