<%@page import="model.sach"%>
<%@page import="model.loai"%>
<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<jsp:include page="/layouts/headerAdmin.jsp"/>


<div class="container-fluid mt-3">
    <div class="row">
       <!-- THỐNG KÊ ĐƠN HÀNG -->
<div class="row mb-4">
    <div class="col-md-3 col-sm-6 mb-3">
        <div class="card text-white bg-primary h-100">
            <div class="card-body">
                <h6 class="card-title">Đơn hàng hôm nay</h6>
                <h3>
                    <%= request.getAttribute("donHomNay") != null 
                        ? request.getAttribute("donHomNay") 
                        : 0 %>
                </h3>
            </div>
        </div>
    </div>

    <div class="col-md-3 col-sm-6 mb-3">
        <div class="card text-white bg-success h-100">
            <div class="card-body">
                <h6 class="card-title">Đơn hàng tháng này</h6>
                <h3>
                    <%= request.getAttribute("donThangNay") != null 
                        ? request.getAttribute("donThangNay") 
                        : 0 %>
                </h3>
            </div>
        </div>
    </div>
</div>
       
    </div>
</div>
</body>
</html>
