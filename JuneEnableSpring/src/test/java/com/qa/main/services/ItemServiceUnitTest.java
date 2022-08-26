package com.qa.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.main.domain.Item;
import com.qa.main.repos.ItemRepo;

@SpringBootTest
public class ItemServiceUnitTest {
	
	@Autowired
	private ItemService service;
	
	@MockBean
	private ItemRepo repo;
	
	@Test
	public void testCreate() {
		// Create an object for saving
		Item entry = new Item("Dress", 4, 270);
		
		// Create an object for result
		Item result = new Item(2L, "Dress", 4, 270);
		
		Mockito.when(repo.saveAndFlush(entry)).thenReturn(result);

		assertEquals(result, service.create(entry));
	}
	
	@Test
	public void testGetAll() {
		// Create an object for saving
		List<Item> result = new ArrayList<>();
		result.add(new Item(1L, "Jumper", 1, 800));

		Mockito.when(repo.findAll()).thenReturn(result);

		assertEquals(result, service.getAll());
	}

}
