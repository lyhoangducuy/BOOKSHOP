<%@page import="model.phuongThucThanhToan"%>
<%@page import="model.hoaDon"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.khachHang"%>
<%@page import="bo.hoaDonBO"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Kết quả thanh toán</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/trangChu.css">
</head>
<body>
<div class="container mt-4">
		<%
phuongThucThanhToan pttt = (phuongThucThanhToan)request.getAttribute("pttt");
String idOrder = (String) request.getAttribute("idOrder");
String total = (String) request.getAttribute("totalBill");

if (phuongThucThanhToan.TIEN_MAT.equals(pttt)) {
%>
    <div class="alert alert-success">
        <h3>Thanh toán thành công (COD)!</h3>
        <p>Vui lòng chuẩn bị tiền khi nhận hàng.</p>
        <p>Số hóa đơn: <strong><%=idOrder %></strong></p>
        <p>Tổng tiền: <strong><%=total %></strong></p>
    </div>

<%
} else if (phuongThucThanhToan.NGAN_HANG.equals(pttt)) {
    String transResult = (String) request.getAttribute("transResult");
    if ("True".equals(transResult)) {
%>
        <div class="alert alert-success">
            <h3>Giao dịch ngân hàng thành công!</h3>
            <p>Số hóa đơn: <strong><%=idOrder%></strong></p>
            <p>Tổng tiền: <strong><%=total%></strong></p>
            <p>Vui lòng để ý số điện thoại nhân viên tư vấn:
                <strong>0383459560</strong>
            </p>
        </div>
<%
    } else {
%>
        <div class="alert alert-danger">
            <h3>Giao dịch ngân hàng thất bại!</h3>
            <p>Vui lòng liên hệ tổng đài để được hỗ trợ.</p>
            <p>Số tổng đài: <strong>0383456xxx</strong></p>
        </div>
<%
    }
}
%>


        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/trangChuController">Trang chủ</a></li>
</div>


</body>
</html>
