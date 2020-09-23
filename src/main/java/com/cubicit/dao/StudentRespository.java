package com.cubicit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cubicit.entity.Student;

public interface StudentRespository extends JpaRepository<Student, Long>{ 

}
