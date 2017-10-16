<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table, th{
	text-align: left;
}
</style>
</head>
<body>
${message}
<a href="home"> Home </a> <br/>
	<h2>Shopping Cart</h2>
	<h3>Total Amount : �${cart.total}</h3><br/>
	<c:forEach items="${cart.cartItems}" var="c">
	<table>
		<tr>
		<th><a href="displayBook?isbn=${c.book.isbn}"><img src="${c.book.imageUrl}" height="80" width="auto"></a></th>
		<th>
		Title : <a href="displayBook?isbn=${c.book.isbn}">${c.book.title}</a><br/>
		Price : �${c.book.price}<br/>
		Subtotal : �${c.subTotal}<br/>
		<form action="updateQuantity?cartItemId=${c.cartItemId}" method="post">
			<input name="quantity" size="1" value="${c.quantity}">
			<input type="Submit" value="Update" />
		</form>
		</th>
		</tr>
	</table>
	</c:forEach>
		<hr>
</body>
</html>