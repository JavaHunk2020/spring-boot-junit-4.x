package com.cubicit.dao;

import java.util.List;

import com.cubicit.controller.vo.CustomerVO;

public interface CustomerDao {

	String save(CustomerVO customerVO);

	List<CustomerVO> findAll();

	CustomerVO findByCid(int cid);

}
