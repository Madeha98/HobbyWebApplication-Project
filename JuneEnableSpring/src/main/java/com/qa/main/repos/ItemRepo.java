package com.qa.main.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.main.domain.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
	
	// SELECT * FROM customer WHERE name = {name}
	List<Item> findItemByName(String name);

}
