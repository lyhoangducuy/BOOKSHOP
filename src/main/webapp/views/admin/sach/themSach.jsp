<%@page import="java.util.ArrayList"%>
<%@page import="model.loai"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sửa loại</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<jsp:include page="/layouts/headerAdmin.jsp"/>

<div class="container mt-4">

    <h2 class="mb-4">✏️ Them sach moi</h2>
	
    <form action="themSachController" method="post" enctype="multipart/form-data"
      class="shadow p-4 bg-white rounded">

    <h3 class="mb-3">Thêm sách mới</h3>

    <% 
        String mess = (String) request.getAttribute("mess");
        if (mess != null && !mess.isEmpty()) {
    %>
        <div class="alert alert-info"><%=mess %></div>
    <% } %>

    <div class="row">

        <!-- Cột trái -->
        <div class="col-5">

            <!-- Mã sách -->
            <div class="mb-3">
                <label class="form-label">Mã sách</label>
                <input type="text" name="maSach" class="form-control" required>
            </div>

            <!-- Tên sách -->
            <div class="mb-3">
                <label class="form-label">Tên sách</label>
                <input type="text" name="tenSach" class="form-control" required>
            </div>

            <!-- Số lượng -->
            <div class="mb-3">
                <label class="form-label">Số lượng</label>
                <input type="number" name="soLuong" class="form-control" required>
            </div>

            <!-- Giá -->
            <div class="mb-3">
                <label class="form-label">Giá</label>
                <input type="number" name="gia" class="form-control" required>
            </div>

        </div>

        <!-- Cột phải -->
        <div class="col-5">

            <!-- Loại sách -->
            <div class="mb-3">
                <label class="form-label">Loại sách</label>
                <select name="maLoai" class="form-select" required>
                    <option value="">-- Chọn loại --</option>
                    <%
                    ArrayList<loai> ds=(ArrayList<loai>)session.getAttribute("dsLoai");
                    for (loai l : ds)  {%>
                        <option value="<%=l.getMaLoai()%>"><%=l.getTenLoai() %></option>
                    <%} %>
                </select>
            </div>

            <!-- Số tập -->
            <div class="mb-3">
                <label class="form-label">Số tập</label>
                <input type="number" name="soTap" class="form-control" required>
            </div>

            <!-- Ảnh -->
            <div class="mb-3">
                <label class="form-label">Ảnh</label>
                <input type="file" name="anh" class="form-control" accept="image/*">
            </div>

            <!-- Ngày nhập -->
            <div class="mb-3">
                <label class="form-label">Ngày nhập</label>
                <input type="date" name="ngayNhap" class="form-control" required>
            </div>

            <!-- Tác giả -->
            <div class="mb-3">
                <label class="form-label">Tác giả</label>
                <input type="text" name="tacGia" class="form-control" required>
            </div>

        </div>

    </div>

    <!-- Nút -->
    <div class="d-flex gap-2 mt-3">
        <button type="submit" class="btn btn-success">Thêm</button>
        <a href="quanLySachController" class="btn btn-secondary">⬅ Quay lại</a>
    </div>

</form>


</div>

</body>
</html>
