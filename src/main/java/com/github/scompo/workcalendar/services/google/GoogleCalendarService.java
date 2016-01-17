package com.github.scompo.workcalendar.services.google;

import com.google.api.services.calendar.model.CalendarList;

public interface GoogleCalendarService {

	CalendarList getCalendars();

}
