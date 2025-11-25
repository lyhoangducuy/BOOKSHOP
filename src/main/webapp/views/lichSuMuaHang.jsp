<%@page import="model.lichSuMuaHang"%>
<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Lịch sử mua hàng</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lichSuMuaHang.css">
</head>
<body>

<jsp:include page="../layouts/header.jsp"/>

<div class="container mt-4">
    <!-- Header bảng -->
    <div class="row bg-light fw-bold border-bottom py-2">
        <div class="col-sm-3 col-6">Tên sách</div>
        <div class="col-sm-2 col-3">Giá</div>
        <div class="col-sm-2 col-3">Số lượng</div>
        <div class="col-sm-2 col-3">Thành tiền</div>
        <div class="col-sm-3 col-6">Trạng thái</div>
    </div>

    <!-- Dữ liệu -->
    <%
        ArrayList<lichSuMuaHang> dsls = (ArrayList<lichSuMuaHang>) request.getAttribute("dsls");
        if(dsls != null && !dsls.isEmpty()) {
            for(lichSuMuaHang ls : dsls) {
    %>
    <div class="row py-2 border-bottom align-items-center">
        <div class="col-sm-3 col-6"><%= ls.getTensach() %></div>
        <div class="col-sm-2 col-3"><%= ls.getGia() %> VND</div>
        <div class="col-sm-2 col-3"><%= ls.getSoLuongMua() %></div>
        <div class="col-sm-2 col-3"><%= ls.getTT() %> VND</div>
        <div class="col-sm-3 col-6"><%= (ls.getDamua() ? "Đã trả tiền" : "Đang đặt mua") %></div>
    </div>
    <%
            }
        } else {
    %>
    <div class="row py-3">
        <div class="col text-center text-muted">Không có đơn hàng nào</div>
    </div>
    <%
        }
    %>

    <!-- PHÂN TRANG -->
    <%
        int totalRows = (int) request.getAttribute("totalRows");
        int pageSize = 10; // 10 đơn hàng/trang
        int soTrang = (int) Math.ceil((double) totalRows / pageSize);
        int trangHienTai = 1;
        String trangParam = request.getParameter("page");
        if(trangParam != null && !trangParam.isEmpty()) {
            try {
                trangHienTai = Integer.parseInt(trangParam);
                if(trangHienTai < 1) trangHienTai = 1;
                if(trangHienTai > soTrang) trangHienTai = soTrang;
            } catch(Exception e){ trangHienTai=1; }
        }
        int startPage = Math.max(1, trangHienTai - 2);
        int endPage = Math.min(soTrang, trangHienTai + 2);
    %>

    <div class="pagination-container mt-3 text-center">
        <% if(trangHienTai > 1) { %>
            <a href="lichSuMuaHangController?page=<%=trangHienTai-1%>" class="btn btn-sm btn-outline-primary mx-1">&laquo;</a>
        <% } %>

        <% if(startPage > 1) { %>
            <a href="lichSuMuaHangController?page=1" class="btn btn-sm btn-outline-primary mx-1">1</a>
            <% if(startPage > 2) { %> ... <% } %>
        <% } %>

        <% for(int i=startPage; i<=endPage; i++) { %>
            <a href="lichSuMuaHangController?page=<%=i%>" 
               class="btn btn-sm <%= (i==trangHienTai) ? "btn-primary" : "btn-outline-primary" %> mx-1">
               <%=i%>
            </a>
        <% } %>

        <% if(endPage < soTrang) { %>
            <% if(endPage < soTrang - 1) { %> ... <% } %>
            <a href="lichSuMuaHangController?page=<%=soTrang%>" class="btn btn-sm btn-outline-primary mx-1"><%=soTrang%></a>
        <% } %>

        <% if(trangHienTai < soTrang) { %>
            <a href="lichSuMuaHangController?page=<%=trangHienTai+1%>" class="btn btn-sm btn-outline-primary mx-1">&raquo;</a>
        <% } %>
    </div>

</div>

</body>
</html>
