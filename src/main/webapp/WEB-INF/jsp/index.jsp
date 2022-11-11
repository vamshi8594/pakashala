<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</head>
<style>
img{
width:100%;
height:100%;
}
#username{
 padding: 5px;
}
.col-sm-4{
padding-top:15px;
}
</style>
<body>

<div class="container-fluid p-5 bg-primary text-white text-center">
  <h1>Pakashala</h1>
  <p>A Telugu Kitchen</p> 
</div>


<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
  <a class="navbar-brand" href="/home">Pakashala</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/about">About Us</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/plans">Plans</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/loginreg">
 <c:choose>
  <c:when test="${message eq 'failed'}">
    login/Register
  </c:when> 
  <c:otherwise>
    ${message}
  </c:otherwise>
</c:choose>
        
        
        </a>
      </li>
    </ul>
    
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form> 
  </div>
  
</nav> 
 <c:choose>
  <c:when test="${message eq 'failed'}">
     
  </c:when> 
  <c:otherwise>
  <a  href="/logout">Logout</a>
  </c:otherwise>
</c:choose>
<div class="container mt-5">
  <div class="row">
    <div class="col-sm-4">
      <img alt="meal1" src="img/meal1.jpg">
    </div>
    <div class="col-sm-4">
      <img alt="meal1" src="img/meal2.jpg">
    </div>
    <div class="col-sm-4">
      <img alt="meal1" src="img/meal3.jpeg">
    </div>
    <div class="col-sm-4">
      <img alt="meal1" src="img/meal4.jpg">
    </div>
    <div class="col-sm-4">
      <img alt="meal1" src="img/meal5.jpg">
    </div>
    <div class="col-sm-4">
      <img alt="meal1" src="img/meal6.jpg">
    </div>
  </div>
</div>
</body>
</html>