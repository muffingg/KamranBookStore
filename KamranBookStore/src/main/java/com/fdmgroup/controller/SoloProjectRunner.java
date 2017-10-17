package com.fdmgroup.controller;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.daos.AuthorDAO;
import com.fdmgroup.daos.AuthorDaoImpl;
import com.fdmgroup.daos.BookDAO;
import com.fdmgroup.daos.BookDaoImpl;
import com.fdmgroup.daos.CartDAO;
import com.fdmgroup.daos.CartDaoImpl;
import com.fdmgroup.daos.CartItemDAO;
import com.fdmgroup.daos.CartItemDaoImpl;
import com.fdmgroup.daos.OrderDAO;
import com.fdmgroup.daos.OrderDaoImpl;
import com.fdmgroup.daos.UnapprovedAuthorDAO;
import com.fdmgroup.daos.UnapprovedAuthorDaoImpl;
import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.daos.UserDaoImpl;
import com.fdmgroup.entities.Book;
import com.fdmgroup.entities.UnapprovedAuthor;

public class SoloProjectRunner {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		UserDAO userDao = new UserDaoImpl(factory);
		BookDAO bookDao = new BookDaoImpl(factory);
		AuthorDAO authorDao = new AuthorDaoImpl(factory);
		CartItemDAO cartItemDao = new CartItemDaoImpl(factory);
		CartDAO cartDao = new CartDaoImpl(factory);
		OrderDAO orderDao = new OrderDaoImpl(factory);
		UnapprovedAuthorDAO unapprovedAuthor = new UnapprovedAuthorDaoImpl(factory);
		
		UnapprovedAuthor ua = new UnapprovedAuthor();
		
		ua.setEmailAddress("abc@def.com");
		ua.setFirstName("Tata");
		ua.setLastName("Nano");
		ua.setAddress("Iceland");
		
		unapprovedAuthor.addUnapprovedAuthor(ua);
		
		factory.close();
	}
}
