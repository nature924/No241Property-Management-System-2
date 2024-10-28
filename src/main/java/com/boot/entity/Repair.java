package com.boot.entity;

import com.alibaba.fastjson.JSONObject;
import com.boot.util.VeDate;

// 业主报修表的实体类
public class Repair {
	private String repairid = "R"+VeDate.getStringId(); // 生成主键编号
	private String rno; // 维修单号
	private String usersid; // 报修业主
	private String reason; // 报修原因
	private String contents; // 故障描述
	private String addtime; // 报修日期
	private String status; // 状态
	private String employ; // 维修人
	private String memo; // 备注
	private String username; // 映射数据
	private Users users;// 多对一映射类
	public String getRepairid() {
		return this.repairid;
	}

	public void setRepairid(String repairid) {
		this.repairid = repairid;
	}

	public String getRno() {
		return this.rno;
	}

	public void setRno(String rno) {
		this.rno = rno;
	}

	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmploy() {
		return this.employ;
	}

	public void setEmploy(String employ) {
		this.employ = employ;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	// 重载方法 生成JSON类型字符串 
	@Override
	public String toString() {
		return this.toJsonString();
	}

	//直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("repairid", this.repairid); // 主键编号
		jsonString.put("rno", this.rno); // 维修单号
		jsonString.put("usersid", this.usersid); // 报修业主
		jsonString.put("reason", this.reason); // 报修原因
		jsonString.put("contents", this.contents); // 故障描述
		jsonString.put("addtime", this.addtime); // 报修日期
		jsonString.put("status", this.status); // 状态
		jsonString.put("employ", this.employ); // 维修人
		jsonString.put("memo", this.memo); // 备注
		jsonString.put("Users", this.users); // 多对一映射类
		jsonString.put("username", this.username); // 映射数据
		return jsonString.toString();
	}




}




