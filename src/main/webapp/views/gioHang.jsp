<%@page import="bo.gioHangBO"%>
<%@page import="model.gioHang"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.sach"%>
<%@page import="bo.sachBO"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Giỏ hàng</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<jsp:include page="../layouts/header.jsp"/>

<div class="container mt-4">
<%
    gioHangBO ds = (gioHangBO) session.getAttribute("gioHang");
    if(ds == null || ds.getGioHang().isEmpty()) {
%>
    <div class="alert alert-info text-center">Giỏ hàng rỗng</div>
    <div class="text-center">
        <a href="trangChuController" class="btn btn-primary">Quay về trang chủ</a>
    </div>
<% } else { %>

<form action="suaXoaGioHangController" method="post">
    <% for(gioHang g : ds.getGioHang()) { %>
    <div class="row mb-3 align-items-center border-bottom py-2">
        <div class="col-sm-1 text-center">
            <input type="checkbox" name="cxoa" value="<%=g.getMaSach()%>">
        </div>
        <div class="col-sm-2 text-center">
            <img src="<%=g.getAnh()%>" width="50" class="img-thumbnail">
        </div>
        <div class="col-sm-3">
            <strong><%=g.getTenSach()%></strong>
        </div>
        <div class="col-sm-1 text-center"><%=g.getGia()%></div>
        <div class="col-sm-1 text-center"><%=g.getSoluong()%></div>
        <div class="col-sm-3 text-center">
            <input type="number" name="<%=g.getMaSach()%>" value="<%=g.getSoluong()%>" style="width:60px" min="1">
            <button type="submit" name="butSua" value="<%=g.getMaSach()%>" class="btn btn-sm btn-success">+</button>
        </div>
        <div class="col-sm-1 text-center">
            <a href="suaXoaGioHangController?maSachXoaChon=<%=g.getMaSach()%>" class="btn btn-sm btn-danger">Xóa</a>
        </div>
    </div>
    <% } %>

    <div class="d-flex align-items-center gap-3 mt-4">
        <button type="submit" name="xoaCheckBox" value="ok" class="btn btn-warning">Xóa chọn</button>
        <a href="thanhToanController?thanhToan=true" class="btn btn-primary">Thanh toán</a>
        <strong>Thành tiền: <%= ds.tongThanhTien() %> VNĐ</strong>
    </div>
</form>

<% } %>
</div>

</body>
</html>
