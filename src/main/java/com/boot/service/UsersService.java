package com.boot.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.boot.entity.Users;
@Service("usersService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface UsersService {
	// 插入小区业主表数据 调用usersDAO里的insertUsers配置
	public int insertUsers(Users users);

	// 更新小区业主表数据 调用usersDAO里的updateUsers配置
	public int updateUsers(Users users);

	// 按主键删除小区业主表数据 调用usersDAO里的deleteUsers配置
	public int deleteUsers(String usersid);

	// 批量删除小区业主表数据 调用mapper包users.xml里的deleteUsersByIds配置 返回值0(失败),大于0(成功)
	public int deleteUsersByIds(String[] ids);

	// 查询全部数据 调用usersDAO里的getAllUsers配置
	public List<Users> getAllUsers();

	// 按照Users类里面的字段名称精确查询 调用usersDAO里的getUsersByCond配置
	public List<Users> getUsersByCond(Users users);

	// 按照Users类里面的字段名称模糊查询 调用usersDAO里的getUsersByLike配置
	public List<Users> getUsersByLike(Users users);

	// 按主键查询表返回单一的Users实例 调用usersDAO里的getUsersById配置
	public Users getUsersById(String usersid);

}

