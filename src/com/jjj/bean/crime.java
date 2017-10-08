package com.jjj.bean;

public class crime {
	private int id;

	private String kind;

	private String department;
	private String startdata;
	private String desc;
	private String province;
	public int getId() {
		return id;
	}
	public String getDesc() {
		return desc;
	}
	
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getStartdata() {
		return startdata;
	}
	public void setStartdata(String startdata) {
		this.startdata = startdata;
	}
	
}
