package com.fdmgroup.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.fdmgroup.entities.User;

public class UserDaoImpl implements UserDAO {

	private EntityManagerFactory factory;

	public UserDaoImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}

	public void addUser(User newUser) {
		EntityManager manager = factory.createEntityManager();

		if (getUser(newUser.getEmailAddress()) == null) {

			manager.getTransaction().begin();
			manager.persist(newUser);
			manager.getTransaction().commit();
			System.out.println(
					"SUCCESS: New user has been added: " + newUser.getFirstName() + " " + newUser.getLastName());
		} else {
			System.out.println("Could not add '" + newUser.getFirstName() + " " + newUser.getLastName()
					+ "'. User already exists");
		}
	}

	public User getUser(String emailAddress) {
		EntityManager manager = factory.createEntityManager();
		User user = manager.find(User.class, emailAddress);

		return user;
	}

	public void removeUser(String emailAddress) {

		EntityManager manager = factory.createEntityManager();

		User user = manager.find(User.class, emailAddress);
		manager.getTransaction().begin();

		manager.remove(user);
		manager.getTransaction().commit();
		System.out.println("SUCCESS: User with email address '" + emailAddress + "' has been removed.");
	}

	public List<User> getAllUsers() {
		EntityManager manager = factory.createEntityManager();
		
		TypedQuery<User> query = manager.createQuery("select u from User u", User.class);
		List<User> listOfAllUsers = query.getResultList();
		
		return listOfAllUsers;
	}

}