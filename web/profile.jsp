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
        <div class="container">
            <jsp:include page="header.jsp"></jsp:include>
                <h2>User Detail</h2>
                <hr/>
                <p style="color: blue">                
                Username: ${sessionScope.account.username}<br/>
                Fullname: ${sessionScope.user.fullName}<br/>
                Email: ${sessionScope.user.email}<br/>
                PhoneNumber:${sessionScope.user.phoneNumber}<br/>
                Gender:${sessionScope.user.gender}<br/>
                Address:${sessionScope.user.address}<br/>
                
            </p>
        </div>
       
    </body>
</html>