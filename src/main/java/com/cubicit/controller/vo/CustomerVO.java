package com.cubicit.controller.vo;

import java.util.Arrays;

public class CustomerVO {

	private int cid;
	private String name;
	private String email;
	private String debitcard;
	private String valid;
	private int cvv;
	private String mobile;
	private byte[] photo;
	private byte[] dbphoto;

	private int age;
	private String company;
	
	private String street;
	private String state;
	

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDebitcard() {
		return debitcard;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public void setDebitcard(String debitcard) {
		this.debitcard = debitcard;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public byte[] getDbphoto() {
		return dbphoto;
	}

	public void setDbphoto(byte[] dbphoto) {
		this.dbphoto = dbphoto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "CustomerVO [name=" + name + ", email=" + email + ", valid=" + valid + ", cvv=" + cvv + ", mobile="
				+ mobile + ", dbphoto=" + Arrays.toString(dbphoto) + ", age=" + age + ", company=" + company + "]";
	}

}
