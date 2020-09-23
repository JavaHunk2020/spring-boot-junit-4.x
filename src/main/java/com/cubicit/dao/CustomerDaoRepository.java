package com.cubicit.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cubicit.entity.CustomerEntity;

public interface CustomerDaoRepository extends JpaRepository<CustomerEntity, Integer> {
	
	public Optional<CustomerEntity> findByEmail(String email);
	public Optional<CustomerEntity> findByMobile(String mobile);
	
}
