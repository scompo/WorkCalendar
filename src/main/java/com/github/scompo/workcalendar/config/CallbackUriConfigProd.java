package com.github.scompo.workcalendar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class CallbackUriConfigProd implements CallbackUriConfig{

	@Override
	@Bean
	public CallBackUri callbackUri() {
		
		CallBackUri callBackUri = new CallBackUri();
		
		callBackUri.setCallbackUri("https://scompo-work-calendar.herokuapp.com/");
		
		return callBackUri;
	}

	
}
