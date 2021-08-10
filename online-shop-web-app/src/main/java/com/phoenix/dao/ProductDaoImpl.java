package com.phoenix.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.phoenix.data.Product;

/*
 * version 2.0
 * */
public class ProductDaoImpl implements ProductDao {

	private static Session session;
	
	static {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		session = factory.openSession();
		System.out.println("Oracle Database Connection Established!!!");
	}
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return session.createQuery("from Product").getResultList();
	}

	@Override
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		return session.get(Product.class, id);
	}

	@Override
	public void insert(Product product) {
		// TODO Auto-generated method stub
		Transaction tx  = session.beginTransaction();
		session.save(product);
		tx.commit();
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		Transaction tx  = session.beginTransaction();
		session.update(product);
		tx.commit();
	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub
		Transaction tx  = session.beginTransaction();
		session.delete(product);
		tx.commit();
	}
	
	

}
