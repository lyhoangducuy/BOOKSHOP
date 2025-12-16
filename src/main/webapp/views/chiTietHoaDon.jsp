
<%@page import="model.phuongThucThanhToan"%>
<%@page import="model.hoaDon"%>
<%@page import="model.sach"%>
<%@page import="bo.sachBO"%>
<%@page import="model.khachHang"%>
<%@page import="bo.hoaDonBO"%>
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
	<%chiTietHoaDonBO cBO = new chiTietHoaDonBO();
    Long mhd = (Long) request.getAttribute("mahd");

    System.out.println(mhd);
    hoaDonBO hBO = new hoaDonBO();
    hoaDon d=hBO.timHoaDonTheoMa(mhd);
    ArrayList<chiTietHoaDon> ds = new ArrayList<>();
    khachHang kh = (khachHang) session.getAttribute("khachHang"); 

    if (mhd != null) {
        ds = cBO.timChiTietHoaDonTheoMaHoaDon(mhd);

        
    } else {
        out.println("<div class='alert alert-warning'>Không tìm thấy mã hóa đơn!</div>");
    } %>
    <% if(d != null && d.isDaMua() == false) { %>

<h5>Nhập thông tin giao hàng</h5>
<form action="capNhatDiaChiController" method="post" class="row g-3">
    <input type="hidden" name="maHoaDon" value="<%=d.getMaHoaDon() %>" />

    <div class="col-md-6">
        <label for="hoTen" class="form-label">Họ tên</label>
        <input type="text" class="form-control" id="hoTen" name="hoTen" value="<%=d.getTenNguoiNhan().isEmpty()?"": d.getTenNguoiNhan()%>" required />
    </div>

    <div class="col-md-6">
        <label for="sdt" class="form-label">Số điện thoại</label>
        <input type="text" class="form-control" id="sdt" name="sdt" value="<%=d.getSoDienThoai().isEmpty()?"": d.getSoDienThoai()%>" required />
    </div>

    <div class="col-12">
        <label for="diaChi" class="form-label">Địa chỉ</label>
        <input type="text" class="form-control" id="diaChi" name="diaChi" value="<%=d.getDiaChiGiaoHang().isEmpty()?"": d.getDiaChiGiaoHang() %>" required />
    </div>

    <div class="col-12">
        <button type="submit" class="btn btn-primary">Cập nhật</button>
    </div>
</form>


<% } %>
<hr class="my-4">
<h5>Chi tiet don hang</h5>
    <!-- Header bảng -->
    <div class="row fw-bold border-bottom pb-2 mb-3 text-center">
        <div class="col-1">Mã</div>
        <div class="col-2">Ảnh</div>
        <div class="col-1 text-start">Tên sách</div>
        <div class="col-2">Số lượng</div>
        <div class="col-1 text-center">Giá</div>
        <div class="col-3">Thành tiền</div>
         <% if (d.isDaMua()==false){%>
        <div class="col-1">Thao tác</div>
        <%} %>
    </div>

    <!-- Dữ liệu -->
    <%
    

    if (ds != null && !ds.isEmpty()) {
    	sachBO sBO=new sachBO();
    	long t=hBO.getThanhTien(mhd)+30000; 
        for (chiTietHoaDon ls : ds) {
        sach s=sBO.getSachTheoMa(ls.getMaSach());
		%>
		 <div class="row align-items-center border-bottom py-2 text-center">

        <div class="col-1"><%= ls.getMaSach() %></div>

        <div class="col-2">
            <img src="<%= s.getAnh() %>" alt="<%= s.getTenSach() %>"
                 class="img-fluid rounded" style="max-height:80px;">
        </div>

        <div class="col-1 text-start"><%= s.getTenSach() %></div>
		 <% if (d.isDaMua()==false){%>
        <div class="col-2">
            <form action="suaChiTietController" method="post"
                  class="d-flex justify-content-center gap-1">
                <input type="number" name="soluong"
                       value="<%= ls.getSoLuongMua() %>"
                       min="1" class="form-control form-control-sm"
                       style="width:70px">
                <input type="hidden" name="maSach" value="<%= ls.getMaSach() %>">
                <input type="hidden" name="mahd" value="<%= ls.getMaHoaDon() %>">
                <button class="btn btn-sm btn-outline-primary">Sửa</button>
            </form>
        </div>
		<%}else  {%>
		<div class="col-2"><%=ls.getSoLuongMua() %> </div>
		<%} %>
        <div class="col-1 text-center"><%= s.getGia() %></div>

        <div class="col-3 text-center fw-bold">
            <%= s.getGia() * ls.getSoLuongMua() %>
        </div>
		<% if (d.isDaMua()==false){%>
        <div class="col-1">
            <a href="xoaChiTietController?maSach=<%=ls.getMaSach()%>&mahd=<%=ls.getMaHoaDon()%>"
               class="btn btn-sm btn-outline-danger">Xóa</a>
        </div>
		<%} %>
    </div>
            
		<%
        }
        
        // Lấy khách hàng từ session
        
        hoaDon h=hBO.timHoaDonTheoMa(mhd);
        if (h.isDaMua()==false){
    %>
    
    <div class="row align-items-center mt-3">
    <!-- Bên trái -->
    <div class="col-md-6 text-start">
        <a class="btn btn-outline-danger btn-sm"
           href="xoaHoaDonController?mahdx=<%=mhd%>">
            Xóa tất cả
        </a>
    </div>

    <!-- Bên phải -->
    <div class="col-md-6 text-end">
    <span>Phí ship</span>
	            <span>30.000 VNĐ</span>
        <h5 class="mb-2">
        	
            Tổng tiền:
            <span class="text-danger fw-bold fs-5">
                <%= String.format("%,d", t) %> VNĐ
            </span>
        </h5>
		<%if (kh.getMakh()== h.getMakh()) {%>
        <form action="ketQuaThanhToan" method="post" class="d-inline">
		    <div class="col-12">
		        <label for="pttt" class="form-label">Phương thức thanh toán</label>
		        <select class="form-select" id="pttt" name="pttt" required>
		            <option value="" selected disabled>-- Chọn phương thức thanh toán --</option>
		            <option value="TIEN_MAT">Thanh toán khi nhận hàng (COD)</option>
		            <option value="NGAN_HANG">Thanh toán ngân hàng</option>
		        </select>
		    </div>

		    <input type="hidden" name="totalBill" value="<%=t%>">
		    <input type="hidden" name="idUser" value="<%=kh.getMakh()%>">
		    
		    <input type="hidden" name="idOrder" value="<%=mhd%>">
		    <button id="btnThanhToan" type="submit" class="btn btn-success px-4">Thanh toán</button>
		</form>
        <%} else {%>
        	<div><%= (h.getPtt().equals(phuongThucThanhToan.TIEN_MAT))? "Tien mat":"Ngan hang"%></div>
        <%} %>
    </div>
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
<script>
    const hoTen  = document.getElementById("hoTen");
    const sdt    = document.getElementById("sdt");
    const diaChi = document.getElementById("diaChi");
    const pttt   = document.getElementById("pttt");
    const btn    = document.getElementById("btnThanhToan");

    function checkForm() {
        if (
            hoTen.value.trim() !== "" &&
            sdt.value.trim() !== "" &&
            diaChi.value.trim() !== "" &&
            pttt.value !== ""
        ) {
            btn.disabled = false;
        } else {
            btn.disabled = true;
        }
    }

    // Gắn sự kiện
    hoTen?.addEventListener("input", checkForm);
    sdt?.addEventListener("input", checkForm);
    diaChi?.addEventListener("input", checkForm);
    pttt?.addEventListener("change", checkForm);

    // Check lần đầu khi load trang
    checkForm();
</script>

</body>
</html>