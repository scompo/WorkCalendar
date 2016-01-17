package com.github.scompo.helper;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.github.scompo.workcalendar.helper.JsonLoader;

public class JsonLoaderTest {

	private static final ClassPathResource RESOURCE_JSON_FILE = new ClassPathResource("test.json");

	private JsonLoader loader;

	@Before
	public void setUp() throws Exception {

		loader = new JsonLoader();
		loader.setResourceJsonFile(RESOURCE_JSON_FILE);
		loader.afterPropertiesSet();
	}

	@After
	public void tearDown() {

		loader = null;
	}

	@Test
	public void testRootLevel() throws Exception {

		assertEquals("root level data", loader.getByName("rootLevelData"));
	}

	@Test
	public void testFirstLevel() throws Exception {

		assertEquals("first level data", loader.getByName("firstLevel.firstLevelData"));
	}

}
