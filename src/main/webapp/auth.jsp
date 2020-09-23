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
 
  <script>
  
        function clearText(){
        	document.getElementById("emessage").innerHTML="";
        }
  
  		function validateForm() {
  			//{name: "Nagendra", email: "nagendra@gmail.com", color: "white"}
  			var obj={};
  			obj.name="Nagendra";
  			obj.email="nagendra@gmail.com";
  			obj.color="white";
  			console.log(obj);
  			
  			//This array of customer
  			var customers=["Nagendra","Michal","Mars"];
  			
  			var customer={name: "Michal", email: "michal@gmail.com", color: "black"};
  			console.log(customer);
  			
  			//loosily typed checking lanaguage
  			console.log("#)#((##()))");
  			var username=document.getElementById("username").value;
  			username=username.trim();
  			
  			if(username=="") {
  				//alert("Hey! username cannot be blank!");
  				//<span style="font-size: 18;color: blue;font-weight: bold;" id="emessage">3454353534535345</span>
  				document.getElementById("emessage").innerHTML="Hey! username cannot be blank!";
  				document.getElementById("username").focus();
  				return;
  			}
  			
  			var pswd=document.getElementById("pswd").value;
  			pswd=pswd.trim();
  			if(pswd.length==0) {
  				document.getElementById("emessage").innerHTML="Hey! password cannot be blank!";
  				document.getElementById("pswd").focus();
  				return;
  			}
  			//Submit form using JavaScript
  			document.authForm.submit();
  		}
       
  </script>  
  
</head>
<body>

<header style="height: 30px;background-color: maroon;">
</header>

<div class="container">
  <h2>Login Page!!!</h2>
   <img src="assets/img/student.png" style="height: 120px;">
   <hr/>
   <span style="font-size: 18;color: blue;font-weight: bold;" id="emessage">${message}</span>
   <hr/>
   <div style="width: 60%">
  <form action="authUser" method="POST" name="authForm">
    <div class="form-group">
      <label>Username/Email:</label>
      <input type="text" class="form-control"  placeholder="Enter Username" name="username" id="username" onkeyup="clearText();">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control"  placeholder="Enter password" name="pswd" id="pswd" onkeyup="clearText();">
    </div>
    <button type="button" class="btn btn-primary" onclick="validateForm();">Login</button>
    
    <a href="obiz">
    <button type="button" class="btn btn-danger">Add Biz</button>
    </a>
    
     <a href="showBizs">
    <button type="button" class="btn btn-warning">Show Biz</button>
    </a>
  </form>
  </div>
</div>

</body>
</html>
