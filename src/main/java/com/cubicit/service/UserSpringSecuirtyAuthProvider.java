package com.cubicit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubicit.dao.CustomerDaoRepository;
import com.cubicit.entity.CustomerEntity;
import com.cubicit.entity.RoleEntity;

/**
 * This is special class for spring security
 * @author javahunk
 *
 */
@Service
public class UserSpringSecuirtyAuthProvider implements UserDetailsService{
	
	@Autowired
	private CustomerDaoRepository customerDaoRepository;

	//Who will call this method ?? spring security automatically
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<CustomerEntity> optional=customerDaoRepository.findByEmail(username);
		User user=null;
		if(optional.isPresent()){
			CustomerEntity customerEntity=optional.get();
			//Role coming from database
			Set<RoleEntity> roles=customerEntity.getRoles();
			//Here converting roles 
			List<GrantedAuthority> sroles=new ArrayList<GrantedAuthority>();
			for(RoleEntity roleEntity:roles){
				GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(roleEntity.getName());
				sroles.add(grantedAuthority);
			}
			user=new User(username, customerEntity.getPasssword(), sroles); //this just logged in customer roles
		}else{
			UsernameNotFoundException ex=new UsernameNotFoundException("Sorry user is not in database");
			throw ex;
		}
		return user;
	}

}
