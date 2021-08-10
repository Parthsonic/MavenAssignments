package com.phoenix.dao;

import java.util.List;

import com.phoenix.data.Product;

/*
 * version 2.0
 * */
public interface ProductDao {
	List<Product> getAllProducts();
	Product getProductById(int id);
	void insert(Product product);
	void update(Product product);
	void delete(Product product);	
}
