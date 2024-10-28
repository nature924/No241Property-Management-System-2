package com.boot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.boot.entity.Complains;

@Repository("complainsDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到ComplainsServiceImpl中
public interface ComplainsDAO {

	/**
	* ComplainsDAO 接口 可以按名称直接调用complains.xml配置文件的SQL语句
	*/

	// 插入意见反馈表数据 调用mapper包complains.xml里的insertComplains配置 返回值0(失败),1(成功)
	public int insertComplains(Complains complains);

	// 更新意见反馈表数据 调用mapper包complains.xml里的updateComplains配置 返回值0(失败),1(成功)
	public int updateComplains(Complains complains);

	// 按主键删除意见反馈表数据 调用mapper包complains.xml里的deleteComplains配置 返回值0(失败),1(成功)
	public int deleteComplains(String complainsid);

	// 批量删除意见反馈表数据 调用mapper包complains.xml里的deleteComplainsByIds配置 返回值0(失败),大于0(成功)
	public int deleteComplainsByIds(String[] ids);

	// 查询意见反馈表全部数据 调用mapper包complains.xml里的getAllComplains配置 返回List<Complains>类型的数据
	public List<Complains> getAllComplains();

	// 按照Complains类里面的值精确查询 调用mapper包complains.xml里的getComplainsByCond配置 返回List<Complains>类型的数据
	public List<Complains> getComplainsByCond(Complains complains);

	// 按照Complains类里面的值模糊查询 调用mapper包complains.xml里的getComplainsByLike配置 返回List<Complains>类型的数据
	public List<Complains> getComplainsByLike(Complains complains);

	// 按主键查询意见反馈表返回单一的Complains实例 调用mapper包complains.xml里的getComplainsById配置
	public Complains getComplainsById(String complainsid);

}


