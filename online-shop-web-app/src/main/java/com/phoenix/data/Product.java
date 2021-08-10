package com.phoenix.data;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * version 2.0
 * POJO class
 * */
@Entity
public class Product implements Comparable<Product> {
	
	@Id
	private int id;
	private String name;
	private String brand;
	private float price;
	
	public Product() {
		
	}

	public Product(int id, String name, String brand, float price) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public int compareTo(Product o) {
		// TODO Auto-generated method stub
		if(this.id>o.id)
			return 1;
		else if(this.id<o.id)
			return -1;
		else
			return 0;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		// TODO Auto-generated method stub
//		if(!(obj instanceof Product))
//			return false;
//		else {
//			Product p = (Product) obj;
//			if(this.id == p.id)
//				return true;
//			else 
//				return false;
//		}
//	}
	
}
