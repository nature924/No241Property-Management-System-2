package com.boot.entity;

import com.alibaba.fastjson.JSONObject;
import com.boot.util.VeDate;

// 楼栋表的实体类
public class Builds {
	private String buildsid = "B"+VeDate.getStringId(); // 生成主键编号
	private String buildsname; // 楼栋名称
	private String address; // 楼栋位置
	private String num; // 单元数
	private String floor; // 楼层数
	private String manager; // 负责人
	private String contact; // 联系方式
	private String memo; // 备注
	public String getBuildsid() {
		return this.buildsid;
	}

	public void setBuildsid(String buildsid) {
		this.buildsid = buildsid;
	}

	public String getBuildsname() {
		return this.buildsname;
	}

	public void setBuildsname(String buildsname) {
		this.buildsname = buildsname;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getFloor() {
		return this.floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


	// 重载方法 生成JSON类型字符串 
	@Override
	public String toString() {
		return this.toJsonString();
	}

	//直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("buildsid", this.buildsid); // 主键编号
		jsonString.put("buildsname", this.buildsname); // 楼栋名称
		jsonString.put("address", this.address); // 楼栋位置
		jsonString.put("num", this.num); // 单元数
		jsonString.put("floor", this.floor); // 楼层数
		jsonString.put("manager", this.manager); // 负责人
		jsonString.put("contact", this.contact); // 联系方式
		jsonString.put("memo", this.memo); // 备注
		return jsonString.toString();
	}




}




