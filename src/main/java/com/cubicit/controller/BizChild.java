package com.cubicit.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BizChild  {
	private int bizid;
	private int porder;
	private int pid;

	public int getBizid() {
		return bizid;
	}

	public void setBizid(int bizid) {
		this.bizid = bizid;
	}

	public int getPorder() {
		return porder;
	}

	public void setPorder(int porder) {
		this.porder = porder;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

}
