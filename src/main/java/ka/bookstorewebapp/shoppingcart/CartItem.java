package ka.bookstorewebapp.shoppingcart;

import ka.bookstorewebapp.entities.Book;

import javax.persistence.*;

@Entity
@Table(name = "CARTITEMS")
public class CartItem {

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Id
    @GeneratedValue
    private int cartItemId;
    private long isbn;
    @ManyToOne(fetch = FetchType.EAGER)
    private Cart cart;
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;
    private int quantity;
    private double subTotal;

    public CartItem() {
    }

    public CartItem(Book book, int quantity, Cart cart) {
        this.isbn = book.getIsbn();
        this.book = book;
        this.quantity = quantity;
        this.cart = cart;
        subTotal = quantity * book.getPrice();
    }

    public long getIsbn() {
        return isbn;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateSubTotal();
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void addToCartItem(int qty) {
        quantity += qty;
        calculateSubTotal();
        cart.calculateTotal();
    }

    private void calculateSubTotal() {
        subTotal = book.getPrice() * quantity;
    }

    public void removeFromCartItem(int qty) {
        quantity -= qty;
        calculateSubTotal();
    }

}
