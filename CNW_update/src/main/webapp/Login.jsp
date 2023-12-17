<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./View/CSS/login.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<script>
    function validateForm() {
      var username = document.forms["loginForm"]["username"].value;
      var password = document.forms["loginForm"]["password"].value;
    

      if (username === "" || password === "") {
        alert("Vui lòng nhập đầy đủ thông tin vào các trường.");
        return false;
      }
    }
  </script>
<title>Login</title>
</head>
<body>

<div class="wrapper fadeInDown">
  <div id="formContent">
 
    <div class="fadeIn first">
      <h3 style="font-family: Arial;">DHD Travel</h3>
    </div>

    
    <form action="checkLogin" method="post" name="loginForm" onsubmit="return validateForm()">
  <input type="text" id="login" class="fadeIn second" name="username" placeholder="username"><br>
  <input type="password" id="password" class="fadeIn third" name="password" placeholder="password"><br>
  <input type="submit" class="fadeIn fourth" value="Log In"><br>
    </form>
    <div id="formFooter">
      <a class="underlineHover" href="Register.jsp">Register?</a>
    </div>

  </div>
</div>
</body>
</html>