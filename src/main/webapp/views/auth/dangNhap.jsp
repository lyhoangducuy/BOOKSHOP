<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Đăng nhập</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dangNhap.css">

</head>
<body>

    <jsp:include page="../../layouts/header.jsp"/>

    <div class="center-box">
        <form action="dangNhapController" method="post" class="login-form">

            <h4 class="text-center mb-4">Đăng nhập</h4>

            <div class="mb-3">
                <label class="form-label">Tên đăng nhập*</label>
                <input type="text" class="form-control" name="txtUser" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Mật khẩu*</label>
                <input type="password" class="form-control" name="txtPass" required>
            </div>

            <c:if test="${not empty msg}">
                <span class="text-danger">${msg}</span>
            </c:if>

            <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>

            <div class="text-center mt-3">
                Chưa có tài khoản?
                <a href="dangKyController" class="text-primary fw-bold">Đăng ký ngay</a>
            </div>

        </form>
    </div>

</body>
</html>
