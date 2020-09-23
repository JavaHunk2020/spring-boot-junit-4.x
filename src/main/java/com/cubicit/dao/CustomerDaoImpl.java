package com.cubicit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cubicit.controller.vo.CustomerVO;

@Repository
public class CustomerDaoImpl implements  CustomerDao{
	
	@Autowired // ByType, @Qualifier ,@ByName
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String save(CustomerVO customerVO){
		String sql="insert into customers_tbl(name,email,debitcard,valid,cvv,mobile,age,company) values(?,?,?,?,?,?,?,?)";
		Object[] data={customerVO.getName(),customerVO.getEmail(),customerVO.getDebitcard(),customerVO.getValid(),customerVO.getCvv(),
				customerVO.getMobile(),customerVO.getAge(),customerVO.getCompany()};
		jdbcTemplate.update(sql,data);
		return "saved";
	}
	
	@Override
	public List<CustomerVO> findAll(){
		String sql="select cid,name,email,debitcard,valid,cvv,mobile,age,company from customers_tbl";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper(CustomerVO.class));
	}
	
	@Override
	public CustomerVO findByCid(int cid){
		String sql="select cid,name,email,debitcard,valid,cvv,mobile,age,company from customers_tbl where cid = "+cid;
		return (CustomerVO)jdbcTemplate.query(sql,new BeanPropertyRowMapper(CustomerVO.class)).get(0);
	}

}
