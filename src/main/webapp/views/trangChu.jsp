<%@page import="model.sach"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="bo.sachBO"%>
<%@page import="model.loai"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bo.loaiBO"%>
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
</head>
<body>

<!-- THANH MENU -->
<jsp:include page="../layouts/header.jsp"/>

<!-- NỘI DUNG TRANG -->
<div class="row">
  <!-- CỘT TRÁI: DANH SÁCH LOẠI -->
   <div class="col-3">
	   	<ul>
	    <%
	        ArrayList<loai> ds = (ArrayList<loai>) request.getAttribute("dsloai");
	        if (ds != null) {
	            for (loai l : ds) {
	    %>
	                <li>
	                    <a href="trangChuController?maloai=<%=l.getMaLoai()%>"><%=l.getTenLoai()%></a>
	                </li>
	    <%
	            }
	        }
	    %>
	    </ul>
	</div>

  <div class="col-9">
  	<div class="row">
	<%
	        ArrayList<sach> dssach = (ArrayList<sach>) request.getAttribute("dssach");
	        if (dssach != null) {
	            for (sach s : dssach) {
	    %>
	               
	               		<div class="col-3"> 
	               			<img src="<%=s.getAnh()%>" alt="<%=s.getTenSach()%>" class="img-fluid">

	               			<%=s.getTenSach() %><br>
	               			<%=s.getSoTap() %><br>
	               			<%=s.getSoLuong() %><br>
	               			<%=s.getGia() %>VND<br>
	               		</div>
	               
	    <%
	            }
	        }
	    %>
	   </div>
  </div>
</div>

</body>
</html>
