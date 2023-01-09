<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%> 
<%@page import="com.vamshi.pakashala.entity.Plans"%>
<%@page import="com.vamshi.pakashala.entity.Subs"%>  
<%@page import="java.time.temporal.ChronoUnit"%> 
<!DOCTYPE html>
<html>
<head>
  <title>Plans</title>
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
.sidemenu {
  width: 100%;
} 
#recentorder{
	display:none;
	margin: 50px;
    border: 1px solid white;
    color: white;
    padding: 50px;
    border-radius: 10px;
}
#nextmeal{
	display:none;
	margin: 50px;
    border: 1px solid white;
    color: white;
    padding: 50px;
    border-radius: 10px;
}
.forminput{
	background:transparent;
	color: white;
	border: 1px solid white;
    border-radius: 3px;
}
#currentplan{
	margin: 50px;
    border: 1px solid white;
    color: white;
    padding: 50px;
    border-radius: 10px;
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
.page-length{
	min-height: 75vh;
	background: radial-gradient(ellipse at center, rgba(0,0,0,0.4) 0%, rgba(153,218,255,0) 100%), url(img/bg6.jpg);
	background-position: center center;
    background-repeat: no-repeat;
    background-size: cover;
    display: flex;
    width: 100vw;
}
.hero {
  min-height: 100vh;
  background: radial-gradient(ellipse at center, rgba(0,0,0,0.4) 0%, rgba(153,218,255,0) 100%), url(img/bg5.jpg); /* radial scrim and background image */
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: end;
  flex-direction: column;
  text-align: right;
  padding-left: 1em;
  padding-right: 6em;
      color: #b9ddff;
    text-shadow: 0 0 7px #000000;
}

.hero-title {
  margin: 0;
  font-size: calc(16px + 5vw);
  letter-spacing: -1px;
  text-transform: uppercase;
  padding-top: 50px;
}

.hero-content {
  font-size: calc(16px + 1vw);
  padding-bottom: 30px;
}
.btn-5 {
  border: 0 solid;
  box-shadow: inset 0 0 20px rgba(255, 255, 255, 0);
  outline: 1px solid;
  outline-color: rgba(255, 255, 255, .5);
  outline-offset: 0px;
  text-shadow: none;
  transition: all 1250ms cubic-bezier(0.19, 1, 0.22, 1);
}
.btn-5:hover, .btn-5.active {
  border: 1px solid;
  box-shadow: inset 0 0 20px rgba(255, 255, 255, .5), 0 0 20px rgba(255, 255, 255, .2);
  outline-color: rgba(255, 255, 255, 0);
  outline-offset: 15px;
  text-shadow: 1px 1px 2px #427388;
  color: azure;
}

.btnv {
  color: #fff;
  cursor: pointer;
  // display: block;
  font-size:16px;
  font-weight: 400;
  line-height: 45px;
  margin: 0 0 2em; 
  position: relative;
  text-decoration: none;
  text-transform: uppercase;
  width: 100%; 
  
//   @media(min-width: 400px) {
//     display: inline-block;
//     margin-right: 2.5em;
  
//     &:nth-of-type(even) {
//       margin-right: 0;
//     }
//   }
  
  @media(min-width: 600px) {
      
    margin: 0 1em 2em;
  
//     &:nth-of-type(even) {
//       margin-right: 2.5em;
//     }
    
//     &:nth-of-type(5) {
//       margin-right: 0;
//     }
    
  }
  
  &:hover { text-decoration: none; }
  
}
.eachmeal{
padding:10px;
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
        <a class="nav-link" href="/about">About Us</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#">Plans <span class="sr-only">(current)</span></a>
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
					<input type="hidden" name="dirpage" value='plans'>
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

<c:choose>
 <c:when test="${message eq 'failed'}">

<div class="hero">
<c:forEach items="${plans}" var="plan">
		  <h1 class="hero-title"><c:out value="${plan.name}"/> Plan</h1>
		  <div class="hero-content"><label><c:out value="${plan.desc}"/></label><br>
		  <label>price: <c:out value="${plan.price}"/>$</label><br>
		  <label>Number of Meals: <c:out value="${plan.nmeals}"/></label><br>
		  <label>Validity : <c:out value="${plan.validity}"/> Days</label> </div> 
</c:forEach> 
</div>
  
  </c:when> 
  <c:otherwise>

<div >
  <div class="page-length">
  		
  	<c:if test="${empty mealadderror}">
  		<c:choose>
 			<c:when test="${mealadderror eq 'failed'}">
 					<div>Unable to add meal</div>
      			</c:when>
      			</c:choose> 
      </c:if>
    <div class="navbar col-sm-4 text-center">
     <ul class="navbar-nav sidemenu">
      
      <li class="nav-item">
        <a class="nav-link btnv btn-5 active" id="currentplant" href="#" onclick="changeview('currentplan')"><h2>Current Plan</h2></a>
      </li>
      <li class="nav-item">
        <a class="nav-link btnv btn-5" id="nextmealt" href="#" onclick="changeview('nextmeal')"><h2>Pick Next Meal</h2> </a>
      </li>
      <li class="nav-item">
        <a class="nav-link btnv btn-5" id="recentordert" href="#" onclick="changeview('recentorder')"><h2>Recently Ordered</h2> </a>
      </li>  
    </ul> 
    </div> 
    <div class="col-sm-6" id="currentplan">
     	<c:choose>
 		<c:when test="${empty currentplan.name}">
		 	No current plan.
		 	
		 	<h3>Plans Available</h3>
      	<c:forEach items="${plans}" var="plan">
      		<form action="/selectplan" method="post" modelAttribute="plans">
			  <h4><c:out value="${plan.name}"/> Plan</h4>
			  <input type="hidden" name='planid' value="<c:out value="${plan.planid}"/>">
			  <div><label><c:out value="${plan.desc}"/></label><br>
			  <label>price: <c:out value="${plan.price}"/>$</label><br>
			  <label>Number of Meals: <c:out value="${plan.nmeals}"/></label><br>
			  <label>Validity : <c:out value="${plan.validity}"/> Days</label> </div> 
			  <input type="submit" value="select">
			 </form>
		</c:forEach> 
		</c:when> 
  		<c:otherwise>
  			 <% 
      	Subs sb = (Subs)request.getAttribute("subscription");
      	
      	Calendar cal = Calendar.getInstance();
      	Date sd=cal.getTime();
      	Date ed=sb.geteDate();
      	int val=(int)( (ed.getTime() - sd.getTime()) / (1000 * 60 * 60 * 24)); 
	  %>
  			<h3>Your Plan : <c:out value="${currentplan.name}"/></h3> 
			  <div><label><c:out value="${currentplan.desc}"/></label><br> 
			  <label>Number of Meals Left: <c:out value="${subscription.nRemain}"/></label><br>
			  <label>Validity : <c:out value="<%=val%>"/> Days Left</label> </div>
      	</c:otherwise>
      	</c:choose>
      	
    </div>
    <div class="col-sm-6" id="recentorder"> 
      <c:choose>
 		<c:when test="${empty orders}">
 			No Recent orders.
 		</c:when>
 		<c:otherwise>
 			<c:forEach items="${orders}" var="ord">
 				<div>
 					<h3>Order ID:  <c:out value="${ord.order_id}"/></h3>
 					Order Items: <c:out value="${ord.item_id}"/>
 					Purchase Date: <c:out value="${ord.date}"/>
 					Total Price:  <c:out value="${ord.price}"/>
 				</div>
 			</c:forEach>
 		</c:otherwise>
 		</c:choose>
    </div> 
    <div class="col-sm-6" id="nextmeal">
    <c:choose>
 		<c:when test="${empty currentplan.name}">
 			Select a plan to choose meals.
 		</c:when>
 		<c:otherwise>
      
      <% 
      	Plans planc = (Plans)request.getAttribute("currentplan");
      	Subs sb = (Subs)request.getAttribute("subscription"); 
    	Date ed=sb.geteDate();
      	Calendar cal = Calendar.getInstance();
      	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
      	cal.add(Calendar.DAY_OF_MONTH, 1); 
		String minDate =sdf.format(cal.getTime()); // new SimpleDateFormat("yyyy-MM-dd").format(cal.add(Calendar.DAY_OF_MONTH, 3).getTime());
		cal.add(Calendar.DAY_OF_MONTH, planc.getValidity()); 
		String maxDate =sdf.format(cal.getTime());
	  %>
	  <form action="/selectmeal" method="post" modelattribute="mealpref">
	  <h4>Pick Next meal</h4>
      		<label>Date:</label><input type="date" id="mealcal" name="mealdate" min="<%=minDate%>" max="<%=ed%>" onchange="changedate('mealcal')">
	      <div id="datespecific"> 
	      		 <label>Meal Count :</label><input class="forminput" type="number" name="mcount" value="1"><br>
	      		 <label>Address</label> <input class="forminput" type="text" name="address"><br>
	      		 <input type="radio" id="brkf" name="tod" value="Breakfast">
			   	 <label for="brkf">Breakfast</label>
				 <input type="radio" id="lunch" name="tod" value="Lunch">
				 <label for="lunch">Lunch</label> 
				 <input type="radio" id="dinner" name="tod" value="Dinner">
				 <label for="dinner">Dinner</label><br>
				 <label>Notes :</label>
	      		 <input class="forminput" type="text" name="notes">  
	      </div>
      		<input type="submit" value="submit">
      </form>
      
      </c:otherwise>
      </c:choose>
      <c:choose>
 		<c:when test="${not empty meals}">
 				<h4>Upcoming Meals:</h4>
 				<c:forEach items="${meals}" var="meal">
 				<div class="eachmeal">
 					<label>Date: </label><c:out value="${meal.mDate}"/><br>
 					<label>During:</label><c:out value="${meal.tod}"/><br>
 					<label>No of People:</label><c:out value="${meal.mcount}"/><br> 
 					<label>Address:</label><c:out value="${meal.address}"/><br>
 					<label>Notes:</label><c:out value="${meal.notes}"/><br>
 				</div>
 				</c:forEach>
 		</c:when>
 		</c:choose>
    </div> 
  </div>
</div>
   
  </c:otherwise>
</c:choose>




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
<script type="text/javascript">
function changeview(linkid){ 
	switch(linkid) {
	  case "currentplan":
		  document.getElementById(linkid).style.display="block";
		  document.getElementById("recentorder").style.display="none";
		  document.getElementById("nextmeal").style.display="none";
		  document.getElementById(linkid+"t").classList.add("active");
		   document.getElementById("recentordert").classList.remove("active");
		   document.getElementById("nextmealt").classList.remove("active");
	    break;
	  case "recentorder":
		  document.getElementById(linkid).style.display="block";
		  document.getElementById("nextmeal").style.display="none";
		  document.getElementById("currentplan").style.display="none";
		  document.getElementById(linkid+"t").classList.add("active");
		  document.getElementById("currentplant").classList.remove("active");
		   document.getElementById("nextmealt").classList.remove("active");
	    break;
	  case "nextmeal":
		  document.getElementById(linkid).style.display="block";
		  document.getElementById("recentorder").style.display="none";
		  document.getElementById("currentplan").style.display="none"; 
		  document.getElementById("recentordert").classList.remove("active");
		   document.getElementById("currentplant").classList.remove("active");
		   document.getElementById(linkid+"t").classList.add("active");
	    break;
	  default: 
	}
}

function changedate(date){
	var pd=document.getElementById(date); 
}
</script>
</body>
</html>