package com.github.scompo.workcalendar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!production")
public class CallbackUriConfigLocal implements CallbackUriConfig{

	@Override
	@Bean
	public CallBackUri callbackUri() {
		
		CallBackUri callBackUri = new CallBackUri();
		
		callBackUri.setCallbackUri("http://localhost:8080/");
		
		return callBackUri;
	}

	
}
