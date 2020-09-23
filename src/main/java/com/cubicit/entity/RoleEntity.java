package com.cubicit.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "roles_tbl")
public class RoleEntity {
	private int rid;
	private String name;
	private String description;
	
	private Set<CustomerEntity> customers;
	
	public RoleEntity() {}
		
	public RoleEntity(int rid, String name, String description) {
		super();
		this.rid = rid;
		this.name = name;
		this.description = description;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "customer_roles", joinColumns = @JoinColumn(name = "rid"), inverseJoinColumns = @JoinColumn(name = "cid"))
	public Set<CustomerEntity> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<CustomerEntity> customers) {
		this.customers = customers;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	@Column(length=30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleEntity other = (RoleEntity) obj;
		if (rid != other.rid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoleEntity [rid=" + rid + ", name=" + name + ", description=" + description + "]";
	}

}
