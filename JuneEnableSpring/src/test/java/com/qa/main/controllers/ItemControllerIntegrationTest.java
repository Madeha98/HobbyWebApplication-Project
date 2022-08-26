package com.qa.main.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.domain.Item;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:testschema.sql", "classpath:testdata.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test") // Switching to h2 for test 
public class ItemControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper; // Used for converting objects to JSON
	
	@Test
	public void createTest() throws Exception {
		// Create an object for posting
		Item entry = new Item("Dress", 4, 270);
		String entryAsJSON = mapper.writeValueAsString(entry);
		
		// Create an object for checking result
		Item result = new Item(2L, "Dress", 4, 270);
		String resultAsJSON = mapper.writeValueAsString(result);
		
		mvc.perform(post("/item/create")
		.contentType(MediaType.APPLICATION_JSON)
		.content(entryAsJSON))
		.andExpect(content().json(resultAsJSON));

	}
	
	@Test 
	public void readAllTest() throws Exception {
		
		// Create a list to check output of readAll
		List<Item> result = new ArrayList<>();
		result.add(new Item(1L, "Jumper", 1, 800));
		// Convert list to JSON (API responds in JSON)
		String resultAsJSON = mapper.writeValueAsString(result);
		
		mvc.perform(get("/item/getAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(resultAsJSON));
		
	}
	
	@Test 
	public void readByIdTest() throws Exception {
		// Create a list to check output of readById
		Item result = new Item(1L, "Jumper", 1, 800);
		
		// Convert list to JSON (API responds in JSON)
		String resultAsJSON = mapper.writeValueAsString(result);
		
		mvc.perform(get("/item/getById/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(resultAsJSON));
	
	}
	
	@Test 
	public void updateTest() throws Exception {
		// Create an object
		Item result = new Item(1L, "Jumper", 1, 800);
		String resultAsJSON = mapper.writeValueAsString(result);
		
		mvc.perform(put("/item/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(resultAsJSON))
			.andExpect(content().json(resultAsJSON));
	}
	
	@Test 
	public void deleteTest() throws Exception {
		mvc.perform(delete("/item/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
		
	}
	

}
