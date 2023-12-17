<%@page import="Model.BEAN.Tour"%>
<%@page import="Model.BEAN.Booking"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Update Booking</title>

<!-- Nhúng các tệp CSS của Bootstrap -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

</head>
<body>
<%
	Booking booking = (Booking)request.getAttribute("booking");
	Tour tour = (Tour)request.getAttribute("tour");
	String username = (String)session.getAttribute("username");
	int bookingId = Integer.parseInt((String)session.getAttribute("bookingId"));
	session.setAttribute("bookingId", bookingId);
	session.setAttribute("username", username);
	System.out.println(bookingId);
%>
<form action="UpdateBooking" method="post" class="container mt-5" name="updateBookingForm">
  <div class="mb-3">
    <label for="location" class="form-label">Location</label>
    <input type="text" class="form-control" id="location" name="location" value="<%= tour.getLocation() %>" disabled>
  </div>
  <div class="mb-3">
    <label for="description" class="form-label">Booking Date</label>
    <input type="date" class="form-control" id="bookingdate" name="bookingdate" value="<%= booking.getBooking_date() %>">
  </div>
  <div class="mb-3">
    <label for="number_day" class="form-label">Number of Participants</label>
    <input type="number" class="form-control" id="number_participants" name="number_participants" value="<%= booking.getNum_participants() %>">
  </div>
  <button type="submit" class="btn btn-primary">Update</button>
</form>

<!-- Nhúng các tệp JavaScript của Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>