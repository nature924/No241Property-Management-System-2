package com.boot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.boot.entity.Users;

@Repository("usersDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到UsersServiceImpl中
public interface UsersDAO {

	/**
	* UsersDAO 接口 可以按名称直接调用users.xml配置文件的SQL语句
	*/

	// 插入小区业主表数据 调用mapper包users.xml里的insertUsers配置 返回值0(失败),1(成功)
	public int insertUsers(Users users);

	// 更新小区业主表数据 调用mapper包users.xml里的updateUsers配置 返回值0(失败),1(成功)
	public int updateUsers(Users users);

	// 按主键删除小区业主表数据 调用mapper包users.xml里的deleteUsers配置 返回值0(失败),1(成功)
	public int deleteUsers(String usersid);

	// 批量删除小区业主表数据 调用mapper包users.xml里的deleteUsersByIds配置 返回值0(失败),大于0(成功)
	public int deleteUsersByIds(String[] ids);

	// 查询小区业主表全部数据 调用mapper包users.xml里的getAllUsers配置 返回List<Users>类型的数据
	public List<Users> getAllUsers();

	// 按照Users类里面的值精确查询 调用mapper包users.xml里的getUsersByCond配置 返回List<Users>类型的数据
	public List<Users> getUsersByCond(Users users);

	// 按照Users类里面的值模糊查询 调用mapper包users.xml里的getUsersByLike配置 返回List<Users>类型的数据
	public List<Users> getUsersByLike(Users users);

	// 按主键查询小区业主表返回单一的Users实例 调用mapper包users.xml里的getUsersById配置
	public Users getUsersById(String usersid);

}


