package com.cubicit.controller.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubicit.controller.vo.ApplicationResponseVO;
import com.cubicit.controller.vo.RoleVO;
import com.cubicit.service.RolesServiceImpl;

@RestController
//this annotation ensures all the method inside will generate
//raw data ->>JSON,XML etc
@RequestMapping("/v3")
public class RolesController {
	
	@Autowired
	private RolesServiceImpl rolesServiceImpl;
	
	@GetMapping("/roles")
	public List<RoleVO> getRoles(){
		List<RoleVO> roleVOs = rolesServiceImpl.findRoles();
		return roleVOs;
	}
	
	//{"name":"Super Admin","description":"This is super role"}
	@PostMapping("/customer/roles")
	//incoming json keys must match with attribute name of the class
	public ApplicationResponseVO createRole(@RequestBody RoleVO roleVO){
		int roleid=rolesServiceImpl.createRole(roleVO);
		ApplicationResponseVO applicationResponseVO=new ApplicationResponseVO();
		if(roleid==0){
			applicationResponseVO.setMessage("Role already exists!!!!");
		}else{
			applicationResponseVO.setMessage("Role is created successfully with id = "+roleid);	
		}
		applicationResponseVO.setStatus("success");
		//{"message":"Role is created successfully with id = 10","status":"success"}
		return applicationResponseVO;
	}
	
}
