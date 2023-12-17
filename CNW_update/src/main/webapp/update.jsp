<%@page import="Model.BEAN.Tour"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Tour</title>
<script>
    function validateForm() {
      var image = document.forms["updateForm"]["newimage"].value;
    
      if (image === "") {
        alert("Vui lòng chọn ảnh");
        return false;
      }
    }
  </script>
<style >

form {
  border: 1px solid black;
  border-radius: 10px;
  width: 50%;
}

</style>
<!-- Nhúng các tệp CSS của Bootstrap -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

<!-- JavaScript để đặt giá trị mặc định -->
</head>
<body>
<%
	Tour tour = (Tour)request.getAttribute("tour");
	int idtour = (int)session.getAttribute("idtour");
	session.setAttribute("idtour", idtour);
%>

<!-- Form cập nhật Tour -->
<form action="updateServlet" method="post" class="container mt-5 form"
	enctype="multipart/form-data" name="updateForm"
	onsubmit="return validateForm()">
	<div class="row">
		<div class="col-md-6">
			<div class="mb-3">
				<label for="location" class="form-label">ID</label> <input
					style="border: 1px solid black; border-radius: 10px;" type="text"
					class="form-control" id="idtour" name="idtour"
					value="<%=tour.getDetail_id()%>" disabled="disabled">
			</div>
		</div>
		<div class="col-md-6">
			<div class="mb-3">
				<label for="location" class="form-label">Location</label> <input
					style="border: 1px solid black; border-radius: 10px;" type="text"
					class="form-control" id="location" name="location"
					value="<%=tour.getLocation()%>">
			</div>
		</div>
		<div class="col-md-6">
			<div class="mb-3">
				<label for="description" class="form-label">Description</label> <input
					style="border: 1px solid black; border-radius: 10px;" type="text"
					class="form-control" id="description" name="description"
					value="<%=tour.getDescription()%>">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="mb-3">
				<label for="number_day" class="form-label">Number of Days</label> <input
					style="border: 1px solid black; border-radius: 10px;" type="text"
					class="form-control" id="number_day" name="number_day"
					value="<%=tour.getNumber_day()%>">
			</div>
		</div>
		<div class="col-md-6">
			<div class="mb-3">
				<label for="price" class="form-label">Price</label> <input
					style="border: 1px solid black; border-radius: 10px;" type="text"
					class="form-control" id="price" name="price"
					value="<%=tour.getPrice()%>">
			</div>
		</div>
		<div class="col-md-6">
			<div class="mb-3">
				<label for="image" class="form-label">Image</label> <input
					type="text" class="form-control" id="oldimage" name="oldimage"
					value="<%=tour.getImage()%>" disabled="disabled">
			</div>
			<div class="mb-3">
				<input style="border: 1px solid black; border-radius: 10px;"
					type="file" class="form-control" id="newimage" name="newimage">
			</div>
		</div>
		<div class="col-md-6">
			<div class="mb-3">
				<label for="price" class="form-label">Video Link</label> <input
					style="border: 1px solid black; border-radius: 10px;" type="text"
					class="form-control" id="video_link" name="video_link"
					value="<%=tour.getVideo_link()%>">
			</div>
		</div>
		<div class="col-md-6">
			<div class="mb-3">
				<label for="detail_description" class="form-label">Detail
					Description</label>
				<textarea style="border: 1px solid black; border-radius: 10px;"
					class="form-control" id="detail_description"
					name="detail_description" rows="5" cols="50"><%=tour.getDetail_description()%></textarea>
			</div>
		</div>
	</div>
	<div class="row">
		<div style="padding-left: 600px;" class="col-md-6">
			<button style="width: 100px;" type="submit" class="btn btn-primary">Update</button>
		</div>
	</div>
</form>

<!-- Nhúng các tệp JavaScript của Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>