<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Materials</title>
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
.sidemenu > .nav-item:hover{
    background-color: antiquewhite;
}
.form_button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer; 
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
        <a class="nav-link" href="/manage">Home </a>
      </li> 
      <li class="nav-item">
        <a class="nav-link" href="/mplan">Plans</a>
      </li>  
      <li class="nav-item">
        <a class="nav-link" href="/orders">Orders</a>
      </li> 
       <li class="nav-item active">
        <a class="nav-link" href="#">Material <span class="sr-only">(current)</span></a>
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
    
<!--     <form class="form-inline my-2 my-lg-0"> -->
<!--       <input class="form-control mr-sm-2" type="search" placeholder="Search"> -->
<!--       <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button> -->
<!--     </form>  -->
  </div>
  
</nav>

<div class="container mt-5">
<div><button class="form_button" onclick="additem();" type="button">Add Material</button> </div>
  <div class="row">
  	
    <div class="navbar col-sm-4 text-center">
     <ul id="mlist" class="navbar-nav sidemenu">  
     <li>
     <c:forEach items="${materials}" var="mt">
     <form method="POST"   id="<c:out value="${mt.mId}"/>" action="/saveMat" modelAttribute="material">
     <input type="hidden" name="mId" value="<c:out value="${mt.mId}"/>">
      <div class="d-flex w-100 justify-content-between">
   	 <label>Material Name:</label>
     <input type="text" placeholder="Enter material name" value="<c:out value="${mt.name}"/>" name="name" required>   
   	 </div> 
     <div class="d-flex w-100 justify-content-between">
   	 <label>Quantity:</label>
     <input type="number" placeholder="Enter quantity" value="<c:out value="${mt.quantity}"/>" name="quantity" required> 
   	 </div> 
   	 <div class="d-flex w-100 justify-content-between">
   	 <label>Units:</label>
     <input type="text" placeholder="Enter price" value="<c:out value="${mt.units}"/>" name="units" required> 
   	 </div>
     <div class="d-flex w-100 justify-content-between">
   	 <label>Default Value:</label>
     <input type="text" placeholder="Enter Description" value="<c:out value="${mt.defaultValue}"/>" name="defaultValue" required> 
   	 </div>  
     <div class="d-flex w-100">
   	 <input type="radio" id="ena" name="isEnabled" value="true">
   	 <label for="ena">Enable</label>&nbsp&nbsp
	 <input type="radio" id="disa" name="isEnabled" value="false">
	 <label for="disa">Disable</label> 
	 </div>
	 <div class="d-flex w-100"><button class="form_button" type="submit">Save</button></div>
	 </form>
	 <script type="text/javascript">
	 var ele=document.getElementById("<c:out value='${mt.mId}'/>").elements;
	 for (let i = 0; i < ele.length; i++) { 
		 
		  if (ele[i].id === "ena") {
			  console.log(i+":"+ele[i].id);
			  <c:choose>
			  <c:when test="${mt.isEnabled eq 'true'}">
			  ele[i].checked=true;
			  </c:when>  
			</c:choose> 
		  } 
		  if (ele[i].id === "disa") {
			  console.log(i+":"+ele[i].id);
			  <c:choose>
			  <c:when test="${mt.isEnabled eq 'false'}">
			  ele[i].checked=true;
			  </c:when>  
			</c:choose> 
		  } 
		} </script>
	 </c:forEach>
   	 </li>
    </ul>
    </div> 
   
  </div>
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
function additem(){
	const node = document.createElement("li");
	node.classList.add("navbar-nav");
	node.classList.add("sidemenu"); 
	node.innerHTML='<form method="POST"    action="/saveMat" modelAttribute="material">'+
	'<input type="hidden" name="mId" value="new">'+
	'<div class="d-flex w-100 justify-content-between">'+
	'<label>Material Name:</label>'+
  	'<input type="text" placeholder="Enter Material name" name="name" required>  '+
    '</div> <div class="d-flex w-100 justify-content-between">'+
  	'<label>Quantity:</label>'+
  	'<input type="number" placeholder="Enter Quantity" name="quantity" required> '+
  	'</div> <div class="d-flex w-100 justify-content-between">  <label>Units:</label>'+
  	'<input type="text" placeholder="Enter Units" name="units" required> '+
  	'</div> <div class="d-flex w-100 justify-content-between">'+
  	'<label>Default Value:</label> <input type="number" placeholder="Enter Default Value" name="defaultValue" required> '+
  	'</div>  <div class="d-flex w-100 ">'+
  	'<input type="radio" id="ena" name="isEnabled" value="true">'+
  	'<label for="ena">Enable</label>&nbsp&nbsp'+
  	'<input type="radio" id="disa" name="isEnabled" value="false">'+
	'<label for="disa">Disable</label> '+
	'</div> <div class="d-flex w-100"><button class="form_button" type="submit">Save</button></div>'+
	'</form>'; 
	document.getElementById("mlist").prepend(node);
}
 
 
</script>
</html>