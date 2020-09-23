package com.cubicit.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubicit.controller.vo.CustomerVO;
import com.cubicit.controller.vo.UpdateRoles;
import com.cubicit.dao.AddressDaoRepository;
import com.cubicit.dao.CustomerDaoRepository;
import com.cubicit.entity.AddressEntity;
import com.cubicit.entity.CustomerEntity;
import com.cubicit.entity.RoleEntity;

@Service
public class CustomerServiceImpl {
	
	//Spring data jpa - Spring jdbc
	@Autowired	
	private CustomerDaoRepository customerDaoRepository;
	
	@Autowired	
	private AddressDaoRepository addressDaoRepository;
	
	
	public List<CustomerVO> findAllCustomers(){
		List<CustomerEntity> entities=customerDaoRepository.findAll();
		List<CustomerVO> customerVOs=new ArrayList<CustomerVO>();
		for(CustomerEntity entity :entities) {
			CustomerVO customerVO=new CustomerVO();
			BeanUtils.copyProperties(entity, customerVO);
			//Setting address manually
			if(entity.getAddressEntity()!=null){
				customerVO.setState(entity.getAddressEntity().getState());
				customerVO.setStreet(entity.getAddressEntity().getStree());	
			}
			
			customerVOs.add(customerVO);
		}
		return customerVOs;
		//Java8 features 
		/*return customerDaoRepository.findAll().stream() //Stream<CustomerEntity>
				.map(customerEntity->{ //Stream<CustomerVO>
			CustomerVO customerVO=new CustomerVO();
			BeanUtils.copyProperties(customerEntity, customerVO);
			return customerVO;
		}).collect(Collectors.toList());*/
		
	}
	
	@Transactional
	public String update(CustomerVO customerVO){
		//Loading whole customer entity inside persistence context
		CustomerEntity result=customerDaoRepository.findById(customerVO.getCid()).get();
		//I am making email and name dirty 
        //so when transaction is commited at the end of the method then
		//these changes get sync with database
		result.setEmail(customerVO.getEmail());
		result.setName(customerVO.getName());
		return "update";
	}
	
	
	public String save(CustomerVO customerVO){
		CustomerEntity customerEntity=new CustomerEntity();
		//What this line is doing?
		BeanUtils.copyProperties(customerVO, customerEntity);
		CustomerEntity result=customerDaoRepository.save(customerEntity);
		
		AddressEntity addressEntity=new AddressEntity();
		addressEntity.setState(customerVO.getState());
		addressEntity.setStree(customerVO.getStreet());
		addressEntity.setDoe(new Timestamp(new Date().getTime()));
		addressEntity.setPincode(12232);
		addressEntity.setCountry("INDIA");
		
		//VVI
		addressEntity.setCustomerEntity(result);
		
		addressDaoRepository.save(addressEntity);
		
		return "saved";
	}
	
	
	public CustomerVO findByEmail(String email){
		CustomerVO customerVO=null;
		Optional<CustomerEntity> optional=customerDaoRepository.findByEmail(email);
		if(optional.isPresent()){
			CustomerEntity customerEntity=optional.get();
			customerVO=new CustomerVO();
			BeanUtils.copyProperties(customerEntity, customerVO);
		}
		return customerVO;		
	}
	
	public CustomerVO findById(int cid){
		CustomerEntity customerEntity=customerDaoRepository.findById(cid).get();
		CustomerVO customerVO=new CustomerVO();
		BeanUtils.copyProperties(customerEntity, customerVO);
		return customerVO;
	}
	
	public List<String> findRolesForCustomer(int cid){
		CustomerEntity customerEntity=customerDaoRepository.findById(cid).get();
		Set<RoleEntity> droles=customerEntity.getRoles();
		List<String> list=new ArrayList<>();
		for(RoleEntity entity: droles){
			list.add(entity.getName());
		}
		return list;
	}
	

	@Transactional
	public String updateRolesForCustomer(UpdateRoles updateRoles) {
		CustomerEntity customerEntity=customerDaoRepository.findById(updateRoles.getCid()).get();
		Set<RoleEntity> ndroles=new HashSet<>();
		//2,4
		for(Integer crid:updateRoles.getSroles()){
			RoleEntity entity=new RoleEntity();
			entity.setRid(crid);
			ndroles.add(entity);
		}
		//overriding old roles with new one
		customerEntity.setRoles(ndroles);
		//customerDaoRepository.save(customerEntity);
		//this will be automatically updated into the database once transaction completed beacause of @Transaction
		return "success";
	}
	
/*	@Transactional
	public String updateRolesForCustomer(UpdateRoles updateRoles) {
		CustomerEntity customerEntity=customerDaoRepository.findById(updateRoles.getCid()).get();
		Set<RoleEntity> ndroles=new HashSet<>();
		//2,4
		for(Integer crid:updateRoles.getSroles()){
			//fetch entity from database from master record
			RoleEntity entity=roleRepository.findById(crid).get();
			ndroles.add(entity);
		}
		//overriding old roles with new one
		customerEntity.setRoles(ndroles);
		//this will be automatically updated into the database once transaction completed beacause of @Transaction
		return "success";
	}*/
	

}
