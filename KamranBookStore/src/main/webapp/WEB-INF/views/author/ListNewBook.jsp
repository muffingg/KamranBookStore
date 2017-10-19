<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="../css/Home.css" rel="stylesheet" type="text/css">
<style type="text/css">
body {
	background-color: #1A1A1A;
	background-image: url();
	background-repeat: repeat-x;
}
th {
	width: 600px;
	border-bottom: 1px solid grey;
}
</style>
</head>
<body>
<div id="mainWrapper">
  <header> 
    <!-- This is the header content. It contains Logo and links -->
    <div id="logo"> 
      <!-- Company Logo text --> 
      <a href="/KamranBookStore/home"> <img src="https://s3.amazonaws.com/media.prestontrail.org/craft/The-Bookstore-Online-Logo-600x.png?mtime=20160930115548" width="248" height="134" alt=""/> </a></div>
    <div id="headerLinks"><a href="/KamranBookStore/logout" title="Logout">Logout</a><a href="/KamranBookStore/viewCart" title="Cart">Cart</a></div>
    <div id="search">
    <form action="/KamranBookStore/viewBooks" method="get">
    <input type="text" size="40" name="searchitem" placeholder="Search for books..." type="search"; style="font-size: 14pt; margin-left: 20px; float: none; opacity: 1; position: relative; bottom: 18px">
   	  <input id="searchButton" type="submit" value="Search"/>
   	 </form>
    </div>
    <div id="nav">
		  <ul>
		  <li><a href="/KamranBookStore/home">Home</a>
		  <li><a href="../viewPersonalDetails">Profile</a>
				<ul>
			        <li><a href="/KamranBookStore/viewPersonalDetails">View Personal Details</a></li>
			        <li><a href="/KamranBookStore/changePassword">Change Password</a></li>
			        <li><a href="/KamranBookStore/viewOrders">View Order History</a></li>
			        ${authorOptions}
	         	</ul>
		    </li>
		    ${adminOptions}
		    <li><a href="viewAllBooks">Books</a>
				<ul>
			        <li><a href="/KamranBookStore/viewAllBooks">View All Books</a></li>
			        <li><a href="/KamranBookStore/searchBook">Advanced Search for Books</a></li>
	         	</ul>
		    </li>
		    <li><a href="/KamranBookStore/help">Help</a>
	        </li>
		    <li><a href="/KamranBookStore/contact">Contact Us</a>
	      </ul>
    </div>
  </header>
  <div id="content">
    <nav class="sidebar"> 
      <div id="menubar">
      	<h1>Categories</h1>
      	<div class="menu">
          <ul>
					<li><a href="/KamranBookStore/viewBiographyBooks">Biography</a></li>
                	<li><a href="/KamranBookStore/viewFictionBooks">Fiction</a></li>
                	<li><a href="/KamranBookStore/viewTechnologyBooks">Technology</a></li>
                	<li><a href="/KamranBookStore/viewTravelBooks">Travel</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="mainContent">
      <h1>List New Book</h1>
${message}

<sf:form method="post" action="/KamranBookStore/author/doListBook" modelAttribute="book" style="text-align:left;">
		<p>ISBN : <sf:input path="isbn" value=""/></p>
		<p>Title : <sf:input path="title"/></p>
		<p>Price : <sf:input path="price"/></p>
		<p>Quantity Available : <sf:input path="quantity"/></p>
		<p>Release Year : <sf:input path="releaseYear" /></p>
		<p>Number of Pages : <sf:input path="numberOfPages"/></p>
		<p>Category : <sf:input path="category"/></p>
		<p>Image (URL) : <sf:input path="imageUrl"/></p>
		<p>Author(s) : <input name="authorsString" size="100" value="${authorString}"/></p>
		List any additional authors by adding their usernames separated by a comma (Use the list of Authors below to find authors)<br/>
		(e.g. adam.kay,william.shakespeare)<br/>
		
		<form:select path="authors" items="${authorsList}"/>
		<input type="submit" value="Go"/>
	</sf:form>
      
        
      </div>
    </div>
  <footer> 
    <!-- This is the footer with default 3 divs -->
    <div><span style="line-height: 5px"> <p><strong>Books4u</strong></p>
      <p>Cottons Centre</p>
      <p> Cottons Lane</p>
      <p> London SE1 2QG</p></span>
    </div>
    <div id="2col">
    	<span style="line-height: 10px"><p>Tel: 020 3141 5926</p> 
    	<p>Email: info@books4u.com</p></span>
    </div>
    <div>
      <span style="line-height: 10px"><p>&copy; 2017 Kamran Ahmed Adnan</p>
      <p>All Rights Reserved</p></span>
    </div>
  </footer>
</div>
</body>
</html>