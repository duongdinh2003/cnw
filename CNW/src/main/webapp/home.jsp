<%@page import="Model.BEAN.User"%>
<%@page import="Model.BEAN.Tour"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Trang Thông Tin</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<!-- custom css file link -->
<link rel="stylesheet" type="text/css" href="./View/CSS/style.css">
<script type="text/javascript">
function showBookingForm(location, fullname, phonenumber, email, address) {
    var bookingForm = document.getElementById('booking-form');
    bookingForm.style.display = 'flex';
    
    document.forms["bookingForm"]["location"].value = location;
    document.forms["bookingForm"]["username"].value = fullname;
    document.forms["bookingForm"]["phonenumber"].value = phonenumber;
    document.forms["bookingForm"]["email"].value = email;
    document.forms["bookingForm"]["address"].value = address;
}
</script>
</head>
<body>
	<%
	String username = (String) session.getAttribute("username");
	session.setAttribute("username", username);
	%>
	<header class="header">
		<section class="section">
			<div class="introduce">
				<img class="logo-page" src="./View/icon/Logo.png">
				<h1 class="title-page">
					<span class="large-text">Đề tài quản lí tour du lịch</span> <span
						class="small-text">
						<h3 class="info-contact">
							Tel: 036 520 5808&nbsp;&nbsp;-&nbsp;&nbsp;088 915 9648<br>
							Email: nguyenthanhhung26102003@gmail.com
						</h3>
					</span>
				</h1>
			</div>

			<div class="list-item">
				<ul class="item">
					<li><a href="" itemid="">TRANG CHỦ</a></li>
					<li><a href="MyTours">My Tours</a></li>
					<li><a href="">TOUR HÀ NỘI</a></li>
					<li><a href="">TOUR ĐÀ NẴNG</a></li>
					<li><a href="">TOUR TP.HCM</a></li>
					<li><a href="Login.jsp">Log out</a></li>
				</ul>
			</div>

			<div class="slideshow-container" id="slide-container"></div>

			<div class="title-tour">Travel Tour</div>

			<div class="select-tours">
				<table class="custom-table">
					<tr>
						<%
						ArrayList<Tour> tourArray = (ArrayList<Tour>) request.getAttribute("tourArray");
						User user = (User)request.getAttribute("user");
						if (tourArray != null) {
							for (int i = 0; i < tourArray.size(); i++) {
						%>
						<td>
							<div class="cell-content">
								<img src="./images/<%=tourArray.get(i).getImage()%>" alt="img">
								<div class="content-tour">
									<span class="name-tour"><%=tourArray.get(i).getLocation()%></span><br>
									<ul class="detail-tour">
										<li><span class="icon">✅</span><span class="text"><%=tourArray.get(i).getDescription()%></span></li>
										<li><span class="icon">✅</span><span class="text"><%=tourArray.get(i).getNumber_day()%></span></li>
										<li id="booking-form-li">
											<button class="submit-tour" onclick="showBookingForm(
																			   '<%=tourArray.get(i).getLocation()%>',
																			   '<%=user.getFull_name()%>',
																			   '<%=user.getPhone_number()%>',
																			   '<%=user.getEmail()%>',
																			   '<%=user.getAddress()%>')">Đặt tour</button>
										</li>
									</ul>
								</div>

							</div>
						</td>
						<%
						}
						} else {
						%>
						<td><h1>No tours available</h1></td>
						<%
						}
						%>

					</tr>
				</table>
			</div>
			<div class="booking-form" id="booking-form">
				<div class="booking-form-content">
					<h2>
						Thông Tin Đặt Tour <span id="booking-form-close">X Đóng</span>
					</h2>
					<form action="BookingTour" method="post" name="bookingForm">
						<input type="text" name="location" value="" readonly /> 
						<label class="booking-fullname">Họ tên Khách hàng</label>
						<input type="text" name="username" value="" readonly /> 
						<label class="booking-phonenumber">Số điện thoại liên hệ</label>
						<input type="text" name="phonenumber" value="" readonly /> 
						<label class="booking-email">Email</label>
						<input type="text" name="email" value="" placeholder="Email" readonly />
						<label class="booking-address">Địa chỉ</label> 
						<input type="text" name="address" value="" readonly /> 
						<label class="booking-num-participants">Số người</label>
						<input type="number" name="num_participants" /> 
						<label class="booking-date" for="bookingDate">Ngày khởi hành</label> 
						<input type="date" name="bookingDate" required />
						<button type="submit" name="bookingTour">Xác nhận</button>
					</form>
				</div>
			</div>
		</section>
	</header>
<script src="./View/JS/script.js"></script>
</body>
</html>