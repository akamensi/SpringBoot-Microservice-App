package com.article.micro.entities;

import jakarta.persistence.*;

@Entity
public class Article {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO) 
	 private Long id; 
	 private String name; 
	 private double price; 
	 private Long supplierId;
	 
	public Article() {}

	public Article(Long id, String name, double price, Long supplierId) {
		
		this.id = id;
		this.name = name;
		this.price = price;
		this.supplierId = supplierId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	} 
	
	
	  

}
