package com.boot.entity;

import com.alibaba.fastjson.JSONObject;
import com.boot.util.VeDate;

// 停车位表的实体类
public class Parks {
	private String parksid = "P" + VeDate.getStringId(); // 生成主键编号
	private String pno; // 车位号
	private String address; // 位置
	private String square; // 车位面积
	private String status; // 状态
	private String usersid; // 业主
	private String memo; // 备注
	private String username; // 映射数据
	private Users users;// 多对一映射类

	public String getParksid() {
		return this.parksid;
	}

	public void setParksid(String parksid) {
		this.parksid = parksid;
	}

	public String getPno() {
		return this.pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSquare() {
		return this.square;
	}

	public void setSquare(String square) {
		this.square = square;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// 重载方法 生成JSON类型字符串
	@Override
	public String toString() {
		return this.toJsonString();
	}

	// 直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("parksid", this.parksid); // 主键编号
		jsonString.put("pno", this.pno); // 车位号
		jsonString.put("address", this.address); // 位置
		jsonString.put("square", this.square); // 车位面积
		jsonString.put("status", this.status); // 状态
		jsonString.put("usersid", this.usersid); // 业主
		jsonString.put("memo", this.memo); // 备注
		jsonString.put("Users", this.users); // 多对一映射类
		jsonString.put("address", this.address); // 映射数据
		return jsonString.toString();
	}

}
