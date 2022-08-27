package com.qa.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.main.domain.Item;
import com.qa.main.repos.ItemRepo;

@Service
public class ItemService {
	
	private ItemRepo repo;
	
	public ItemService(ItemRepo repo) {
		this.repo = repo;
	}
	
	public Item create(Item item) {
		return repo.saveAndFlush(item);
	}
	
	public List<Item> getAll() {
		return repo.findAll();
	}
	
	public Item getByID(long id) {
		return repo.findById(id).get();
	}
	
	
	
	public Item update(long id, Item item) {
		// We get the existing entry
		Item existing = repo.findById(id).get();
		
		// Update the existing entry to match the incoming object
		existing.setname(item.getname());
		existing.setquantity(item.getquantity());
		existing.setprice(item.getprice());
		
		// Save the updated entry back into the DB (ID is the same)
		return repo.saveAndFlush(existing);
		
	}
	
	public boolean delete(long id) {
		repo.deleteById(id);
		
		return !repo.existsById(id);
	}

}
