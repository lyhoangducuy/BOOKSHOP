<%@page import="model.sach"%>
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

    <h2 class="mb-4">✏️ Sửa sách</h2>

    <form action="suaSachController" class="shadow p-4 bg-white rounded">
        <% sach l = (sach) request.getAttribute("sach"); 
        if (l!=null ){
        %>

        <div class="row">
            <!-- Cột trái -->
            <div class="col-12 col-md-5 mb-3">
                <label class="form-label">Mã sách</label>
                <input type="text" name="maSach" class="form-control"
                       value="<%= l.getMaSach() %>" readonly>
            </div>

            <div class="col-12 col-md-5 mb-3">
                <label class="form-label">Tên sách</label>
                <input type="text" name="tenSach" class="form-control"
                       value="<%= l.getTenSach() %>" required>
            </div>

            <div class="col-12 col-md-5 mb-3">
                <label class="form-label">Giá</label>
                <input type="text" name="gia" class="form-control"
                       value="<%= l.getGia() %>" required>
            </div>

            <div class="col-12 col-md-5 mb-3">
                <label class="form-label">Số lượng</label>
                <input type="text" name="soLuong" class="form-control"
                       value="<%= l.getSoLuong() %>" required>
            </div>

            <div class="col-12 col-md-5 mb-3">
                <label class="form-label">Loại sách</label>
                <input type="text" name="loaiSach" class="form-control"
                       value="<%= l.getMaLoai()%>" required>
            </div>

            <div class="col-12 col-md-5 mb-3">
                <label class="form-label">Tác giả</label>
                <input type="text" name="tacGia" class="form-control"
                       value="<%= l.getTacGia() %>" required>
            </div>

            <div class="col-12 col-md-5 mb-3">
                <label class="form-label">Số tập</label>
                <input type="text" name="soTap" class="form-control"
                       value="<%= l.getSoTap() %>" required>
            </div>
        </div>

        <% }
            String mess = (String) request.getAttribute("mess");
            if (mess != null && !mess.isEmpty()) { 
        %>
            <div class="alert alert-success mt-3"><%= mess %></div>
        <% } %>

        <div class="mt-3 d-flex gap-2">
            <button type="submit" class="btn btn-success">💾 Lưu thay đổi</button>

            <a href="quanLySachController" class="btn btn-secondary">
                ⬅ Quay lại
            </a>
        </div>

    </form>

</div>

</body>
</html>
