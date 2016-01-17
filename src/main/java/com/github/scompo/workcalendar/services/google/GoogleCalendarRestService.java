package com.github.scompo.workcalendar.services.google;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.model.CalendarList;

@Service
public class GoogleCalendarRestService implements GoogleCalendarService{

	@Autowired
	private OAuth2RestOperations oAuth2RestOperations;

	public OAuth2RestOperations getoAuth2RestOperations() {
		return oAuth2RestOperations;
	}

	public void setoAuth2RestOperations(OAuth2RestOperations oAuth2RestOperations) {
		this.oAuth2RestOperations = oAuth2RestOperations;
	}

	@Override
	public CalendarList getCalendars() {
		
		//oAuth2RestOperations.getForEntity("https://www.googleapis.com/calendar/v3/users/me/calendarList", Object.class);
		
		ResponseEntity<String> res = oAuth2RestOperations.getForEntity("https://www.googleapis.com/calendar/v3/users/me/calendarList", String.class);
		JacksonFactory jacksonFactory = new JacksonFactory();
		
		JsonParser p = null;
		CalendarList data = null;
		
		try {
			p = jacksonFactory.createJsonParser(res.getBody());
			data = p.parse(CalendarList.class);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return data;
	}
}
