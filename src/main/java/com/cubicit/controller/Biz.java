package com.cubicit.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Biz implements Serializable {
	private int id;
	private String name;
	private String brand;
	private Timestamp doe;
	//This is used store file
	private MultipartFile[] file;
	
	private List<Integer> cimageIds;
	
	public List<Integer> getCimageIds() {
		return cimageIds;
	}

	public void setCimageIds(List<Integer> cimageIds) {
		this.cimageIds = cimageIds;
	}

	public MultipartFile[] getFile() {
		return file;
	}

	public void setFile(MultipartFile[] file) {
		this.file = file;
	}

	public Biz(){
		
	}

	public Biz(int id, String name, String brand, Timestamp doe) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.doe = doe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Timestamp getDoe() {
		return doe;
	}

	public void setDoe(Timestamp doe) {
		this.doe = doe;
	}

	@Override
	public String toString() {
		return "Biz [id=" + id + ", name=" + name + ", brand=" + brand + ", doe=" + doe + "]";
	}

}
