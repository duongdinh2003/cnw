<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Tour</title>

<!-- Nhúng các tệp CSS của Bootstrap -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script type="text/javascript">
	function validateForm() {
		var location = document.forms["addTourForm"]["location"].value;
		var description = document.forms["addTourForm"]["description"].value;
		var number_day = document.forms["addTourForm"]["number_day"].value;
		var image = document.forms["addTourForm"]["image"].value;
		var price = document.forms["addTourForm"]["price"].value;
		var video_link = document.forms["addTourForm"]["video_link"].value;
		var detail_description = document.forms["addTourForm"]["detail_description"].value;
		if (location === "" || description === "" || number_day === ""
				|| image === "" || price === "" || video_link === ""
				|| detail_description === "") {
			alert("Vui lòng nhập đầy đủ thông tin vào các trường.");
			return false;
		}
	}
</script>
<style>
	form {
	  border: 1px solid black;
	  border-radius: 10px;
	  width: 50%;
	}
</style>
</head>
<body>
<form action="addTour" method="post" class="container mt-5 form" enctype="multipart/form-data" name="addTourForm" onsubmit="return validateForm()">
  <div class="row">
    <div class="col-md-6">
      <div class="mb-3">
        <label for="location" class="form-label">Location</label>
        <input style=" border: 1px solid black;
         border-radius: 10px;"
          type="text" class="form-control" id="location" name="location">
      </div>
    </div>
    <div class="col-md-6">
      <div class="mb-3">
        <label for="description" class="form-label">Description</label>
        <input style=" border: 1px solid black;
         border-radius: 10px;" type="text" class="form-control" id="description" name="description">
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-md-6">
      <div class="mb-3">
        <label for="number_day" class="form-label">Number of Days</label>
        <input style=" border: 1px solid black;
         border-radius: 10px;" type="text" class="form-control" id="number_day" name="number_day">
      </div>
    </div>
    <div class="col-md-6">
      <div class="mb-3">
        <label for="image" class="form-label">Image</label>
        <input style=" border: 1px solid black;
         border-radius: 10px;" type="file" class="form-control" id="image" name="image">
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-md-6">
      <div class="mb-3">
        <label for="price" class="form-label">Price</label>
        <input style=" border: 1px solid black;
         border-radius: 10px;" type="text" class="form-control" id="price" name="price">
      </div>
    </div>
    
    <div class="col-md-6">
      <div class="mb-3">
        <label for="price" class="form-label">Video Link</label>
        <input style=" border: 1px solid black;
         border-radius: 10px;" type="text" class="form-control" id="video_link" name="video_link">
      </div>
    </div>
    <div class="col-md-6">
      <div class="mb-3">
        <label for="price" class="form-label">Detail Description</label>
        <textarea style="border: 1px solid black; border-radius: 10px;" class="form-control" id="detail_description" name="detail_description" rows="5" cols="50"></textarea>
      </div>
    </div>
  </div>
  <div class="row">
    <div  style="padding-left: 600px; " class="col-md-6">
      <button  style="width: 100px;" type="submit" class="btn btn-primary">Thêm</button>
    </div>
  </div>
</form>

<!-- Nhúng các tệp JavaScript của Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>