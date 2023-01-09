<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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

.form_button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer; 
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
      <li class="nav-item ">
        <a class="nav-link" href="/manage">Home </a>
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
      <li class="nav-item active">
        <a class="nav-link" href="#">MaterialUsage <span class="sr-only">(current)</span></a>
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
  	<ul id="itemslist" class="list-group list-group-light">
  	<c:forEach items="${items}" var="item">
  	<li class="list-group-item d-flex justify-content-between align-items-center">
  	<div class="row d-flex align-items-center">
	 <div class="col-sm-6" >
	 <img alt="meal1" id="imageResult" src="<c:out value="${item.filepath}"/>" style="width: 500px; height: 300px;"> 
	 </div>
 	 <div class="col-sm-6 ms-3 justify-content-between" style="padding-left: 100px; padding-right: 100px;">
 	 <div class="d-flex w-100 justify-content-between">
   	 <label>Item Name:</label>
     <label><c:out value="${item.name}"/></label>
   	 </div>
   	 <div class="d-flex w-100 justify-content-between" >
   	 		 <ul id="<c:out value="${item.itemid}"/>">
   	 		 </ul>
			
   	 </div>  
	 <a ><span onclick="addMaterial('<c:out value="${item.itemid}"/>')"><h4>+</h4></span></a>
	   </div>   
	 </li>
</c:forEach>
  	</ul> 
  </div> 
  <c:forEach items="${items}" var="item">
	 	<script type="text/javascript"> 
	 	$.ajax({
            type: "POST",
            url: "/musageitems",
            data : {itemid: '<c:out value="${item.itemid}"/>' },
            success: function (result) { 
            	 var jsonData = JSON.parse(result);
            	 for (var i = 0; i < jsonData.length; i++) {
            	     var mu = jsonData[i]; 
            	     console.log(mu.mId);
            	     console.log(mu.portion);
            	    fnSingleMat( mu.mId,mu.portion,mu.itemId);
            	 }
            },
            error: function (result) {
                // do something.
            }
        });
	 	</script>
	 </c:forEach>
  <script>
  
 function fnSingleMat(mid,portion,itemid){
  $.ajax({
 	    type: "POST",
 	    url: "/smaterial",
 	 data : {mid: mid },
 	    success: function (result) { 
 	    	var ele=document.getElementById(itemid);
 	    	 var jsonData = JSON.parse(result); 
 	    	 var li = document.createElement('li');
 	    	li.innerHTML = jsonData.name+":"+portion+" "+jsonData.units;  
 	    	li.setAttribute('style', 'display: block;');   

	        ele.appendChild(li);
 	    },
 	    error: function (result) {
 	        // do something.
 	    }
 	}); 
 }
  </script>
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

function addMaterial(id){
	var ul=document.getElementById(id);
  	var li = document.createElement('li');    
  	var select = document.createElement( 'select' );
  	var option;
  	$.ajax({
  	    type: "POST",
  	    url: "/materials",
  	    success: function (result) {
  	    	 console.log(result); 
  	    	 var jsonData = JSON.parse(result);
  	    	 for (var i = 0; i < jsonData.length; i++) {
  	    	     var mat = jsonData[i]; 
  	    	     option = document.createElement( 'option' );

  	             option.value = mat.mId;
  	             option.textContent = mat.name;

  	             select.appendChild( option ); 
  	    	 }
  	    },
  	    error: function (result) {
  	        // do something.
  	    }
  	}); 
  	var s2 = document.createElement( 'select' );
    li.append(select);
    op1 = document.createElement( 'option' ); 
    op1.value = op1.textContent = "kg";
    op2 = document.createElement( 'option' ); 
    op2.value = op2.textContent = "grams"; 
    op3 = document.createElement( 'option' ); 
    op3.value = op3.textContent = "ea"; 
    s2.appendChild( op1 );
    s2.appendChild( op2 );
    s2.appendChild( op3 );
    li.append(s2);
    var x = document.createElement("INPUT");
    x.setAttribute("type", "number");
    x.setAttribute("placeholder", "Enter portion");
    x.setAttribute("min", "0");
    x.setAttribute("width", "90px");
    x.setAttribute("height", "23px");
    x.setAttribute("required", "");
    li.append(x);
    li.setAttribute('style', 'display: block;'); 
    var btn = document.createElement("button");
    btn.setAttribute("class","form_button");
    btn.innerText="Save"; 
    console.log(select.value);
    btn.addEventListener('click', () => {
    	$.ajax({
            type: "POST",
            url: "/addMaterialUsage",
            data: {itemid:id,mid:select.value, portion:x.value, units:s2.value},
            success: function (result) {
            	alert("success");
            	location.href="/musage";
            },
            error: function (result) {
                // do something.
            }
        });
    	  
    	})
    li.append(btn);
    ul.appendChild(li);   
}
 
</script>
</html>