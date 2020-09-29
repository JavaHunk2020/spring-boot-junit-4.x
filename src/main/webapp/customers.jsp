<!DOCTYPE html>
<html lang="en">
<head>
  <title>Customer Ajax!!!</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  
  <script src="js/customer.js">
  </script>
  
</head>
<body>

<header style="height: 30px;background-color: maroon;">
</header>

<div class="container">
   <img src="img/header.png">
   <hr/>
   <span style="font-size: 18;color: black;font-weight: bold;">
   Customer Data
    <button type="button" class="btn btn-danger" onclick="loadCustomer();">Load Customers</button>
    <button type="button" class="btn btn-primary" onclick="openRoleModal();">Add Role</button>
     <button type="button" class="btn btn-warning" onclick="openRoleModal();">Change Password</button>
    <button type="button" class="btn btn-primary" onclick="$('#addCustomerPopup').modal('show')">Add Customer</button>
       <a href="${pageContext.request.contextPath}/customer/logout"><button type="button" class="btn btn-warning">Logout</button></a>
    
   ${message}
   </span>
   <hr/>
    <span id="mmessage" style="color:blue;font-size: 18px;"></span>
   <hr/>
   <div style="width: 100%">
   
   <table class="table table-bordered">
    <thead>
      <tr style="background-color: #50aaff;">
       <th>Cid</th>
        <th>Name</th>
        <th>Email</th>
        <th>Debit Card</th>
        <th>Valid</th>
        <th>CVV</th>
        <th>Mobile</th>
        <th>Photo</th>
        <th>Company</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody id="cdata">
    
    </tbody>
  </table>
   
  </div>
</div>



 <!-- The Modal -->
  <div class="modal" id="updateRolePopup">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 540px;">
      
       <input type="hidden" id="trcid">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Edit Customer Role!</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
             Name : <span style="font-size: 18pxlfont-weight: bold;margin-right: 50px;" id="rpname" >Nagendra Kumar</span>
             <img src="img/college-student-dental-health.jpg"  style="height: 75px;" id="rprofilePic">  
             <hr/>
            Email :  <span style="font-size: 18px;color: red;font-weight: bold;" id="rpemail">jaha8@gmail.com</span>
             <hr/>
             <table class="table table-bordered">
    <thead>
      <tr style="background-color: #50aaff;">
       <th>SNO</th>
        <th>Role</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody id="crdata">
       <tr>
          <td>12</td><td>ADMIN</td><td>TODO</td>
       </tr>
    
    </tbody>
  </table>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="updateRoles()">UPDATE</button>
          <button type="button" class="btn btn-warning" onclick="sendData();">CANCEL</button>
        </div>
        
      </div>
    </div>
  </div>
  
  
  
<!-- The edit customer -->
  <div class="modal" id="editCustomerPopup">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 540px;">
       <input type="hidden" name="ecid" id="ecid">
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Edit Customer!!</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
             <hr/>
             <div class="from-group">
                 <label for="cname">Name : </label>
                 <input type="text" class="form-control" name="name" id="ename">
             </div>
             
              <div class="from-group">
                 <label for="eemail">Email : </label>
                 <input type="text" class="form-control" name="email" id="eemail">
             </div>
             <hr/>
             <img src="" id="editImage" style="height: 110px;">
             
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="updateCustomer()">Update Customer</button>
          <button type="button" class="btn btn-warning" onclick="$('#editCustomerPopup').modal('hide');">Cancel</button>
        </div>
        
      </div>
    </div>
  </div>



<!-- The Modal -->
  <div class="modal" id="addCustomerPopup">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 540px;">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Add New Customer!</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
             <hr/>
             <div class="from-group">
                 <label for="cname">Name : </label>
                 <input type="text" class="form-control" name="name" id="cname">
             </div>
             
              <div class="from-group">
                 <label for="cemail">Email : </label>
                 <input type="text" class="form-control" name="email" id="cemail">
             </div>
             
               <div class="from-group">
                 <label for="cmobile">Mobile : </label>
                 <input type="text" class="form-control" name="mobile" id="cmobile">
             </div>
             
             
               <div class="from-group">
                 <label for="cpassword">Password : </label>
                 <input type="text" class="form-control" name="password" id="cpassword">
             </div>
             
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="addNewCustomer()">Add Customer</button>
          <button type="button" class="btn btn-warning" onclick="$('#addCustomerPopup').modal('hide');">Cancel</button>
        </div>
        
      </div>
    </div>
  </div>


<!-- The Modal -->
  <div class="modal" id="addRolePopup">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 540px;">
      
       <input type="hidden" id="tpname">
       <input type="hidden" id="tpcid">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Add New Role!</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
             <hr/>
             <div class="from-group">
                 <label for="roleName">Name : </label>
                 <input type="text" class="form-control" name="roleName" id="roleName">
             </div>
             
              <div class="from-group">
                 <label for="description">Description : </label>
                 <input type="text" class="form-control" name="description" id="description">
             </div>
             
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="addNewRole()">Add Role</button>
          <button type="button" class="btn btn-warning" onclick="$('#addRolePopup').modal('hide');">Cancel</button>
        </div>
        
      </div>
    </div>
  </div>


 <!-- The Modal -->
  <div class="modal" id="crediCardPopup">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 540px;">
      
       <input type="hidden" id="tpname">
       <input type="hidden" id="tpcid">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Customer Credit Card!</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
             <span style="font-size: 18pxlfont-weight: bold;" id="pname" ></span>
             <span style="font-size: 18px;color: red;font-weight: bold;" id="pemail"></span>
             <hr/>
             <img src="img/credit-card-front-template.jpg" id="cphoto">
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="generateCard()">Generate Card</button>
          <button type="button" class="btn btn-warning" onclick="sendData();">Save</button>
        </div>
        
      </div>
    </div>
  </div>

</body>
</html>
