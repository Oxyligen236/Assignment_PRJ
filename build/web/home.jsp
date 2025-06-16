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


            <div class="container">
                <div class="row"> 
                    <!--Type list -->
                    <div class="col-3">
                        <div class="list-group">                            
                        <c:forEach items="${requestScope.typeList}" var="tyl">
                            <a href="product?typeId=${tyl.typeID}" class="list-group-item list-group-item-action">${tyl.typeName}</a>
                        </c:forEach>
                    </div>
                </div>
                <!--Toan bo product-->
                <div class="col-9">
                    <h3>List of products (${requestScope.numP})</h3>
                    <div class="row">
                        <c:forEach items="${requestScope.productList}" var="pr" varStatus="status">
                            <div class="card col-md-3 m-3 p-0" style="width: 18rem;">

                                <img style="width: " src="${imageLists[status.index][0]}" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title">${pr.productName}</h5>
                                    <p class="card-text">

                                        <fmt:formatNumber value="${pr.price}" type="number" groupingUsed="true"/>
                                    </p>
                                    <a class="btn btn-primary" href="detail?pid=${pr.productId}">Detail</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>


            </div>
        </div>





    </body>
</html>
