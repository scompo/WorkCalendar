package com.github.scompo.workcalendar.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.jayway.jsonpath.JsonPath;

public class JsonLoader implements InitializingBean {

	private Resource resourceJsonFile;

	private String content;

	@Override
	public void afterPropertiesSet() throws Exception {

		content = loadJsonFile(getResourceJsonFile());
	}

	public String getByName(String name) {

			return JsonPath.parse(content).read(name);
	}

	public String loadJsonFile(Resource googleJsonFile) throws IOException {

		String string = new BufferedReader(new InputStreamReader(googleJsonFile.getInputStream())).lines()
				.reduce((s, s2) -> s + s2).orElse("{}");
		
		return string;
	}

	public Resource getResourceJsonFile() {

		return resourceJsonFile;
	}

	public void setResourceJsonFile(Resource resourceJsonFile) {
		this.resourceJsonFile = resourceJsonFile;
	}

}
