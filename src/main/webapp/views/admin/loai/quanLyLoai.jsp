<%@page import="java.lang.reflect.Array"%>
<%@page import="model.loai"%>
<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Quan ly loai</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/trangChu.css">
</head>
<body>

<jsp:include page="/layouts/headerAdmin.jsp"/>

<div class="container mt-4">
	<div class="row">
     	<div class="col-3">
     		<h2 class="mb-4">📁 Quản lý sách</h2>
     	</div>
     	<div class="col-5">
     		 <a href="themLoaiController" class="btn btn-primary mb-3">➕ Thêm loai</a>
     	</div>
	    <div class="col-4">
	    	<form class="d-flex" action="quanLyLoaiController">
		       <input class="form-control me-2" type="text" placeholder="Search" name="timKiem">
		       <button class="btn btn-primary" type="submit">Search</button>
     		</form>
	    </div>
     </div>
	<% String popup = (String) request.getAttribute("popup");
	   if (popup != null) { %>
		<script>
		    alert("<%= popup %>");
		</script>
	<% } %>
	
    <table class="table table-bordered table-striped shadow">
        <thead class="table-dark">
        <tr>
            <th>Mã loại</th>
            <th>Tên loại</th>
            <th>Chức năng</th>
        </tr>
        </thead>

        <tbody>
<%
    ArrayList<loai> ds = (ArrayList<loai>) request.getAttribute("dsloai");

    if (ds != null && !ds.isEmpty()) {
        for (loai l : ds) {
%>
            <tr>
                <td><%= l.getMaLoai() %></td>
                <td><%= l.getTenLoai() %></td>
                <td>
                    <a href="suaLoaiController?maloai=<%= l.getMaLoai() %>"
                       class="btn btn-warning btn-sm">Sửa</a>

                    <a href="xoaLoaiController?maloai=<%= l.getMaLoai() %>"
                       class="btn btn-danger btn-sm"
                       onclick="return confirm('Xóa loại này?');">
                        Xóa
                    </a>
                </td>
            </tr>
<%
        }
    } else {
%>
        <tr>
            <td colspan="3" class="text-center text-muted">
                Không có loại nào trong hệ thống.
            </td>
        </tr>
<%
    }
%>
</tbody>

    </table>
	<!-- PHÂN TRANG -->
		<div class="pagination-container mt-3 text-center">
			<%
			    int totalRows = (int) request.getAttribute("totalRows");
			    int trangHienTai = (int) request.getAttribute("trangHienTai");
			
			    int pageSize = 6; // CỐ ĐỊNH, không truyền nữa
			    int soTrang = (int) Math.ceil((double) totalRows / pageSize);
			
			    int startPage = Math.max(1, trangHienTai - 2);
			    int endPage = Math.min(soTrang, trangHienTai + 2);
			
			    String queryString = "";
			    if (request.getParameter("maloai") != null)
			        queryString += "&maloai=" + request.getParameter("maloai");
			    if (request.getParameter("timKiem") != null)
			        queryString += "&timKiem=" + request.getParameter("timKiem");
			%>
			
			<!-- Nút Previous -->
			<% if (trangHienTai > 1) { %>
			    <a href="quanLyLoaiController?page=<%=trangHienTai - 1%><%=queryString%>"
			       class="btn btn-sm btn-outline-primary mx-1">&laquo;</a>
			<% } %>
			
			<!-- Trang đầu -->
			<% if (startPage > 1) { %>
			    <a href="quanLyLoaiController?page=1<%=queryString%>"
			       class="btn btn-sm btn-outline-primary mx-1">1</a>
			    <% if (startPage > 2) { %> ... <% } %>
			<% } %>
			
			<!-- Các trang giữa -->
			<% for (int i = startPage; i <= endPage; i++) { %>
			    <a href="quanLyLoaiController?page=<%=i%><%=queryString%>"
			       class="btn btn-sm <%= (i == trangHienTai) ? "btn-primary" : "btn-outline-primary" %> mx-1">
			        <%=i%>
			    </a>
			<% } %>
			
			<!-- Trang cuối -->
			<% if (endPage < soTrang) { %>
			    <% if (endPage < soTrang - 1) { %> ... <% } %>
			    <a href="quanLyLoaiController?page=<%=soTrang%><%=queryString%>"
			       class="btn btn-sm btn-outline-primary mx-1"><%=soTrang%></a>
			<% } %>
			
			<!-- Nút Next -->
			<% if (trangHienTai < soTrang) { %>
			    <a href="quanLyLoaiController?page=<%=trangHienTai + 1%><%=queryString%>"
			       class="btn btn-sm btn-outline-primary mx-1">&raquo;</a>
			<% } %>
		</div>
	
</div>


</body>
</html>