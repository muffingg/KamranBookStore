package com.fdmgroup.model;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.fdmgroup.daos.RejectedUserDAO;
import com.fdmgroup.daos.RejectedUserDaoImpl;
import com.fdmgroup.entities.RejectedUser;
import com.fdmgroup.entities.Book;

public class TestRejectedUserDaoImpl {

	private RejectedUserDAO rejectedUserDao;

	@Mock
	private EntityManager manager;
	private EntityTransaction transaction;
	private EntityManagerFactory factory;
	private TypedQuery typedQuery;

	@Before
	public void setUp() {
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		rejectedUserDao = new RejectedUserDaoImpl(factory);
		typedQuery = mock(TypedQuery.class);
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
	}

	@Test
	public void test_AddRejectedUser_CallsFindMethodRejectedUserMethod() {
		RejectedUserDaoImpl rejectedUserDaoImpl = new RejectedUserDaoImpl();
		RejectedUser rejectedUser = new RejectedUser();
		rejectedUser.setEmailAddress("abc@hotmail.com");
		rejectedUserDao.addRejectedUser(rejectedUser);

		verify(manager).persist(rejectedUser);
	}
	
	@Test
	public void test_CallingTheGetRejectedUserMethod_CallsFindMethodOnManager(){
		rejectedUserDao.getRejectedUser("abc@gmail.com");
		
		verify(manager, times(1)).find(RejectedUser.class, "abc@gmail.com");
	}
	
	@Test
	public void test_CallingRemoveRejectedUserMethod_CallsGetRejectedUserMethodInManager(){
		rejectedUserDao.removeRejectedUser("def@yahoo.com");
		
		verify(transaction, times(1)).begin();
		verify(manager, times(1)).find(RejectedUser.class, "def@yahoo.com");
		verify(transaction, times(1)).commit();
	}
	
	@Test
	public void test_CallingRemoveRejectedUserMethod_CallsRemoveMethodInManager() {
		RejectedUser rejectedUser = new RejectedUser();
		
		when(manager.find(RejectedUser.class, "def@gmail.com")).thenReturn(rejectedUser);
		
		rejectedUserDao.removeRejectedUser("def@gmail.com");
		
		verify(manager, times(1)).remove(rejectedUser);
	}
	
	@Test
	public void test_GetAllRejectedUsers_ReturnsListOfAllRejectedUsers(){
		when(manager.createQuery("select u from RejectedUser u", RejectedUser.class)).thenReturn(typedQuery);
		List<Book> list = new ArrayList<Book>();
		when(typedQuery.getResultList()).thenReturn(new ArrayList<Book>());
		
		rejectedUserDao.getAllRejectedUsers();
		
		verify(manager, times(1)).createQuery("select u from RejectedUser u", RejectedUser.class);
		assertEquals(list, rejectedUserDao.getAllRejectedUsers());
	}
	
	
	
	
	
}