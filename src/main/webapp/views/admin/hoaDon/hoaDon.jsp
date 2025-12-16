<%@page import="model.hoaDon"%>
<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hoa don da thanh toan</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/trangChu.css"> <%-- Đảm bảo đường dẫn CSS đúng --%>
    <style>
        /* Tùy chỉnh nhỏ để hiển thị cờ 'Đã mua' */
        .status-badge {
            padding: 0.35em 0.65em;
            font-size: 0.75em;
            font-weight: 700;
            line-height: 1;
            text-align: center;
            white-space: nowrap;
            vertical-align: baseline;
            border-radius: 0.375rem;
        }
    </style>
</head>
<body>

<jsp:include page="/layouts/headerAdmin.jsp"/>

<div class="container mt-4">
	
    <div class="row">
     	<div class="col-3">
     		<h2 class="mb-4">🧾 Hoa don da thanh toan</h2>
     	</div>

	    <div class="col-4">
	    	<form class="d-flex" action="timKiemHoaDon">
		       <input class="form-control me-2" type="text" placeholder="Search" name="timKiem">
		       <input type="hidden" name="page" value="hoaDon">
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
            <th>Trạng thái (Đã mua)</th>
            <th>Thao tác</th>
        </tr>
        </thead>

        <tbody>
<%
    // Thay thế 'dsSach' bằng 'dsHoaDon' và cast sang ArrayList<HoaDon>
    ArrayList<hoaDon> ds = (ArrayList<hoaDon>) request.getAttribute("dsHoaDon");
    if (ds != null && !ds.isEmpty()) {
        for (hoaDon hd : ds) {
            // Lấy các giá trị từ đối tượng HoaDon (Giả sử có các getter tương ứng)
            String maHoaDon = String.valueOf(hd.getMaHoaDon()); // bigint
            String maKH = String.valueOf(hd.getMakh()); // bigint
            String ngayMua = hd.getNgayMua().toString(); // datetime
            boolean daMua = hd.isDaMua(); // bit/boolean
            
            // Định dạng trạng thái Đã mua
            String statusText = daMua ? "Đã thanh toán" : "Chưa thanh toán";
            String statusClass = daMua ? "bg-success" : "bg-warning text-dark";
%>
            <tr>
                <td><%= maHoaDon %></td>
                <td><%= maKH %></td>
                <td><%= ngayMua %></td>
                <td><span class="status-badge <%= statusClass %>"><%= statusText %></span></td>
                <td>
                    <%-- Liên kết đến trang chi tiết/sửa hóa đơn --%>
                    <a href="chiTietHoaDonController?mahd=<%= maHoaDon %>"
                       class="btn btn-warning btn-sm">Xem chi tiết</a>
                </td>
            </tr>
<%
        }
    } else {
%>
        <tr>
            <td colspan="5" class="text-center text-muted">
                Không có hóa đơn nào trong hệ thống.
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