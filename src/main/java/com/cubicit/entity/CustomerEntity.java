package com.cubicit.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity //means this class or object can be saved into the database
@Table(name="customers_tbl")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cid;
	
	@Column(length=100)
	private String name;
	
	@Column(length=100)
	private String email;
	
	@Column(length=20)
	private String debitcard;
	
	@Column(length=10)
	private String valid;
	
	@Column(length=3)
	private int cvv;
	
	@Column(length=16)
	private String mobile;
	
	@Column(columnDefinition="longblob")
	private byte[] photo;
	
	@Column(columnDefinition="longblob")
	private byte[] dbphoto;

	@Column(length=11)
	private int age;
	
	@Column(length=100)
	private String company;
	
	@Column(length=100)
	private String passsword;
	
	
	
	
	public String getPasssword() {
		return passsword;
	}

	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}


	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL) // login is an attribute present inside CustomerQuestionAnswer entity
	private List<LaptopEntity> laptops;
	
	//mappedBy means , hey customerEntity does not have foreign key
	@OneToOne(cascade = CascadeType.ALL,mappedBy="customerEntity")
	private AddressEntity addressEntity;
	
	@ManyToMany
	@JoinTable(name = "customer_roles", joinColumns = @JoinColumn(name = "cid"), inverseJoinColumns = @JoinColumn(name = "rid"))
	private Set<RoleEntity> roles;
	
	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}
	

	
	
	public List<LaptopEntity> getLaptops() {
		return laptops;
	}

	public void setLaptops(List<LaptopEntity> laptops) {
		this.laptops = laptops;
	}
	

	public AddressEntity getAddressEntity() {
		return addressEntity;
	}

	public void setAddressEntity(AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
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
