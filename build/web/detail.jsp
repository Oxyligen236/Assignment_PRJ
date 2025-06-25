<%-- 
    Document   : detail
    Created on : Jun 10, 2025, 2:01:52 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
    </head>
    <body>
        <div> 
             <h2>Product Information</h2>
        </div>
       
        <div class="row">
            <div class="col-2"></div>
            <div class="col-4 product-img">
                
            </div>
            <div class="col-4 product-info">
                <h2>${product.productName}</h2>
                <hr/>   
                <h3>Overview</h3> <br/><br/>
                <p>${product.description}</p>
            </div>
            <div class="col-2"></div>
        </div>
        <c:set var="images" value="${requestScope.images}"/>
         <c:set var="product" value="${requestScope.product}"/>
        <img src="${images[0]}" width="300" height="250"/>

      
        
        <p>Price: ${product.price}</p>
        <p>Quantity: ${product.productQuantity}</p>
        
        
   
        <p>Brand: ${product.brand.brandName}</p> 

        <!-- Back -->
        <a href="home">Back to Home</a>

    </body>
</html>
