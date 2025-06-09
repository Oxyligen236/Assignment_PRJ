<%-- 
    Document   : home
    Created on : Jun 7, 2025, 10:59:44 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
    </head>
    <body>
        <!--Header-->
        <jsp:include page="header.jsp"></jsp:include>

            <!--Type list -->
            <div>
            <c:forEach items="${typeList}" var="tyl">
                <div class="elementor-element elementor-element-10caac2 e-con-full e-flex e-con e-child" data-id="10caac2" data-element_type="container">
                    <h5 class="card-title"><a href="product?typeId=${tyl.typeID}">${tyl.typeName}</a> </h5>
                </div>
            </c:forEach>
            </div>  
            <!--Toan bo product-->
            <div>
                <h3>List of products (${requestScope.numP})</h3>
            <div class="row">
                <c:forEach items="${productList}" var="pr" varStatus="status">
                    <div class="card col-md-3 m-3 p-0" style="width: 18rem;">

                        <img src="${imageLists[status.index][0]}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${pr.productName}</h5>
                            <p class="card-text">

                                <fmt:formatNumber value="${pr.price}" type="number" groupingUsed="true"/>
                            </p>
                            <a href="#" class="btn btn-primary">Add To Cart</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>



    </body>
</html>
