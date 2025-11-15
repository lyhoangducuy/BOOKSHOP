<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Đăng nhập</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dangNhap.css">
</head>
<body>

    <!-- THANH MENU -->
    <jsp:include page="../../layouts/header.jsp"/>

    <!-- FORM ĐĂNG NHẬP -->
    <div class="center-box">
        <form class="login-form" action="dangKyController" method="post">

            <h4 class="text-center mb-4">Đăng ký</h4>

            <div class="mb-3">
                <label class="form-label">Tên đăng nhập*</label>
                <input type="text" class="form-control" name="txtTendn" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Mật khẩu*</label>
                <input type="password" class="form-control" name="txtMatkhau" required>
            </div>
            <c:if test="${not empty msg2}">
                <span class="text-danger">${msg2}</span>
            </c:if>
            <div class="mb-3">
                <label class="form-label">Nhập lại mật khẩu*</label>
                <input type="password" class="form-control" name="txtNhaplai" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Họ và tên</label>
                <input type="text" class="form-control" name="txtHovaten" >
            </div>
            <div class="mb-3">
                <label class="form-label">Địa chỉ</label>
                <input type="text" class="form-control" name="txtDiachi" >
            </div>
            <div class="mb-3">
                <label class="form-label">Số điện thoại</label>
                <input type="text" class="form-control" name="txtSdt" >
            </div>
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="text" class="form-control" name="txtEmail">
            </div>
			 <c:if test="${not empty msg3}">
                <span class="text-danger">${msg3}</span>
            </c:if>
            <button type="submit" class="btn btn-primary w-100">Đăng ký</button>

            <!-- Đăng ký tài khoản -->
            <div class="text-center mt-3">
                <a href="dangNhapController" class="text-primary fw-bold">
                    Quay lại đăng nhập
                </a>
            </div>

        </form>
    </div>

</body>
</html>
