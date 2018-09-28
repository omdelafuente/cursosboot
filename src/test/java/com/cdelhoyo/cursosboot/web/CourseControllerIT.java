package com.cdelhoyo.cursosboot.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.jmx.enabled:true", "spring.datasource.jmx-enabled:true" })
@ActiveProfiles("scratch")
public class CourseControllerIT {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testEndpoints() throws Exception {
		mvc.perform(get("/courses")).andExpect(status().isOk());
		mvc.perform(get("/courses?name=metodo&page=0&size1")).andExpect(status().isOk());
		mvc.perform(get("/courses/1")).andExpect(status().isOk());
		mvc.perform(get("/courses/1/subjects")).andExpect(status().isOk());
		mvc.perform(get("/courses/1/subjects?name=kanban&page=0&size1")).andExpect(status().isOk());
		mvc.perform(post("/courses/1/subjects").contentType(MediaType.APPLICATION_JSON).content("{name:\"testName\"}")).andExpect(status().isOk());
	}

}
