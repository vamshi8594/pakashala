<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>About Us</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<style>
.footer{
color:white;
font-size: larger;
padding: inherit;
}
ul{
list-style: none;
}
img{
width:100%;
height:100%;
}
#username{
 padding: 5px;
}

.img_container {
  position: relative; 
}

.image {
  opacity: 1;
  display: block;
  width: 100%;
  height: auto;
  transition: .5s ease;
  backface-visibility: hidden;
}

.middle {
  transition: .5s ease;
  opacity: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  text-align: center;
  color:white;
}

.img_container:hover .image {
  opacity: 0.3;
}

.img_container:hover .middle {
  opacity: 1;
}
.header {
  height: 30vh;
  background: radial-gradient(ellipse at center, rgba(0,0,0,0.4) 0%, rgba(153,218,255,0.3) 100%), url(img/bg4.jpg); /* radial scrim and background image */
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  text-align: center;
  padding-left: 1em;
  padding-right: 1em;
  color:white;
  text-shadow: 5px 5px 12px black;
}

.header-title {
  margin: 0;
  font-size: calc(16px + 5vw);
  letter-spacing: -1px;
  text-transform: uppercase;
}

.header-content {
  font-size: calc(16px + 1vw);
}
</style>
<body>

<div class="header">
  <h1 class="header-title">Pakashala</h1>
  <p class="header-content">Feels like Meals from your own Kitchen</p> 
</div>

<nav class="navbar navbar-expand-lg sticky-top navbar-light" style="background-color: #e3f2fd;">
  <a class="navbar-brand" href="/home">Pakashala</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item">
        <a class="nav-link" href="/home">Home </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">About Us </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/plans">Plans </a>
      </li>
       <c:choose>
  <c:when test="${message eq 'failed'}">
  <li class="nav-item">
     <a class="nav-link" href="/loginreg">login/Register</a>
     </li>
  </c:when> 
  <c:otherwise>
  <li class="nav-item">
        <a class="nav-link" href="/uorders">Orders</a>
      </li> 
  <li class="nav-item dropdown">
        <a class="nav-link btn btn-default dropdown-toggle" href="/plans" data-toggle="dropdown"> ${message} <span class="caret"></span></a>
         <ul class="dropdown-menu">  
      			<li> <a  class="nav-link" href="/logout">Logout</a></li> 
    	</ul>
      </li>
   
  </c:otherwise>
</c:choose>
    </ul>
    <c:choose>
  <c:when test="${message ne 'failed'}">
  <ul class="navbar-nav ">
      <li class="nav-item dropdown">
      <a  class="nav-link btn btn-default dropdown-toggle" data-toggle="dropdown" href="#">Cart
      <c:if test="${not empty cartcount}">
      		<c:if test="${cartcount gt 0}">
      			(<c:out value="${cartcount}"/>)
      			</c:if> 
      			</c:if>
      <span class="caret"></span></a>
      <ul class="dropdown-menu">  
      			<c:if test="${empty cartit}">
      				<li> <Label class="nav-link">No items in Cart</Label></li> 
      			</c:if>
      			<c:if test="${not empty cartit}"> 
	      			<c:forEach var="it" items="${cartit}">
	      				<li class="nav-item"> <label class="nav-link"><c:out value="${it.key.name}"/>(<c:out value="${it.value}"/>)</label></li>  
					</c:forEach>
					<li class="nav-item justify-content-between">
					<form action="/clearcart" id='cartclear'> 
					<input type="hidden" name="dirpage" value='viewItem'>
					<input type="hidden" name="itemid" value='${sitem.itemid}'>
					<a class="nav-link" href="#" onclick="document.getElementById('cartclear').submit();" >Clear</a>
					<a class="nav-link" href="/checkout">CheckOut</a></form> </li> 
				</c:if>
				
    	</ul>
      </li></ul>
  </c:when>
  </c:choose>
   <form class="form-inline my-2 my-lg-0" action='/search' method="POST">
      <input class="form-control mr-sm-2" type="search"  name='search' placeholder="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>  
  </div>
  
</nav>
<h1>Search Result for: <c:out value="${searchstr}"/></h1>
<div class="container mt-5">
<c:forEach items="${sres}" var="item">
  <div class="row"> 
    <div class="col-sm-4 img_container">
    <a href="#">
    	<form method="POST" style="background:black;"  id="<c:out value="${item.itemid}"/>"  onclick="formsubmit(this)" action="/viewItem" modelAttribute="viewItem">
      <img class="image" style="width:100%" alt="<c:out value="${item.name}"/>" src="<c:out value="${item.filepath}"/>">
      <input type="hidden" name="itemid" value="<c:out value="${item.itemid}"/>">
      <div class="middle">
				    <div class="text">
				    <h2><c:out value="${item.name}"/></h2>
				    <h4>Price:<c:out value="${item.price}"/> </h4>
				    </div>
	  	</div>
      </form>
      </a>
    </div>  
  </div> 
  </c:forEach>
</div>


<div class="container-fluid p-5 bg-primary text-white text-center">
  <div class="row">
  <div class="col-sm-4" style="text-align: center;">
  	<h3>Links</h3>
  	<div style="display:inline-block;text-align: left;">
  	<ul class="mr-auto mt-2 mt-lg-0" >
  	<li class="nav-item" >
        <a class="nav-link footer" href="/home">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link footer" href="/about">About Us</a>
      </li>
      <li class="nav-item">
        <a class="nav-link footer" href="/plans">Plans</a>
      </li> 
  	</ul>
  	</div>
  </div>
  <div class="col-sm-4">
  	<h3>Contact</h3>
  	<ul>
  	<li class="nav-item">
        ph: xx xxx xxxxx 
      </li>
      <li class="nav-item">
        Email: vamshi.guntupalli@gmail.com
      </li>
      <li class="nav-item">
        Address: Overland Park, Kansas.
      </li> 
  	</ul>
  </div>
  <div class="col-sm-4">
  	<h3>Follow Us</h3> 
  	<a href="#" class="fa fa-facebook footer"></a>
	<a href="#" class="fa fa-twitter footer"></a> 
	<a href="#" class="fa fa-linkedin footer"></a>
	<a href="#" class="fa fa-youtube footer"></a> 
  </div>
  </div>
 
</div>
<div class="container-fluid p-3 text-center">
 <div class="row">
  <div class="col-sm-12">
  		© 2022 Pakashala. All rights reserved.
  </div>
  </div>
</div>
</body>
<script type="text/javascript">
function formsubmit(fid){ 
	fid.submit();
} 
</script>
</html>