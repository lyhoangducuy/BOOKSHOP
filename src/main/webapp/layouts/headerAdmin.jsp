<%@ page import="model.khachHang" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Logo</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="mynavbar">
      <ul class="navbar-nav me-auto">
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/trangChuAdminController">
        Trang chủ</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/quanLyLoaiController">Quan ly loai</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/quanLySachController">Quan ly sach</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/hoaDonChuaThanhToanController">Xac nhan don hang</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/hoaDonDaThanhToanController">Hoa don da thanh toan</a></li>
        <%
            // Lấy khách hàng từ session
            khachHang kh = (khachHang) session.getAttribute("khachHang"); 
            if (kh == null) {
        %>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/dangNhapController">Đăng nhập</a>
               <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/dangKyController">Đăng ký</a></li>
            </li>
        <%
            } else {
        %>
            <li class="nav-item">
              <a class="nav-link">Xin chào <%= kh.getHoTen() %>!</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/dangXuatController">Đăng xuất</a>
            </li>
        <%
            }
        %>
      </ul>
      
    </div>
  </div>
</nav>
