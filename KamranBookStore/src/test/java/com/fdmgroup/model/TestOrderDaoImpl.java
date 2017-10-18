package com.fdmgroup.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.fdmgroup.daos.OrderDAO;
import com.fdmgroup.daos.OrderDaoImpl;
import com.fdmgroup.entities.Book;
import com.fdmgroup.entities.User;
import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.Order;

public class TestOrderDaoImpl {

	private OrderDAO orderDao;

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
		orderDao = new OrderDaoImpl(factory);
		typedQuery = mock(TypedQuery.class);
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
	}
	
	@Test
	public void test_GetOrderMethod_CallsTheFindMethodInManager(){
		OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
		Order order = new Order();
		when(manager.find(Order.class, 2)).thenReturn(order);
		assertEquals(order, orderDao.getOrder(2));
	}
	
	@Test
	public void test_UpdateOrderMethod_CallsMergeMethodInManager(){
		Order order = new Order();
		orderDao.updateOrder(order);
		
		verify(manager, times(1)).merge(order);
	}
	
	@Test
	public void test_RemoveOrderMethod_CallsTheRemoveMethodInManager(){
		Order order = new Order();
		when(manager.find(Order.class, 3)).thenReturn(order);
		orderDao.removeOrder(3);
		verify(manager).remove(order);
	}
	
	@Test
	public void test_GetAllOrdersForUserMethod_ReturnsListOfAllOrders(){
		User user = new User();
		user.setEmailAddress("abc@gmail.com");
		List<Order> orders = new ArrayList<Order>();
		
		when(manager.createQuery("select o from Order as o join fetch o.user u where u.emailAddress = ?", Order.class)).thenReturn(typedQuery);
		when(typedQuery.getResultList()).thenReturn(orders);
		
		orderDao.getAllOrdersForUser(user);
		
		verify(manager, times(1)).createQuery("select o from Order as o join fetch o.user u where u.emailAddress = ?", Order.class);
		assertEquals(orders, orderDao.getAllOrdersForUser(user));
	}
	
	@Test
	public void test_AddOrderMethod_CallsPersistInManager_WhenOrderDoesNotExist(){
		Order order = new Order();
		Cart cart = new Cart();
		
		order.setCart(cart);
		
		orderDao.addOrder(order);
		
		verify(manager).persist(order);
	}
	
}
