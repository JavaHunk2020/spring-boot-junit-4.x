package com.cubicit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cubicit.entity.AddressEntity;

public interface AddressDaoRepository extends JpaRepository<AddressEntity, Integer> {

}
