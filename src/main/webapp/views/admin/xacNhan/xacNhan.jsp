<%@page import="model.hoaDon"%>
<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Quan ly hoa don chua mua</title>
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
     		<h2 class="mb-4">✅ Xac nhan don hang</h2>
     	</div>
	    <div class="col-4">
	    	<form class="d-flex" action="timKiemHoaDon">
		       <input class="form-control me-2" type="text" placeholder="Search" name="timKiem">
		       <input type="hidden" name="page" value="xacNhan">
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
            <th>Mã Hóa đơn</th>
            <th>Mã KH</th>
            <th>Ngày Mua</th>
            <th>Thao tác</th>
        </tr>
        </thead>

        <tbody>
<%
    // Controller phải đặt dsHoaDonChuaMua vào request
    ArrayList<hoaDon> ds = (ArrayList<hoaDon>) request.getAttribute("dsHoaDon");
    if (ds != null && !ds.isEmpty()) {
        for (hoaDon hd : ds) {
            String maHoaDon = String.valueOf(hd.getMaHoaDon()); 
            String maKH = String.valueOf(hd.getMakh()); 
            // Định dạng ngày mua cho dễ đọc hơn
            String ngayMua = hd.getNgayMua().toString(); 
%>
            <tr>
                <td><%= maHoaDon %></td>
                <td><%= maKH %></td>
                <td><%= ngayMua %></td>
                <td>
                    <%-- Nút xác nhận: Gửi Mã Hóa đơn đến Controller xử lý cập nhật trạng thái --%>
                    <a href="xacNhanMuaController?maHoaDon=<%= maHoaDon %>"
                       class="btn btn-success btn-sm"
                       onclick="return confirm('Xác nhận hóa đơn <%= maHoaDon %> đã thanh toán?');">
                        ✅ Xác nhận da thanh toan
                    </a>
                    <%-- Liên kết đến trang chi tiết/sửa hóa đơn --%>
                    <a href="chiTietHoaDonController?mahd=<%= maHoaDon %>"
                       class="btn btn-warning btn-sm">Xem chi tiết</a>
                    <a class="btn btn-danger btn-sm"
                       href="xoaHoaDonController?mahdx=<%=hd.getMaHoaDon()%>">
                        Xóa
                    </a>
                </td>
                </td>
            </tr>
<%
        }
    } else {
%>
        <tr>
            <td colspan="4" class="text-center text-muted">
                Không có hóa đơn chờ thanh toán nào.
            </td>
        </tr>
<%
    }
%>
</tbody>

    </table>

</div>


</body>
</html>