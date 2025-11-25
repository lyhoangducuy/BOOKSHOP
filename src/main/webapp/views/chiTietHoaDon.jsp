
<%@page import="model.chiTietHoaDon"%>
<%@page import="bo.chiTietHoaDonBO"%>
<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Lịch sử mua hàng</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/trangChu.css">
</head>
<body>

<!-- THANH MENU -->
<jsp:include page="../layouts/header.jsp"/>

<div class="container mt-4">
    <!-- Header bảng -->
    <div class="row fw-bold border-bottom pb-2 mb-2">
        <div class="col-sm-2">Mã sách</div>
        <div class="col-sm-2">Số lượng mua</div>
        <div class="col-sm-2">Trạng thái</div>
    </div>

    <!-- Dữ liệu -->
    <%
    chiTietHoaDonBO cBO = new chiTietHoaDonBO();
    Long mhd = (Long) request.getAttribute("mahd");

    System.out.println(mhd);
    
    ArrayList<chiTietHoaDon> ds = new ArrayList<>();

    if (mhd != null) {
        ds = cBO.timChiTietHoaDonTheoMaHoaDon(mhd);
    } else {
        out.println("<div class='alert alert-warning'>Không tìm thấy mã hóa đơn!</div>");
    }

    if (ds != null && !ds.isEmpty()) {
        for (chiTietHoaDon ls : ds) {
		%>
		<div class="row mb-2 border-bottom pb-1">
		    <div class="col-sm-2"><%= ls.getMaSach() %></div>
		    <div class="col-sm-2"><%= ls.getSoLuongMua() %></div>
		    <div class="col-sm-2"><%= (ls.isDaMua() ? "Đã trả tiền" : "Đang đặt mua") %></div>
		</div>
		<%
        }
    } else if(mhd != null) {
%>
<div class="row">
    <div class="col">Hóa đơn trống!</div>
</div>
<%
    }
%>
    
</div>
</body>
</html>