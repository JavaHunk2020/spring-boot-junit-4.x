package com.cubicit.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity //means this class or object can be saved into the database
@Table(name="address_tbl")
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int aid;
	
	@Column(length=100)
	private String stree;
	
	@Column(length=20)
	private String state;
	
	@Column(length=50)
	private String country;
	
	@Column(length=8)
	private int pincode;
	
	private Timestamp doe;
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
	private CustomerEntity customerEntity;
	

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getStree() {
		return stree;
	}

	public void setStree(String stree) {
		this.stree = stree;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Timestamp getDoe() {
		return doe;
	}

	public void setDoe(Timestamp doe) {
		this.doe = doe;
	}

	@Override
	public String toString() {
		return "AddressEntity [aid=" + aid + ", stree=" + stree + ", state=" + state + ", country=" + country
				+ ", pincode=" + pincode + ", doe=" + doe + "]";
	}

}
