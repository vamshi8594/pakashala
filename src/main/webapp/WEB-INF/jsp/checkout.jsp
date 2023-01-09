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
<script src="https://code.jquery.com/jquery-3.6.1.js" crossorigin="anonymous"></script>
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
.form_button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer; 
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
      <li class="nav-item ">
        <a class="nav-link" href="/about">About Us</a>
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
    <form class="form-inline my-2 my-lg-0" action='/search' method="POST">
      <input class="form-control mr-sm-2" type="search"  name='search' placeholder="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>  
  </div>
  
</nav>
<div class="container mt-5"> 
  		<div><button class="form_button" onclick="history.back()" type="button">Back</button> </div>
  		<div class="row">  
 	 <div class="col-sm-4 justify-content-between" style="padding-left: 100px; ">
 	 <form   method="POST" >
 	 			<c:if test="${not empty cartit}"> 
	      			<c:forEach var="it" items="${cartit}">
	      				<li class="nav-item" style="list-style:none;"> <label class="nav-link"><c:out value="${it.key.name}"/>&nbsp&nbsp&nbsp
	      				<div style="display:inline-flex;font-size:x-large;"><div onclick="reduceitem('<c:out value="${it.key.itemid}"/>')">-</div>&nbsp&nbsp<span id="<c:out value="${it.key.itemid}"/>"><c:out value="${it.value}"/></span>&nbsp&nbsp<div onclick="increaseitem('<c:out value="${it.key.itemid}"/>')">+</div></div></label></li>  
	<!--       				  <a href="#" ><img alt="delete" style="width:12px;height:12px;" src="img/x.png"></a> -->
							<div class="col-sm-4" >
								 <img alt="meal1" id="imageResult" src="<c:out value="${it.key.filepath}"/>" style="width: 300px; height: 200px;"> 
							</div>
					</c:forEach> 
					<div>
						<label>Delivery Address:</label><input type="text" id='address' placeholder="Enter Address for delivery"> 
					</div>
					<div>
						<label>Notes:</label><input type="text" id='notes' placeholder="Enter Notes to prepare and deliver"> 
					</div>
					<div><input class="form_button"  type="button" onclick="processPayment()" value="Proceed to Payment"> </div>
<!-- 					<a class="nav-link btn" href="#" onclick="document.getElementById('cartclear').submit();" >Clear</a> -->
				</c:if>  
	   	 </form>
	   	 
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
<script>
function ordernow(){
	
}
function addcart(){
	window.redirect();
}

function reduceitem(id){
	var count=document.getElementById(id).textContent;
	if(count==1){
		location.href="/home";
	}
	document.getElementById(id).innerHTML=parseInt(count)-1;
	$.ajax({
        type: "POST",
        url: "/ReduceItem",
        data: { id: id},
        success: function (result) {
            // do something.
        },
        error: function (result) {
            // do something.
        }
    });
}
function increaseitem(id){
	var count=document.getElementById(id).textContent;
	document.getElementById(id).innerHTML=parseInt(count)+1; 
	$.ajax({
        type: "POST",
        url: "/IncreaseItem",
        data: { id: id},
        success: function (result) {
            // do something.
        },
        error: function (result) {
            // do something.
        }
    });
}

function processPayment(){
	var address=document.getElementById("address").value;
	var notes=document.getElementById("notes").value;
	
	
	$.ajax({
        type: "GET",
        url: "/findmaterials", 
        success: function (result) { 
      	const obj = JSON.parse(result);
      	console.log(obj);
      	if(obj.isdone==true){
      		$.ajax({
                type: "POST",
                url: "/NoteAddress",
                data: { address: address, notes:notes},
                success: function (result) {
                	location.href="/payment";
                },
                error: function (result) {
                    // do something.
                }
            });
      	}
      	else{
      		var res="Following Items may not be available try again!\n";
      		for(let x in obj.object){
      			console.log(obj.object[x]);
      			for (var key in obj.object[x]) { 
           			res+=obj.object[x][key]+"\n";
      				} 
      			}
      		alert(res);
      	}
      	
        },
        error: function (result) {
            // do something.
        }
    });
	
	
}
</script>
</html>