<%-- 
    Document   : login
    Created on : Jun 17, 2025, 1:31:46 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-3"></div>
                
                <div class="col-6">
                    <h3 style="text-align: center">Login form</h3>
                    <h5 style="color: red"> ${requestScope.error}</h5>
                    <form action="login" method="post">
                        <div class="form-group">
                            <laber>Username: </laber>
                            <input type="text" name="user" class="form-control">
                        </div>

                        <div class="form-group">
                            <laber>Password: </laber>
                            <input type="text" name="pass" class="form-control">
                        </div>
                        <button style="margin-left: 200px;width: 180px" class="btn btn-primary">Login</button>

                    </form>


                </div>
            </div>

        </div>
    </body>
</html>
