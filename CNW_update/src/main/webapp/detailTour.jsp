<%@page import="Model.BEAN.Tour"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail Tour</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
}

.container {
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
}

.select-tours {
	text-align: center;
	margin-bottom: 20px;
}

.custom-table {
	border: 1px solid #ccc;
	padding: 20px;
}

.tour-card {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	margin-top: 20px;
}

.tour-details {
	text-align: center;
}

.detail-description {
	margin: 20px 0;
	padding: 10px;
	background-color: #f2f2f2;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
	line-height: 1.5;
	text-align: left;
}

.price {
	margin-top: 10px;
}

.submit-tour {
	background-color: #4CAF50;
	color: white;
	border: none;
	padding: 10px 20px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin-top: 20px;
	cursor: pointer;
}

.no-tours {
	text-align: center;
	margin-top: 20px;
}

iframe {
	width: 853px;
	height: 480px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="select-tours">
			<div class="custom-table">
				<% 
                    Tour tour = (Tour)request.getAttribute("tour_detail");
                    if (tour != null) {
                %>
				<h2>
					Thông Tin Chi Tiết Của Tour
					<%=tour.getLocation() %></h2>
				<div class="tour-card">
					<div class="tour-details">
						<div class="detail-description"><%= tour.getDetail_description() %></div>
						<iframe src="<%=tour.getVideo_link() %>"></iframe>
						<div class="price" style="color: red; font-size: 20px;">
							Giá vé:
							<%= tour.getPrice() %>/Người
						</div>
						<a href=""><button class="submit-tour">Đặt tour</button></a>
					</div>
				</div>
				<% 
                    } else {
                %>
				<div class="no-tours">
					<h1>No tours available</h1>
				</div>
				<% 
                    }
                %>
			</div>
		</div>
	</div>
</body>
</html>