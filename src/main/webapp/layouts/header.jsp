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
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/trangChuController">Trang chủ</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/gioHangController">Giỏ hàng</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/thanhToanController">Thanh toán</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/lichSuMuaHangController">Lịch sử mua hàng</a></li>
        
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
      <form class="d-flex" action="trangChuController">
        <input class="form-control me-2" type="text" placeholder="Search" name="timKiem">
        <button class="btn btn-primary" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
