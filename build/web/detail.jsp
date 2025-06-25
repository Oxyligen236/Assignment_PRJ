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
        <link rel="stylesheet" href="css/style-detail.css"/>

    </head>
    <body class="container"> 
        <c:set var="images" value="${requestScope.images}"/>
        <c:set var="product" value="${requestScope.product}"/>
        <div id="product-info-header"> 
            <h2>Product Information</h2>
        </div>

        <div class="row">
            <div class="col-2">


            </div>    

            <div class="col-4 product-img d-flex flex-column">
                <div class="img-top">
                    <img src="${images[0]}" class="img-fluid" />
                </div>
                
                <div class="img-bottom text-center">
                    <!-- xoá c:forEach neu bi loi -->
                    <c:forEach var="img" items="${images}" varStatus="loop">
                        <c:if test="${loop.index > 0}">
                            <img src="${img}" width="50" height="50"/>
                        </c:if>
                    </c:forEach>        
                </div>
            </div>
            <div class="col-4 product-info">
                <h2>${product.productName}</h2>
                <hr/>   
                <h3>Overview</h3> <br/>
                <hr/> 
                <p>${product.description}</p>
                <p>Price: ${product.price}</p>
                <p>Quantity: ${product.productQuantity}</p>
                <p>Brand: ${product.brand.brandName}</p> 

            </div>

            <div class="col-2">

            </div>

        </div>
        <div id="back-to-home" class="text-center mt-4">
            <a href="home" class="btn btn-warning rounded-pill px-4">Back to Home</a>
        </div>

    </body>
</html>
