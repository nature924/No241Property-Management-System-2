package com.boot.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.UsersDAO;
import com.boot.entity.Users;
import com.boot.service.UsersService;

@Service("usersService") //
public class UsersServiceImpl implements UsersService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private UsersDAO usersDAO;
	@Override // 继承接口的新增小区业主表数据 返回值0(失败),1(成功)
	public int insertUsers(Users users) {
		return this.usersDAO.insertUsers(users);
	}

	@Override // 继承接口的更新小区业主表数据 返回值0(失败),1(成功)
	public int updateUsers(Users users) {
		return this.usersDAO.updateUsers(users);
	}

	@Override // 继承接口的按主键删除小区业主表数据 返回值0(失败),1(成功)
	public int deleteUsers(String usersid) {
		return this.usersDAO.deleteUsers(usersid);
	}

	@Override // 继承接口的批量删除小区业主表数据 返回值0(失败),大于0(成功)
	public int deleteUsersByIds(String[] ids) {
		return this.usersDAO.deleteUsersByIds(ids);
	}

	@Override // 继承接口的查询小区业主表全部数据
	public List<Users> getAllUsers() {
		return this.usersDAO.getAllUsers();
	}

	@Override // 继承接口的按条件精确查询小区业主表数据
	public List<Users> getUsersByCond(Users users) {
		return this.usersDAO.getUsersByCond(users);
	}

	@Override // 继承接口的按条件模糊查询小区业主表数据
	public List<Users> getUsersByLike(Users users) {
		return this.usersDAO.getUsersByLike(users);
	}

	@Override // 继承接口的按主键查询小区业主表数据 返回Entity实例
	public Users getUsersById(String usersid) {
		return this.usersDAO.getUsersById(usersid);
	}

}

