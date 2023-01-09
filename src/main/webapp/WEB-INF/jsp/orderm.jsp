<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="com.vamshi.pakashala.service.GetItems"%>
<%@page import="com.vamshi.pakashala.entity.ItemDetails"%>

<!DOCTYPE html>
<html>
<head>
  <title>PakaShala</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.1.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://www.paypal.com/sdk/js?client-id=AbfMCdBJ3uHXNA6cxm7luMelON_F6TsrN5f2H6-Pgxrkh3Z7bEkC_h6IvSyUDBMpw2osCJNMCUX0IXK-&currency=USD&intent=capture&enable-funding=venmo" data-sdk-integration-source="integrationbuilder"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<style>
.footer{
color:white;
font-size: larger;
padding: inherit;
}
img{
width:100%;
height:100%;
}
.container{
padding-bottom:30px;
}
#username{
 padding: 5px;
}
ul{
list-style: none;
}
.col-sm-4{
padding-top:15px;
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

.form_button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer; 
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
/* .text { */
/*   background-color: #04AA6D; */
/*   color: white; */
/*   font-size: 16px; */
/*   padding: 16px 32px; */
/* } */
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
        <a class="nav-link" href="/manage">Home </a>
      </li> 
      <li class="nav-item">
        <a class="nav-link" href="/mplan">Plans</a>
      </li> 
      <li class="nav-item active">
        <a class="nav-link" href="#">Orders <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/materials">Materials</a>
      </li>  
      <li class="nav-item">
        <a class="nav-link" href="/musage">MaterialUsage</a>
      </li>
     <li class="nav-item dropdown">
        <a class="nav-link btn btn-default dropdown-toggle" href="#" data-toggle="dropdown"> ${message} <span class="caret"></span></a>
         <ul class="dropdown-menu">  
      			<li> <a  class="nav-link" href="/mlogout">Logout</a></li> 
    	</ul>
      </li>   
    </ul> 
  </div>
  
</nav>

<div class="container mt-5">
<div><h4>Orders in Queue</h4></div>
  <div class="row">
  	
    <div class="navbar col-sm-4 text-center">
     <ul id="planlist" class="navbar-nav sidemenu">  
     <li>
     <c:forEach items="${orders}" var="order">
     <form method="POST"   id="<c:out value="${order.order_id}"/>" action="/confirmorder" >
     <input type="hidden" name="orderid" value="<c:out value="${order.order_id}"/>">
      <div class="d-flex w-100 justify-content-between">
   	 <label>Order Date:</label>
     <label><c:out value="${order.date}"/></label>   
   	 </div>  
   	  <div class="d-flex w-100 justify-content-between">
   	 <label>Total Price:</label>
     <label><c:out value="${order.totalPrice}"/></label>   
   	 </div> 
   	 <div class="d-flex w-100 justify-content-between">
   	 <label>Status:</label>
     <label><c:out value="${order.paidStatus}"/></label>   
   	 </div>
   	 <div class="d-flex w-100 justify-content-between">
   	 <label>Items in Order:</label>
   	 	<ul> 
        <c:forEach items="${order.idl}" var="item">
        <li>
        	<c:out value="${item.name}"/> (<c:out value="${item.count}"/>)
        </li>
        </c:forEach>
        </ul>
   	 </div>
	 <div class="d-flex w-100 justify-content-between"><button class="form_button" type="submit">Confirm</button>
	 <input class="form_button" type="button" value='Deny' onclick="denyOrder('<c:out value="${order.order_id}"/>')"> 
	 </div>
	 </form> 
	 </c:forEach>
   	 </li>
    </ul>
    </div> 
   
  </div>
</div>

<div class="container-fluid p-5 bg-primary text-white text-center" style="background-color:#89939d! important;">
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
function denyOrder(oid){
	$.ajax({
        type: "POST",
        url: "/denyOrder", 
        data: {orderid: oid},
        success: function (result) {
        	location.href="/orders";
        },
        error: function (result) {
            // do something.
        }
    });
	alert('1');
}

</script>

</html>