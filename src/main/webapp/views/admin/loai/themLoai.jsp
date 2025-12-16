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

    <h2 class="mb-4">✏️ Them loai moi</h2>
	
    <form action="themLoaiController" class="shadow p-4 bg-white rounded">
        <!-- Mã loại (readonly) -->
        <div class="mb-3">
            <label class="form-label">Mã loại</label>
            <input type="text" name="maloai" class="form-control"
                    required>
        </div>

        <!-- Tên loại -->
        <div class="mb-3">
            <label class="form-label">Tên loại</label>
            <input type="text" name="tenloai" class="form-control"
                   required>
        </div>
		<%String mess=(String)request.getAttribute("mess");
			if (mess!=null && !mess.isEmpty())
			%>
			<h3 class="mb-4"><%=mess %></h3>
			
        <!-- Nút -->
        <div class="d-flex gap-2">
            <button type="submit" class="btn btn-success">Them</button>
            <a href="quanLyLoaiController"
               class="btn btn-secondary">⬅ Quay lại</a>
        </div>

    </form>

</div>

</body>
</html>
