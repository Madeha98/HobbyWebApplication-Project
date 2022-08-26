	package com.qa.main.controllers;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qa.main.domain.Item;
import com.qa.main.services.ItemService;
@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {
	
	private ItemService service;
	
	public ItemController(ItemService service) {
		this.service = service;
	}
	
	// Post Requests - CREATE
	@PostMapping("/create")
	public ResponseEntity<Item> create(@RequestBody Item item) {
		return new ResponseEntity<Item>(service.create(item), HttpStatus.CREATED);
	}
	
	// Get Requests - READ
	@GetMapping("/getAll")
	public ResponseEntity<List<Item>> getAll() {
		return new ResponseEntity<List<Item>>(this.service.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public Item getByID(@PathVariable long id) {
		return this.service.getByID(id);
	}
	
	// Put Requests - UPDATE
	@PutMapping("/update/{id}")
	public Item update(@PathVariable long id, @RequestBody Item item) {
		return this.service.update(id, item);
	}
	
	// Delete Requests - DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
		return new ResponseEntity<Boolean>(this.service.delete(id), HttpStatus.NO_CONTENT);
	}
}