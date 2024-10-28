package com.boot.entity;

import com.alibaba.fastjson.JSONObject;
import com.boot.util.VeDate;

// 意见反馈表的实体类
public class Complains {
	private String complainsid = "C"+VeDate.getStringId(); // 生成主键编号
	private String usersid; // 业主用户
	private String title; // 标题
	private String contents; // 内容
	private String addtime; // 日期
	private String status; // 状态
	private String reps; // 回复内容
	private String username; // 映射数据
	private Users users;// 多对一映射类
	public String getComplainsid() {
		return this.complainsid;
	}

	public void setComplainsid(String complainsid) {
		this.complainsid = complainsid;
	}

	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getReps() {
		return this.reps;
	}

	public void setReps(String reps) {
		this.reps = reps;
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
		jsonString.put("complainsid", this.complainsid); // 主键编号
		jsonString.put("usersid", this.usersid); // 业主用户
		jsonString.put("title", this.title); // 标题
		jsonString.put("contents", this.contents); // 内容
		jsonString.put("addtime", this.addtime); // 日期
		jsonString.put("status", this.status); // 状态
		jsonString.put("reps", this.reps); // 回复内容
		jsonString.put("Users", this.users); // 多对一映射类
		jsonString.put("username", this.username); // 映射数据
		return jsonString.toString();
	}




}




