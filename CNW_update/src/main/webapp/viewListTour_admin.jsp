<%@page import="Model.BEAN.Tour"%>
<%@page import="Model.BEAN.User"%>
<%@page import="Model.BEAN.Booking"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking Table</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<style>
    .custom-table {
        border-collapse: collapse;
        width: 100%;
    }

    .custom-table thead th {
        background-color: #f8f9fa;
        border: 1px solid #dee2e6;
        padding: 8px;
        text-align: center;
        border: 1px solid black;
    }

    .custom-table tbody td {
        border: 1px solid #dee2e6;
        padding: 8px;
        text-align: center;
    }

    .custom-table tbody tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    .custom-table tbody tr th {
        border: 1px solid black;
    }

    .custom-table tbody tr td {
        border: 1px solid black;
    }
</style>
</head>
<body>
	<div class="container">
		<h2 style="padding: 70px 20px 20px 430px;">Danh Sách Tour Đã Được Đặt</h2>
		<table class="table custom-table">
			<thead>
				<tr>
					<th scope="col">STT</th>
					<th scope="col">Tên Khách Hàng</th>
					<th scope="col">Địa Điểm</th>
					<th scope="col">Số Điện Thoại</th>
					<th scope="col">Email</th>
					<th scope="col">Ngày Đặt</th>
					<th scope="col">Số Người</th>
				</tr>
			</thead>
			<tbody>
				<% 
                ArrayList<Booking> booking = (ArrayList<Booking>) request.getAttribute("booking");
				ArrayList<Tour> tourList = (ArrayList<Tour>)request.getAttribute("tourList");
				ArrayList<User> userList = (ArrayList<User>)request.getAttribute("userList");
                if (booking != null) {
                       for (int i = 0; i < booking.size(); i++) {
                %>
				<tr>
					<th scope="row"><%= i+1 %></th>
					<td><%= userList.get(i).getFull_name() %></td>
					<td><%= tourList.get(i).getLocation() %></td>
					<td><%= userList.get(i).getPhone_number() %></td>
					<td><%= userList.get(i).getEmail() %></td>
					<td><%= booking.get(i).getBooking_date() %></td>
					<td><%= booking.get(i).getNum_participants() %></td>
				</tr>
				<% } } %>
			</tbody>
		</table>
	</div>
	<div style="padding-left: 700px;">
		<button style="background-color: lightgrey;"
			onclick="window.history.back()">Quay lại</button>
	</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>