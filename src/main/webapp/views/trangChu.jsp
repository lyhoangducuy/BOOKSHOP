<%@page import="model.sach"%>
<%@page import="model.loai"%>
<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Trang chủ</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/trangChu.css">
</head>
<body>

<jsp:include page="../layouts/header.jsp"/>

<div class="container-fluid mt-3">
    <div class="row">
        <!-- MENU -->
        <div class="col-md-2 col-12 mb-3">
            <div class="sidebar-menu">
                <% ArrayList<loai> ds = (ArrayList<loai>) request.getAttribute("dsloai");
                   if(ds != null) {
                       for(loai l : ds) { %>
                <a href="trangChuController?maloai=<%=l.getMaLoai()%>" 
                   class="<%= (request.getParameter("maloai") != null && request.getParameter("maloai").equals(l.getMaLoai())) ? "active" : "" %>">
                   <%= l.getTenLoai() %>
                </a>
                <%   } 
                   } %>
            </div>
        </div>

        <!-- DANH SÁCH SÁCH -->
        <div class="col-md-10 col-12">
            <div class="row g-3">
                <% ArrayList<sach> dssach = (ArrayList<sach>) request.getAttribute("dssach");
                   if(dssach != null) {
                       for(sach s : dssach) { %>
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="book-card">
                        <img src="<%= s.getAnh() %>" alt="<%= s.getTenSach() %>" class="book-img mb-2">
                        <div class="book-title"><%= s.getTenSach() %></div>
                        <div class="book-info">Số tập: <%= s.getSoTap() %></div>
                        <div class="book-info">Số lượng: <%= s.getSoLuong() %></div>
                        <div class="book-price"><%= s.getGia() %> VND</div>
                        <a href="gioHangController?maSachMua=<%= s.getMaSach() %>&tenSachMua=<%= s.getTenSach() %>&soLuongMua=1&giaSachMua=<%= s.getGia()%>&anhSachMua=<%= s.getAnh()%>" class="d-block mt-2">
                            <img src="${pageContext.request.contextPath}/buynow.jpg" alt="Mua ngay" class="book-buy">
                        </a>
                    </div>
                </div>
                <%   } 
                   } %>
            </div>

            <!-- PHÂN TRANG -->
            <div class="pagination-container mt-3 text-center">
                <%
                    int totalRows = (int) request.getAttribute("totalRows");
                    int trangHienTai = (int) request.getAttribute("trangHienTai");
                    int pageSize = (int) request.getAttribute("pageSize");
                    int soTrang = (int)Math.ceil((double)totalRows / pageSize);

                    int startPage = Math.max(1, trangHienTai - 2);
                    int endPage = Math.min(soTrang, trangHienTai + 2);

                    String queryString = "";
                    if(request.getParameter("maloai") != null)
                        queryString += "&maloai=" + request.getParameter("maloai");
                    if(request.getParameter("timKiem") != null)
                        queryString += "&timKiem=" + request.getParameter("timKiem");
                %>

                <% if(trangHienTai > 1) { %>
                    <a href="trangChuController?page=<%=trangHienTai-1%><%=queryString%>" class="btn btn-sm btn-outline-primary mx-1">&laquo;</a>
                <% } %>

                <% if(startPage > 1) { %>
                    <a href="trangChuController?page=1<%=queryString%>" class="btn btn-sm btn-outline-primary mx-1">1</a>
                    <% if(startPage > 2) { %> ... <% } %>
                <% } %>

                <% for(int i = startPage; i <= endPage; i++) { %>
                    <a href="trangChuController?page=<%=i%><%=queryString%>" 
                       class="btn btn-sm <%= (i == trangHienTai) ? "btn-primary" : "btn-outline-primary" %> mx-1">
                        <%=i%>
                    </a>
                <% } %>

                <% if(endPage < soTrang) { %>
                    <% if(endPage < soTrang - 1) { %> ... <% } %>
                    <a href="trangChuController?page=<%=soTrang%><%=queryString%>" class="btn btn-sm btn-outline-primary mx-1"><%=soTrang%></a>
                <% } %>

                <% if(trangHienTai < soTrang) { %>
                    <a href="trangChuController?page=<%=trangHienTai+1%><%=queryString%>" class="btn btn-sm btn-outline-primary mx-1">&raquo;</a>
                <% } %>
            </div>
        </div>
    </div>
</div>
</body>
</html>
