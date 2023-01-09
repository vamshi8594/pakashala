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
.col-sm-4{
padding-top:15px;
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
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li> 
      <li class="nav-item">
        <a class="nav-link" href="/mplan">Plans</a>
      </li> 
      <li class="nav-item">
        <a class="nav-link" href="/orders">Orders</a>
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
  
  	<div><button class="form_button" onclick="additem();" type="button">Add Item</button> </div>
  	<ul id="itemslist" class="list-group list-group-light">
  	<c:forEach items="${items}" var="item">
  	<li class="list-group-item d-flex justify-content-between align-items-center">
  		<form method="POST" id="<c:out value="${item.itemid}"/>" enctype="multipart/form-data" action="/saveItem" modelAttribute="itemdetail"> <div class="row d-flex align-items-center">
	 <div class="col-sm-6" >
	 <img alt="meal1" id="imageResult" src="<c:out value="${item.filepath}"/>" style="width: 500px; height: 300px;">
	 <input type="file" name="fi" onchange="readURL(this);" >
	 </div>
 	 <div class="col-sm-6 ms-3 justify-content-between" style="padding-left: 100px; padding-right: 100px;">
 	 <div class="d-flex w-100 justify-content-between">
   	 <label>Item Name:</label>
     <input type="text" placeholder="Enter item name" name="name" value="<c:out value="${item.name}"/>" required>
   	 </div>
   	 <div class="d-flex w-100 justify-content-between">
   	 <label>Category:</label>
     <select id="selectcat" name="category" required style="width: 53%;">
	 <option value="breakfast" >Breakfast</option>
	 <option value="starter">Starter</option>
	 <option value="main course">Main Course</option>
	 <option value="dessert" >Dessert</option>
	 <option value="sides">Sides</option>
	 <option value="beverages" >Beverages</option> 
	 <option value="misc" >Miscellaneous</option>
	 </select>
	 <input type="hidden" name="itemid" value="<c:out value="${item.itemid}"/>">
	 <input type="hidden" name="filepath" value="<c:out value="${item.filepath}"/>">
	 </div>
   	 <div class="d-flex w-100 justify-content-between">
   	 <label>Price:</label>
     <input type="text" placeholder="set price " name="price"  value="<c:out value="${item.price}"/>"  required>
     </div>
   	 <div class="d-flex w-100 justify-content-between">
	 <label>Keywords:</label>
	 <input type="text" placeholder="Enter keywords" name="keywords"  value="<c:out value="${item.keywords}"/>" required>
   	 </div>
   	 <div class="d-flex w-100 ">
   	 <input type="radio" id="ena" name="enable" value="enable">
   	 <label for="ena">Enable</label>&nbsp&nbsp
	 <input type="radio" id="disa" name="enable" value="disable">
	 <label for="disa">Disable</label> 
	 </div>
	 <script type="text/javascript">
	 var ele=document.getElementById("<c:out value='${item.itemid}'/>").elements;
	 for (let i = 0; i < ele.length; i++) {
		  if (ele[i].id === "selectcat") {
			  ele[i].value="<c:out value="${item.category}"/>";
		  }
		  if (ele[i].id === "ena") {
			  <c:choose>
			  <c:when test="${item.enable eq 'enable'}">
			  ele[i].checked=true;
			  </c:when>  
			</c:choose> 
		  } 
		  if (ele[i].id === "disa") {
			  <c:choose>
			  <c:when test="${item.enable eq 'disable'}">
			  ele[i].checked=true;
			  </c:when>  
			</c:choose> 
		  } 
		} </script>
	 <div><button class="form_button" type="submit">Save</button>
	 </div> </div>  </div>
	 </form>
<!-- 	 <button class="form_button" type="button">Delete</button> -->
	 </li>
</c:forEach>
  	</ul> 
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
	node.classList.add("list-group-item");
	node.classList.add("d-flex");
	node.classList.add("justify-content-between");
	node.classList.add("align-items-center");
	node.innerHTML='<form method="POST" id="itform" enctype="multipart/form-data" action="/saveItem" modelAttribute="itemdetail"> <div class="row d-flex align-items-center">'+
	' <div class="col-sm-6" >'+
	' <img alt="meal1" id="imageResult" src="img/default_meal.png" style="width: 500px; height: 300px;">'+
	' <input type="file" name="fi" onchange="readURL(this);" >'+
	' </div>'+
 	' <div class="col-sm-6 ms-3 justify-content-between" style="padding-left: 100px; padding-right: 100px;">'+
 	' <div class="d-flex w-100 justify-content-between">'+
   	' <label>Item Name:</label>'+
    ' <input type="text" placeholder="Enter item name" name="name" required>'+
   	' </div>'+
   	' <div class="d-flex w-100 justify-content-between">'+
   	' <label>Category:</label>'+
    ' <select id="selectcat" name="category" required style="width: 53%;">'+
	' <option value="breakfast" >Breakfast</option>'+
	' <option value="starter">Starter</option>'+
	' <option value="main course">Main Course</option>'+
	' <option value="dessert" >Dessert</option>'+
	' <option value="sides">Sides</option>'+
	' <option value="beverages" >Beverages</option> '+
	' <option value="misc" >Miscellaneous</option> '+
	' </select>'+
	' </div>'+
   	' <div class="d-flex w-100 justify-content-between">'+
   	' <label>Price:</label>'+
    ' <input type="text" placeholder="set price " name="price" required>'+
    ' </div>'+
   	' <div class="d-flex w-100 justify-content-between">'+
	' <label>Keywords:</label>'+
	' <input type="text" placeholder="Enter keywords" name="keywords" required> '+
   	' </div> <input type="hidden" name="itemid" value="new">'+
   	' <div class="d-flex w-100 ">'+
   	' <input type="radio" id="ena" name="enable" value="enable">'+
   	' <label for="ena">Enable</label>&nbsp&nbsp'+
	' <input type="radio" id="disa" name="enable" value="disable">'+
	' <label for="disa">Disable</label>'+
	' </div>'+
	' <div><button class="form_button" type="submit">Save</button>'+
	' </div> </div>  </div></form>'; 
	document.getElementById("itemslist").prepend(node);
}
//<button class="form_button" type="button">Delete</button>
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#imageResult')
                .attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
}
 
</script>
</html>