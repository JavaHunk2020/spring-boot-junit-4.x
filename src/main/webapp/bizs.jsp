<!DOCTYPE html>
<%@page import="com.cubicit.controller.Biz"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
  <title>Biz Data Page</title>
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
   <img src="img/student.png" style="height: 120px;">
   <hr/>
   <span style="font-size: 18;color: blue;font-weight: bold;">Biz Data
   ${message}
   </span>
   <hr/>
   <div style="width: 100%">
   
   <table class="table table-bordered">
    <thead>
      <tr>
       <th>ID</th>
        <th>Name</th>
        <th>Brand</th>
        <th>DOE</th>
        <th>Photo</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
    <%
    List<Biz> bizs=(List<Biz>)request.getAttribute("ashma");
    for(Biz biz:bizs){
    	List<Integer> cimageIds=biz.getCimageIds();
    %>
      <tr>
       <td style="background-color: #555aff;color:white;"><%=biz.getId() %></td>
        <td><%=biz.getName() %></td>
        <td><%=biz.getBrand() %></td>
        <td><%=biz.getDoe() %></td>
        <td>
        
         <img src="loadPhoto?dbid=<%=biz.getId()%>" style="height: 120px;">
         
  
         <%
         for(Integer cid:cimageIds){ %>
         <img src="cloadPhoto?dbid=<%=cid%>" style="height: 120px;">
         <a href="deleteCPhoto?cid=<%=cid%>"> 
            <img src="img/del.png">
         </a>
        <% } %>
                
        <a href="rotateImageRight?dbid=<%=biz.getId()%>">
   				 <button type="button" class="btn btn-danger">Right Rotation</button>
   		 </a>
         
        </td>
        
        <td>
        
        <a href="deleteBizId?id=<%=biz.getId() %>">
        <img src="img/delete.png" style="height: 40px;">
        </a>
        
        <a href="editBiz?did=<%=biz.getId() %>">
            <img src="img/edita.png">
        </a>
        
        </td>
      </tr>
    <% } %> 
     
    </tbody>
  </table>
   
  </div>
</div>

</body>
</html>
