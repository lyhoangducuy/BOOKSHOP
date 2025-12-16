<%@page import="model.khachHang"%>
<%@page import="bo.gioHangSachBO"%>
<%@page import="model.gioHangSach"%>
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
	    ArrayList<gioHangSach> ds = (ArrayList<gioHangSach>) session.getAttribute("gioHang");
	    gioHangSachBO ghsBo = new gioHangSachBO();
	    long magh = -1;
	    if(ds == null || ds.isEmpty()) {
	%>
	    <div class="alert alert-info text-center">Giỏ hàng rỗng</div>
	    <div class="text-center">
	        <a href="trangChuController" class="btn btn-primary">Quay về trang chủ</a>
	    </div>
	<% } else { %>
	<% for(gioHangSach g : ds) { %>
	<div class="row mb-3 align-items-center border-bottom py-2">
	    <div class="col-1 text-center">
	        <input type="checkbox" name="cxoa" value="<%= g.getMaSach() %>">
	    </div>
	
	    <div class="col-2 text-center">
	        <img src="<%= g.getMaSach()	 %>" width="50" class="img-thumbnail">
	    </div>
	
	    <div class="col-3">
	        <strong><%= g.getDonGia() %> VNĐ</strong>
	    </div>
	
	
	    <div class="col-3 text-center">
	    	<form action="suaGioHangController" method="post">
	    		
			    <input type="number" name="soluong" value="<%= g.getSoLuong() %>" min="1"
			           style="width:60px; display:inline-block; margin-right:5px;">
			    <input type="hidden" name="maSach" value="<%= g.getMaSach() %>">
			    <input type="hidden" name="magh" value="<%= g.getMaGioHang() %>">
			    <button type="submit" class="btn btn-sm btn-primary">Sửa</button>
			</form>

	    </div>
	
	    <div class="col-2 text-center">
	        <a href="xoaChonController?maSach=<%=g.getMaSach()%>&magh=<%=g.getMaGioHang()%>" 
	           class="btn btn-sm btn-danger">Xóa</a>
	    </div>
	</div>
	<% magh = g.getMaGioHang(); } %>
	
	<div class="d-flex justify-content-between align-items-center mt-4">
	    <button type="submit" name="xoaCheckBox" value="ok" class="btn btn-warning">
	        Xóa chọn
	    </button>
	    <% if (magh != -1) { %>
	    <%
    long tongTien = 0;
    for (gioHangSach g : ds) {
        tongTien += g.getDonGia() * g.getSoLuong();
    }
%>

<div class="text-end">
    <strong>Thành tiền: <%= tongTien %> VNĐ</strong>
    <a href="thanhToanController?thanhToan=true" class="btn btn-primary ms-3">
        Thanh toán
    </a>
</div>

	    <% } %>
	</div>
	
	<% } %>
	</div>


</body>
</html>
