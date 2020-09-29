 function updateRoles(){
    	 //Http client ->>
    	 var pcid=$("#trcid").val();
    	 //creating array in JavaScript
    	 var editedRolesId = [];
         $.each($("input[name='srole']:checked"), function(){
        	 editedRolesId.push($(this).val());
         });
         //Making JavaScript object
         var data={cid:pcid,sroles:editedRolesId};
         var jsonData=JSON.stringify(data);
      	  
     	   //Creating GET,POST,PUT,DELETE
     	   const options = {
                    method: 'PUT',
                    body: jsonData,
                    headers: {
                      'Content-Type': 'application/json',
                      'Accept': 'application/json'
                    }
                  };
     	   
     	   var promise=fetch("v3/customers/roles",options);
     	   //public String
     	   promise.then(response=>response.text())
     	   .then(function(data){
     		   console.log(data);
     		  $("#updateRolePopup").modal("hide");
     	   });
    	
    }
  
  
    function editRolePopup(cid,name,email) {
    	 $("#rpname").html(name);
    	 $("#rpemail").html(email);
    	 $("#rprofilePic").attr("src","customer/photo?cid="+cid);
    	 $("#trcid").val(cid);
    	 
    	 
    	 fetch("v3/customers/roles?cid="+cid).then(function(response){
 			return response.json();
 	    }).then(function(myroles){
 	    	 
 	    	////first call to fetch only customer specific  roles
 		    	var tbcontents="";
 		    	//Second call to fetch all the roles
 		    	fetch("v3/roles").then(function(response){
 		    			return response.json();
 		    	}).then(function(roles){
 		    		  var tdata="";
 		    		  roles.forEach((pdata)=> {
 		  				tdata=tdata+'<tr>';
 		  				tdata=tdata+'<td>'+pdata.rid+'</td>';
 		  				tdata=tdata+'<td>'+pdata.name+'</td>';
 		  				var yes=myroles.includes(pdata.name);
 		  				if(yes){
 		  					tdata=tdata+'<td><input type="checkbox" name="srole" id="srole" checked value="'+pdata.rid+'"></td>';	
 		  				}else{
 		  					tdata=tdata+'<td><input type="checkbox" name="srole" id="srole" value="'+pdata.rid+'"></td>';	
 		  				}
 		  				tdata=tdata+'</tr>';
 		  			});
 		    		document.getElementById("crdata").innerHTML=tdata;
 		    	});
 		    	 $("#updateRolePopup").modal("show");
 	    });
    	 
    }
  
    function sendData(){
    	  var pname=$("#pname").html();
		  var pemail=$("#pemail").html();
		  var cdata={name:pname,email:pemail};
		   //convert above object into JSON
   	     var jsonData=JSON.stringify(cdata);
   	  
   	   //Creating 
   	   const options = {
                  method: 'POST',
                  body: jsonData,
                  headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                  }
                };
   	   
   	   var promise=fetch("v3/ccustomer",options);
   	   promise.then(response=>response.json())
   	   .then(function(data){
   		   console.log(data);
   	   });
    	
    }
    
     function generateCard(){
    	  var name=$("#tpname").val();
		  var cid =$("#tpcid").val();
		  // <img src="img/credit-card-front-template.jpg" id="cphoto">
		  var tete=new Date();
		  $("#cphoto").attr("src","loadCrediCard?cid="+cid+"&name="+name+"&time="+tete);
     }
  
     function openPopup(pcid){
    	 //	@GetMapping(value="/customers",params={"cid"})
    	 fetch("v3/customers?cid="+pcid).then(function(response){
			return response.json();//return json data to next fucntion
		}).then(function(data){ //data is now holding JavaScript object
			console.log(data);
		    //{"cid":3,"name":"Michal","email":"michal@gmail.com","debitcard":"234204329842384","valid":"09/2022","cvv":122,"mobile":"+19282782722","photo":null,"dbphoto":null,"age":32,"company":"WIPRO"}
            //<span style="font-size: 18px" id="pname"></span>
		    $("#pname").html("Name : "+data.name);
		    $("#pemail").html("Email : "+data.email);
		  
		    //setting these values inside hidden field
		    $("#tpname").val(data.name);
		    $("#tpcid").val(pcid);
		    
		    $("#crediCardPopup").modal("show");  		    
		});
     }
     
     function editCustomer(pcid){
    	 //Write ajax code to fetch customer data
    	 //	@GetMapping(value="/customers",params={"cid"})
    	 fetch("v3/customers?cid="+pcid).then(function(response){
			return response.json();//return json data to next fucntion
		}).then(function(data){ //data is now holding JavaScript object
			console.log(data);
		    //{"cid":3,"name":"Michal","email":"michal@gmail.com","debitcard":"234204329842384","valid":"09/2022","cvv":122,"mobile":"+19282782722","photo":null,"dbphoto":null,"age":32,"company":"WIPRO"}
		    $("#ename").val(data.name);
		    $("#eemail").val(data.email);
		    $("#ecid").val(data.cid);
		    $("#editImage").attr("src","/customer/photo?cid="+pcid);
		    $("#editCustomerPopup").modal("show");  		    
		});
    	 
    	 
     }
     
     function updateCustomer(){
    	    var name=$("#ename").val();
		    var email=$("#eemail").val();
		    var cid=$("#ecid").val();
		    var obj={cid:cid,name:name,email:email};
		    var jsonData=JSON.stringify(obj);
		    //Creating 
		   	   const options = {
		                  method: 'PUT',
		                  body: jsonData,
		                  headers: {
		                    'Content-Type': 'application/json',
		                    'Accept': 'application/json'
		                  }
		                };
		   	   
		   	   var promise=fetch("v3/ccustomer",options);
		   	   promise.then(response=>response.json())
		   	   .then(function(data){
		   		   console.log(data);
		   		   $("#editCustomerPopup").modal("hide");
		   	   });
     }
  
     function loadCustomer(){
		//I have to write code to fetch data from the server using AJAX and data will come from the server
		//in which format? json
		//http://localhost:444/cubic-mvc/v3/customers = this is called endpoint
		
		//method -GET
		//AJAX call
		//var promise=window.fetch("v3/customers");
		fetch("v3/customers").then(function(response){
			return response.json();
		}).then(function(data){ //data is java script object which holds json data coming from server
		    //data is array of javascript
		    //
		    var pk=[];
		    pk.push("Nagendra");
		    //{"cid":3,"name":"Michal","email":"michal@gmail.com","debitcard":"234204329842384","valid":"09/2022","cvv":122,"mobile":"+19282782722","photo":null,"dbphoto":null,"age":32,"company":"WIPRO"}
		    var tdata="";
			data.forEach((pdata)=> {
				console.log(pdata);
				tdata=tdata+'<tr>';
				tdata=tdata+'<td>'+pdata.cid+'</td>';
				tdata=tdata+'<td>'+pdata.name+'&nbsp;&nbsp;<a href="javascript:editCustomer('+pdata.cid+');"><img src="img/edita.png"><a/></td>';
				tdata=tdata+'<td>'+pdata.email+'</td>';
				tdata=tdata+'<td>'+pdata.debitcard+'</td>';
				tdata=tdata+'<td> <button type="button" class="btn btn-danger" onclick="editRolePopup('+pdata.cid+',\''+pdata.name+'\',\''+pdata.email+'\');">Role</button></td>';
				tdata=tdata+'<td>'+pdata.cvv+'</td>';
				tdata=tdata+'<td>'+pdata.mobile+'</td>';
				tdata=tdata+'<td>coming soon</td>';
				tdata=tdata+'<td>'+pdata.company+'</td>';
				tdata=tdata+'<td> <button type="button" class="btn btn-primary" onclick="openPopup('+pdata.cid+');">GEN</button></td>';
				tdata=tdata+'</tr>';
			});
			document.getElementById("cdata").innerHTML=tdata;
		});
     }
     
     function Role(name,description){
    	 this.name=name;
    	 this.description=description;
     }
     
     
     
 function addNewCustomer(){
    	 
    	 let name=$("#cname").val();
    	 let email=$("#cemail").val();
    	 let mobile=$("#cmobile").val();
    	 let password=$("#cpassword").val();
    	 var customer={name:name,email:email,password:password,mobile:mobile};
    	
    	   //convert above object into JSON
   	     var jsonData=JSON.stringify(customer);
   	
    	   //Creating 
     	   const options = {
                    method: 'POST',
                    body: jsonData,
                    headers: {
                      'Content-Type': 'application/json',
                      'Accept': 'application/json'
                    }
                  };
     	   
     	   var promise=fetch("v3/customers",options);
     	   promise.then(response=>response.json())
     	   .then(function(data){
     		   //	//data ={"message":"Role is created successfully with id = 10","status":"success"}
     		   console.log(data);
     		   $("#mmessage").html(data.message);
     		  $('#addCustomerPopup').modal('hide');
     	   }) .catch(err =>{
               console.log(err)
              // dispatch(loginFailed())
          });
    	 
     }
     
     function addNewRole(){
    	 
    	 let proleName=$("#roleName").val();
    	 let pdescription=$("#description").val();
    	 //Creating JavaScript object 
    	 var obj=new Role(proleName,pdescription);
    	   //convert above object into JSON
   	     var jsonData=JSON.stringify(obj);
   	
    	   //Creating 
     	   const options = {
                    method: 'POST',
                    body: jsonData,
                    headers: {
                      'Content-Type': 'application/json',
                      'Accept': 'application/json'
                    }
                  };
     	   
     	   var promise=fetch("v3/customer/roles",options);
     	   promise.then(response=>response.json())
     	   .then(function(data){
     		   //	//data ={"message":"Role is created successfully with id = 10","status":"success"}
     		   console.log(data);
     		   $("#mmessage").html(data.message);
     		  $('#addRolePopup').modal('hide');
     	   });
    	 
     }
     
     function openRoleModal(){
    	 $("#roleName").val(""); //input field so we have to use val
    	 $("#description").val(""); ////input field so we have to use val
     	$('#addRolePopup').modal('show');
     }
  