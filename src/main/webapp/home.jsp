<!DOCTYPE html>
<html lang="en">
<head>
  <title>Auth Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>

<header style="height: 30px;background-color: maroon;">
</header>

<div class="container">
  <h2>Welcome to home!!!</h2>
   <a href="${pageContext.request.contentType}/customers">
   <img src="${pageContext.request.contentType}/img/student.png" style="height: 120px;">
   Customers!
   </a>
   <hr/>
   <div style="width: 60%">
   
  
   <img src="${pageContext.request.contentType}/img/home.jpg">
  </div>
</div>

</body>
</html>
