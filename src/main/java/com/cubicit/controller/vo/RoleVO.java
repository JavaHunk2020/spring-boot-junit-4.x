package com.cubicit.controller.vo;

public class RoleVO {

	private int rid;
	private String name;
	private String description;

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RoleVO [rid=" + rid + ", name=" + name + ", description=" + description + "]";
	}

}
