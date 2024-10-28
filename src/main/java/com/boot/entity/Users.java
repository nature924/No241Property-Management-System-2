package com.boot.entity;

import com.alibaba.fastjson.JSONObject;
import com.boot.util.VeDate;

// 小区业主表的实体类
public class Users {
	private String usersid = "U"+VeDate.getStringId(); // 生成主键编号
	private String username; // 用户名
	private String password; // 密码
	private String realname; // 姓名
	private String sex; // 性别
	private String birthday; // 出生日期
	private String buildsid; // 楼栋
	private String address; // 地址
	private String contact; // 联系方式
	private String addtime; // 创建日期
	private String memo; // 备注
	private String buildsname; // 映射数据
	private Builds builds;// 多对一映射类
	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBuildsid() {
		return this.buildsid;
	}

	public void setBuildsid(String buildsid) {
		this.buildsid = buildsid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Builds getBuilds() {
		return this.builds;
	}

	public void setBuilds(Builds builds) {
		this.builds = builds;
	}

	public String getBuildsname() {
		return this.buildsname;
	}

	public void setBuildsname(String buildsname) {
		this.buildsname = buildsname;
	}


	// 重载方法 生成JSON类型字符串 
	@Override
	public String toString() {
		return this.toJsonString();
	}

	//直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("usersid", this.usersid); // 主键编号
		jsonString.put("username", this.username); // 用户名
		jsonString.put("password", this.password); // 密码
		jsonString.put("realname", this.realname); // 姓名
		jsonString.put("sex", this.sex); // 性别
		jsonString.put("birthday", this.birthday); // 出生日期
		jsonString.put("buildsid", this.buildsid); // 楼栋
		jsonString.put("address", this.address); // 地址
		jsonString.put("contact", this.contact); // 联系方式
		jsonString.put("addtime", this.addtime); // 创建日期
		jsonString.put("memo", this.memo); // 备注
		jsonString.put("Builds", this.builds); // 多对一映射类
		jsonString.put("buildsname", this.buildsname); // 映射数据
		return jsonString.toString();
	}




}




