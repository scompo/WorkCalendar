package com.github.scompo.workcalendar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.github.scompo.workcalendar.helper.JsonLoader;

@Configuration
public class GoogleJsonConfig {

	@Value("${google.json.resource}")
	private Resource googleJsonResource;

	@Bean
	public JsonLoader googleJsonLoader() {

		JsonLoader jsonLoader = new JsonLoader();

		jsonLoader.setResourceJsonFile(googleJsonResource);

		return jsonLoader;
	}
}
