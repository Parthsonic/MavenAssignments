package com.phoenix.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.phoenix.dao.ProductDao;
import com.phoenix.dao.ProductDaoImpl;
import com.phoenix.data.Product;
import com.phoenix.exceptions.ProductNotFoundException;
import com.phoenix.exceptions.ServiceException;


public class ProductServiceImpl implements ProductService {

	private ProductDao productDao; 
	
	public ProductServiceImpl() {
		productDao = new ProductDaoImpl();
	}
	
	@Override
	public List<Product> findAll() throws ServiceException {
		return productDao.getAllProducts();
	}

	@Override
	public Product findProductById(int id) throws ProductNotFoundException {
		Product product = productDao.getProductById(id);
		if(product!=null)
			return product;
		else
			throw new ProductNotFoundException("Sorry ! product is Not Found");
	}

	@Override
	public void add(Product product) throws ServiceException{
		productDao.insert(product);
	}

	@Override
	public void edit(Product product) throws ServiceException {
		productDao.update(product);
	}

	@Override
	public void remove(Product product) throws ServiceException {
		productDao.delete(product);
	}

	@Override
	public List<Product> findByName(String name) throws ServiceException {
		// TODO Auto-generated method stub
		List<Product> products = findAll();
		List<Product> productsByName = new ArrayList<Product>();
		for(Product product: products) {
			if(product.getName().equals(name))
				productsByName.add(product);
		}
		return products;
	}

	@Override
	public List<Product> findByBrand(String brand) throws ServiceException {
		// TODO Auto-generated method stub
		List<Product> products = findAll();
		List<Product> productsByBrand = new ArrayList<Product>();
		for(Product product: products) {
			if(product.getBrand().equals(brand))
				productsByBrand.add(product);
		}
		return products;
	}

	@Override
	public List<Product> findByPrice(float price) throws ServiceException {
		// TODO Auto-generated method stub
		List<Product> products = findAll();
		List<Product> productsByPrice = new ArrayList<Product>();
		for(Product product: products) {
			if(product.getPrice() == price)
				productsByPrice.add(product);
		}
		return products;
	}

	@Override
	public List<Product> findByPriceRange(float minPrice, float maxPrice) throws ServiceException {
		// TODO Auto-generated method stub
		List<Product> products = findAll();
		List<Product> productsByPriceRange = new ArrayList<Product>();
		for(Product product: products) {
			if(product.getPrice()>=minPrice && product.getPrice()<=maxPrice)
				productsByPriceRange.add(product);
		}
		return products;
	}

	@Override
	public List<Product> findByNameAndBrand(String name, String brand) throws ServiceException {
		// TODO Auto-generated method stub
		List<Product> products = findAll();
		List<Product> productsByNameAndBrand = new ArrayList<Product>();
		for(Product product: products) {
			if(product.getName().equalsIgnoreCase(name) || product.getBrand().equalsIgnoreCase(brand))
				productsByNameAndBrand.add(product);
		}
		return productsByNameAndBrand;
	}

	@Override
	public List<Product> findByNameAndPrice(String name, float price) throws ServiceException {
		// TODO Auto-generated method stub
		List<Product> products = findAll();
		List<Product> productsByNameAndPrice = new ArrayList<Product>();
		for(Product product: products) {
			if(product.getName().equals(name) && product.getPrice()==price)
				productsByNameAndPrice.add(product);
		}
		return productsByNameAndPrice;
	}

	@Override
	public List<Product> findByBrandAndPrice(String brand, float price) throws ServiceException {
		// TODO Auto-generated method stub
		List<Product> products = findAll();
		List<Product> productsByNameAndPrice = new ArrayList<Product>();
		for(Product product: products) {
			if(product.getBrand().equals(brand) && product.getPrice()==price)
				productsByNameAndPrice.add(product);
		}
		return productsByNameAndPrice;
	}
	
	@Override
	public List<Product> sortByName(List<Product> products) throws ServiceException {
		// TODO Auto-generated method stub
		//List<Product> products = findAll();
		//Collections.sort(products,new ProductNameComparator());
		//System.out.println("products name:"+products);
		products = products.stream().sorted(Comparator.comparing(Product :: getName)).collect(Collectors.toList());
		return products;
	}

	@Override
	public List<Product> sortByBrand(List<Product> products) throws ServiceException {
		// TODO Auto-generated method stub
		//Collections.sort(products,new ProductBrandComparator());
		//System.out.println("products brand:"+products);
		products = products.stream().sorted(Comparator.comparing(Product :: getBrand)).collect(Collectors.toList());
		return products;
	}

	@Override
	public List<Product> sortByPrice(List<Product> products) throws ServiceException {
		// TODO Auto-generated method stub
		//Collections.sort(products,new ProductPriceComparator());
		//System.out.println("products price:"+products);
		products = products.stream().sorted(Comparator.comparing(Product :: getPrice)).collect(Collectors.toList());
		return products;
	}
	

	@Override
	public List<Product> sortByPriceDesc(List<Product> products) throws ServiceException {
		// TODO Auto-generated method stub
		//Collections.sort(products,new ProductPriceComparator().reversed());
		products = products.stream().sorted(Comparator.comparing(Product :: getPrice).reversed()).collect(Collectors.toList());
		return products;
	}

}
