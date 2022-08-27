package com.qa.main.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
	
	// Columns
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
//	@Column(name = "name") // name is used to change the name of the generated column
//	@Column(unique = true) // adds the unique constraint to the column
//  @Column(length = 50) // adds a limit to the datatype length	
	@Column(nullable = false) // adds a not null constraint to the column
	private String name; // Creates a column called first_name with the datatype VARCHAR(255)
	
	@Column(nullable = false) // adds a not null constraint to the column
	private int quantity; // Creates a column called first_name with the datatype VARCHAR(255)
	
	@Column(nullable = false) // adds a not null constraint to the column
	private double price; // Creates a column called price with the datatype INT

	// Constructors
	// Default constructor (for Spring)
	public Item () {}
	
	// For creating (without ID)
	public Item(String name, int quantity, double price) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	// For reading (with ID)
	public Item(long id, String name, int quantity, double price) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public int getquantity() {
		return quantity;
	}

	public void setquantity(int quantity) {
		this.quantity = quantity;
	}

	public double getprice() {
		return price;
	}

	public void setprice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return id == other.id && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && quantity == other.quantity;
	}

	
	
	
	
}
