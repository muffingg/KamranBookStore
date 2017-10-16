package com.fdmgroup.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.daos.BookDAO;
import com.fdmgroup.daos.CartDAO;
import com.fdmgroup.daos.CartDaoImpl;
import com.fdmgroup.daos.CartItemDAO;
import com.fdmgroup.daos.CartItemDaoImpl;
import com.fdmgroup.daos.UserDAO;
import com.fdmgroup.daos.UserDaoImpl;
import com.fdmgroup.entities.Book;
import com.fdmgroup.entities.User;
import com.fdmgroup.shoppingcart.Cart;
import com.fdmgroup.shoppingcart.CartItem;

@Controller
public class BookController {
	
	@Autowired
	private EntityManagerFactory factory;
	
	@Autowired
	private BookDAO bookDao;
	
	@RequestMapping("/viewAllBooks")
	public String goToViewAllBooks(Model model){
		
		List<Book> booksList = bookDao.getAllBooks();
		model.addAttribute("booksList", booksList);
		return "ViewAllBooks";
	}
	
	@RequestMapping("/displayBooks")
	public String goToDisplayBooks(@RequestParam Long isbn, @RequestParam String title, @RequestParam String author, @RequestParam String category, @RequestParam Double min, @RequestParam Double max, Model model){

		List<Book> booksList;
		if (isbn != null){
			Book book = bookDao.getBook(isbn);
			booksList = new ArrayList<Book>();
			booksList.add(book);
		}else{
			System.out.println("Running Else part");
			booksList = bookDao.getBooksByAllAttributes(title, author, category, min, max);
		}
		model.addAttribute("booksList", booksList);
		return "ViewBooks";
	}
	
	@RequestMapping("/displayBook")
	public String goToDisplayBook(@RequestParam Long isbn, Model model){
		
		Book book = bookDao.getBook(isbn);
		
		model.addAttribute("book", book);
		
		return "ViewBook";
	}
	
	@RequestMapping("/viewBooksByCategory")
	public String goToViewBooksByCategory(@RequestParam String category, Model model){
		
		List<Book> booksList = bookDao.getBooksByCategory(category);
		
		model.addAttribute("booksList", booksList);
		
		return "ViewBooks";
	}
	
	@RequestMapping("/addBookToBasket")
	public String doAddBookToBasket(@RequestParam Long isbn, @RequestParam Integer quantity, Model model, Principal principal, HttpSession session){
		
		UserDAO userDao = new UserDaoImpl(factory);
		Book book = bookDao.getBook(isbn);
		User user = userDao.getUser(principal.getName());
		
		if (quantity <1){
			model.addAttribute("message", "Quantity must be 1 or more");
		}else{
			CartItemDAO cartItemDao = new CartItemDaoImpl(factory);
			CartDAO cartDao = new CartDaoImpl(factory);
			
			
			Cart cart = null;
			if (user.getCart() == null){
				cart = new Cart();
				cartDao.addCart(cart);
				user.setCart(cart);
			}else{
				cart = user.getCart();
			}
	
			CartItem cartItem = new CartItem(book, quantity, cart);
			cartItemDao.addCartItem(cartItem);
			
			cart.addCartItem(cartItem);
			userDao.updateUser(user);
			
			model.addAttribute("message", "<h4>Successfully added to <a href='viewCart'>Shopping Cart</a><h4>");
		}
		model.addAttribute("book", book);
		return "ViewBook";
	}
	
	@RequestMapping("/rateBook")
	public String doRateBook(@RequestParam long isbn, Model model, HttpServletRequest request){
		
		int rating = Integer.parseInt(request.getParameter("rating"));
		
		Book book = bookDao.getBook(isbn);
		book.addCustomerRating(rating);
		bookDao.updateBook(book);
		
		model.addAttribute("book", book);
		model.addAttribute("message", "Thank you, your rating has been successfully saved<br/>");
		return "ViewBook";
	}
	
}
