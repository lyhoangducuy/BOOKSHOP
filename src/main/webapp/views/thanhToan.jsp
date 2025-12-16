
<%@page import="model.hoaDon"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.khachHang"%>
<%@page import="bo.hoaDonBO"%>
<%@ page import="model.phuongThucThanhToan" %>

<%@ page import="model.trangThaiHoaDon" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<!-- THANH MENU -->
<jsp:include page="../layouts/header.jsp"/>

<!-- NỘI DUNG TRANG -->
<div class="container mt-4">
    <%
        hoaDonBO ds = new hoaDonBO();
    	khachHang newkh=(khachHang) session.getAttribute("khachHang");
        ArrayList<hoaDon> list = ds.getDsHoaDonChuaMuaTheoMaKh(newkh.getMakh());
    %>

    <h3 class="mb-3">Danh sách hóa đơn chưa thanh toán</h3>

    <div class="row g-3"> 
    	<%if (list==null || list.isEmpty()) {%>
     	<div class="alert alert-info text-center">Danh sach trong</div>
	    <div class="text-center">
	        <a href="trangChuController" class="btn btn-primary">Quay về trang chủ</a>
	    </div>
	    <%
	    return;
    	} %>
        <% for (hoaDon hd : list) { 
        
        %>
        <div class="col-sm-4">  
            <div class="border rounded p-3 shadow-sm bg-light">

                <h5>Mã hóa đơn: <%= hd.getMaHoaDon() %></h5>

                <p>Mã khách hàng: <%= hd.getMakh() %></p>
                
                <p>Ngày mua: <%= hd.getNgayMua() %></p>

                <p>
                    Trạng thái: 
                    <span class="badge <%= hd.isDaMua() ? "bg-success" : "bg-warning text-dark" %>">
                        <%= hd.isDaMua() ?	"Đã mua" : "Chưa thanh toán" %>
                    </span>
                </p>
				<%long t=ds.getThanhTien(hd.getMaHoaDon()); %>
                    <p><b>Tổng tiền:</b> <%= String.format("%,d", t) %> VNĐ</p>
                <div class="d-flex justify-content-between mt-2">
                    <a class="btn btn-primary btn-sm" 
                       href="chiTietHoaDonController?mahd=<%=hd.getMaHoaDon()%>">
                        Xem chi tiết
                    </a>
                    
                    
                    <%
			            // Lấy khách hàng từ session
			            khachHang kh = (khachHang) session.getAttribute("khachHang"); 
			        %>
                    
					<form action="payment" method="post">
						<input type="hidden" name="totalBill" value="<%=t%>">
						<input type="hidden" name="idUser" value="<%=kh.getMakh()%>">
						<input type="hidden" name="idOrder" value="<%=hd.getMaHoaDon()%>">
						<button class="btn btn-primary" type="submit">Thanh toán</button>
					</form>
					
                    <a class="btn btn-danger btn-sm"
                       href="xoaHoaDonController?mahdx=<%=hd.getMaHoaDon()%>">
                        Xóa
                    </a>
                </div>

            </div>
        </div>
        <% } %>
    </div>
</div>


</body>
</html>
