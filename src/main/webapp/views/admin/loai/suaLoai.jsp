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

    <h2 class="mb-4">✏️ Sửa loại sách</h2>

    <form action="suaLoaiController" class="shadow p-4 bg-white rounded">
        <% loai l = (loai) request.getAttribute("loai"); %>

        <div class="row">
            <!-- Cột trái -->
            <div class="col-12 col-md-5 mb-3">
                <label class="form-label">Mã loại</label>
                <input type="text" name="maloai" class="form-control"
                       value="<%= l.getMaLoai() %>" readonly>
            </div>

            <!-- Cột phải -->
            <div class="col-12 col-md-5 mb-3">
                <label class="form-label">Tên loại</label>
                <input type="text" name="tenloai" class="form-control"
                       value="<%= l.getTenLoai() %>" required>
            </div>
        </div>

        <% 
            String mess = (String) request.getAttribute("mess");
            if (mess != null && !mess.isEmpty()) { 
        %>
            <div class="alert alert-success mt-3"><%= mess %></div>
        <% } %>

        <!-- Buttons -->
        <div class="mt-3 d-flex gap-2">
            <button type="submit" class="btn btn-success">💾 Lưu thay đổi</button>
            <a href="quanLyLoaiController" class="btn btn-secondary">⬅ Quay lại</a>
        </div>

    </form>

</div>


</body>
</html>
