<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Đăng ký</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
    </head>
    <body>
        <div class="container mt-5">
            <h2>Đăng ký tài khoản</h2>
            <form action="register" method="post">
                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="email" class="form-control" required />
                </div>
                <div class="form-group">
                    <label>Tên đăng nhập:</label>
                    <input type="text" name="username" class="form-control" required />
                </div>
                <div class="form-group">
                    <label>Mật khẩu:</label>
                    <input type="password" name="password" class="form-control" required />
                </div>
                <div class="form-group">
                    <label>Họ tên:</label>
                    <input type="text" name="fullname" class="form-control" required />
                </div>
                <div class="form-group">
                    <label>Giới tính:</label><br/>
                    <input type="radio" name="gender" value="Male" checked /> Nam
                    <input type="radio" name="gender" value="Female" /> Nữ
                </div>
                <div class="form-group">
                    <label>Số điện thoại:</label>
                    <input type="text" name="phone" class="form-control" required />
                </div>
                <div class="form-group">
                    <label>Địa chỉ:</label>
                    <input type="text" name="address" class="form-control" />
                </div>
                <button type="submit" class="btn btn-primary">Đăng ký</button>
            </form>

            <c:if test="${not empty error}">
                <div class="alert alert-danger mt-3">${error}</div>
            </c:if>
        </div>
    </body>
</html>