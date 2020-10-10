package com.cubicit.controller.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cubicit.SwaggerCtrl;
import com.cubicit.controller.vo.ApplicationResponseVO;
import com.cubicit.controller.vo.CustomerVO;
import com.cubicit.controller.vo.UpdateRoles;
import com.cubicit.service.CustomerServiceImpl;

@RestController
//this annotation ensures all the method inside will generate
//raw data ->>JSON,XML etc
@RequestMapping("/v3")
@CrossOrigin(origins = "*")
@SwaggerCtrl
public class CustomerController {
	
	//Spring data jpa - Spring jdbc
	@Autowired	
	private CustomerServiceImpl customerService;
	
	//{"name":"James King","email":"mocha@gmail.com","debitcard":null,"valid":null,"cvv":0,
	 //"mobile":"9999762622","photo":null,"dbphoto":null,"age":12,"company":"HCL"}
     //}
	//@RequestBody - reading the raw data from incoming request and creating an instance of CustomerVO
	// and finally populating this raw data into this object
	@PostMapping("/customers")
	public ApplicationResponseVO createCustomer(@RequestBody CustomerVO customerVO){
		
		if(customerVO.getAge()==0){
			customerVO.setAge(12);
		}
		
		if(customerVO.getCompany()==null){
			customerVO.setCompany("Technihunk1823");
		}
		
		if(customerVO.getCvv()==0){
			customerVO.setCvv(334);
		}
		
		System.out.println(customerVO);
		customerService.save(customerVO);
		//We will save it into database
		ApplicationResponseVO applicationResponseVO=new ApplicationResponseVO();
		applicationResponseVO.setStatus("200");
		applicationResponseVO.setMessage("customer is added email = "+customerVO.getEmail());
		return applicationResponseVO;
	}
	
	
	@PutMapping("/ccustomer")
	public ApplicationResponseVO updateCustomer(@RequestBody CustomerVO customerVO){
		System.out.println(customerVO);
		customerService.update(customerVO);
		ApplicationResponseVO applicationResponseVO=new ApplicationResponseVO();
		applicationResponseVO.setStatus("200");
		applicationResponseVO.setMessage("customer is added email = "+customerVO.getEmail());
		return applicationResponseVO; //
	}
	
	@PostMapping("/ccustomer")
	public ApplicationResponseVO ccreateCustomer(@RequestBody CustomerVO customerVO){
		System.out.println(customerVO);
		ApplicationResponseVO applicationResponseVO=new ApplicationResponseVO();
		applicationResponseVO.setStatus("200");
		applicationResponseVO.setMessage("customer is added email = "+customerVO.getEmail());
		return applicationResponseVO;
	}
	
	@GetMapping(value="/customers",params={"cid"})
	public CustomerVO getCustomerByCid(@RequestParam int  cid){
		CustomerVO  customerVO= customerService.findById(cid);
		return customerVO; //view resolver is not coming in pic
	}
	
	@GetMapping(value="/customers",params={"email"})
	public CustomerVO getCustomer(@RequestParam String email){
		CustomerVO  customerVO=customerService.findByEmail(email);
		return customerVO; //view resolver is not coming in pic
	}
	
	
	
	//  //Making JavaScript object
   // var data={cid:pcid,sroles:editedRolesId};
    //var jsonData=JSON.stringify(data);
	//jsonData ={"cid":19,sroles:[1,4]}
	@PutMapping("customers/roles")
	public String updateCustomerRoles(@RequestBody UpdateRoles updateRoles){//{"cid":19,sroles:[1,4]}//List<Integer> sroles;
		return customerService.updateRolesForCustomer(updateRoles);
	}
	
	@GetMapping("customers/roles")
	public List<String> findRoles(@RequestParam int  cid){
		return customerService.findRolesForCustomer(cid);
	} 
	
	@GetMapping("/customers")
	public List<CustomerVO> getCustomers(){
		List<CustomerVO> customerVOs = customerService.findAllCustomers();
		return customerVOs;
	}
	
	@GetMapping("/ccustomers")
	public List<CustomerVO> getCustomers(String name,String email){
		List<CustomerVO> customerVOs = customerService.csearch(name,email);
		return customerVOs;
	}
	
	

}
