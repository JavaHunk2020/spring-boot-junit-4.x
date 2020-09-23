package com.cubicit;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.cubicit.dao.CustomerDaoRepository;
import com.cubicit.dao.LaptopRepository;
import com.cubicit.dao.RoleRepository;
import com.cubicit.dao.StudentRespository;
import com.cubicit.dao.UniversityRepository;
import com.cubicit.entity.CustomerEntity;
import com.cubicit.entity.LaptopEntity;
import com.cubicit.entity.RoleEntity;
import com.cubicit.entity.Student;
import com.cubicit.entity.University;

//@Service
@Transactional
public class DataPusher implements CommandLineRunner{
	
	@Autowired
	private LaptopRepository laptopRepository;
	
	@Autowired
	private CustomerDaoRepository customerDaoRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private StudentRespository studentRespository;
	
	@Autowired
	private UniversityRepository universityRepository;
	

	@Override
	public void run(String... args) throws Exception {
		
		/*CustomerEntity customerEntity=new CustomerEntity();
		
		customerEntity.setCid(4);*/
		//Loading data inside persistence context
		CustomerEntity customerEntity=customerDaoRepository.findById(2).get();
		RoleEntity admin=roleRepository.findById(1).get();
		RoleEntity employee=roleRepository.findById(2).get();
		
		Set<RoleEntity> roles=new HashSet<>();
		roles.add(admin);
		roles.add(employee);
		customerEntity.setRoles(roles);
		
		//All these values will come from UI
		LaptopEntity entity1=new LaptopEntity();
		entity1.setColor("orange");
		entity1.setDoe(new Timestamp(new Date().getTime()));
		entity1.setSize(12);
		entity1.setVendor("Accer");
		entity1.setCustomer(customerEntity);
		
		LaptopEntity entity2=new LaptopEntity();
		entity2.setColor("voilet");
		entity2.setDoe(new Timestamp(new Date().getTime()));
		entity2.setSize(17);
		entity2.setVendor("Samsung");
		entity2.setCustomer(customerEntity);
		
		laptopRepository.save(entity1);
		laptopRepository.save(entity2);
		System.out.println("saved!");
		
		
		Student student1 = new Student("Sam","Disilva","Maths");
        Student student2 = new Student("Joshua", "Brill", "Science");
        Student student3 = new Student("Peter", "Pan", "Physics");
         
        University university = new University("CAMBRIDGE", "ENGLAND");
 
        student1.setUniversity(university);
        student2.setUniversity(university);
        student3.setUniversity(university);
 
        //universityRepository.save(university);
        studentRespository.save(student1);
        studentRespository.save(student2);
        studentRespository.save(student3);
	}

}
