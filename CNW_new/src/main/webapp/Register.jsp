<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="./View/CSS/register.css">
   <script>
    function validateForm() {
      var fullName = document.forms["registerForm"]["full_name"].value;
      var address = document.forms["registerForm"]["address"].value;
      var phoneNumber = document.forms["registerForm"]["phone"].value;
      var email = document.forms["registerForm"]["email"].value;
      var username = document.forms["registerForm"]["username"].value;
      var password = document.forms["registerForm"]["password"].value;

      if (fullName === "" || address === "" || phoneNumber === "" || email === "" || username === "" || password === "") {
        alert("Vui lòng nhập đầy đủ thông tin vào các trường.");
        return false;
      }
    }
  </script>
  <title>Register</title>
</head>
<body>
<form action="Register" method="post" name="registerForm" onsubmit="return validateForm()">
<div class="page-wrapper">
    <div class="wrapper">
      <div class="card">
        <h2 class="title">Register Form</h2>
        <div class="row">
          <div class="col-2">
            <label class="label">Full Name</label>
            <div class="input-group">
              <input class="input--style-4" type="text" name="full_name">
            </div>
          </div>
          <div class="col-2">
            <label class="label">Address</label>
            <div class="input-group">
              <input class="input--style-4" type="text" name="address">
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-2">
            <label class="label">Phone Number</label>
            <div class="input-group">
              <input class="input--style-4" type="text" name="phone">
            </div>
          </div>
          <div class="col-2">
            <label class="label">Email</label>
            <div class="input-group">
              <input class="input--style-4" type="email" name="email">
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-2">
            <label class="label">Username</label>
            <div class="input-group">
              <input class="input--style-4" type="text" name="username">
            </div>
          </div>
          <div class="col-2">
            <label class="label">Password</label>
            <div class="input-group">
              <input class="input--style-4" type="password" name="password">
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-2">
            <label class="label">Role</label>
            <div class="input-group">
              <input class="input--style-4" type="text" name="role" value="Client" readonly="readonly">
            </div>
          </div>
        </div>
        <div class="input-group">
         <input class="btn btn--blue" type="submit" value="Register" >
        </div>
      </div>
    </div>
  </div>
</form>
  
</body>
</html>