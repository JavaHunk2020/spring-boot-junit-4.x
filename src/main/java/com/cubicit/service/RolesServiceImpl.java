package com.cubicit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubicit.controller.vo.RoleVO;
import com.cubicit.dao.RoleRepository;
import com.cubicit.entity.RoleEntity;

@Service
public class RolesServiceImpl {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public int createRole(RoleVO roleVO){
		//roleVO.setName(roleVO.getName().toUpperCase());
		Optional<RoleEntity> orole=roleRepository.findByName(roleVO.getName().toUpperCase());
		if(!orole.isPresent()){
			RoleEntity entity=new RoleEntity();
			BeanUtils.copyProperties(roleVO, entity);
			//admin, ADMIN,Admin, ADmin=ADMIN
			entity.setName(entity.getName().toUpperCase());
			RoleEntity entity2=roleRepository.save(entity);
			return entity2.getRid();	
		}else{
			return 0;
		}
	}
	
	public List<RoleVO> findRoles() {
		List<RoleEntity> listEntity = roleRepository.findAll();
		List<RoleVO> roleVOs = new ArrayList<RoleVO>();
		for (RoleEntity entity : listEntity) {
			RoleVO roleVO = new RoleVO();
			BeanUtils.copyProperties(entity, roleVO);
			roleVOs.add(roleVO);
		}
		return roleVOs;
	}

}
