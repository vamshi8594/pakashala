<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>PakaShala</title>
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

body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

.form_submit {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

img.avatar {
  width: 10%; 
}

.container {
  padding: 16px;
/*   width: fit-content; */
  margin-top: 1rem!important;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}

.invaliderror{
color:red;
text-align:center;
}
#register{
margin-left: auto;
margin-right: auto;
display:none; 
}
#selectgen{
width: -webkit-fill-available;
padding: 12px 20px;
border: 1px solid #ccc;
margin: 8px 0;
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
  
</nav>  
<div class="container "> 
<c:choose>
  <c:when test="${message eq 'failed'}">
    <p class='invaliderror'>Invalid Username/password<p> 
  </c:when>   
</c:choose>
<form:form id='login' method="POST" action="/manage" modelAttribute="user">
  <div class="imgcontainer">
    <img src="img/stove.png" alt="Avatar" class="avatar">
  </div>

  <div class="container">
    <label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="uname" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>
        
    <button class="form_submit" type="submit">Login</button>  
  </div> 
</form:form> 
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
function registerview(){
	 var lform= document.getElementById('login');
	 var rform= document.getElementById('register');
	 lform.style.display='none';
	 rform.style.display='block';
}
function gotoLogin(){
	var lform= document.getElementById('login');
	 var rform= document.getElementById('register');
	 lform.style.display='block';
	 rform.style.display='none';
}
</script>
</html>