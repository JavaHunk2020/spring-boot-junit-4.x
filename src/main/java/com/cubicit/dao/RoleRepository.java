package com.cubicit.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cubicit.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

	public Optional<RoleEntity> findByName(String name);
	
}
