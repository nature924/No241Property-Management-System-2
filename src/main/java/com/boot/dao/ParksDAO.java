package com.boot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.boot.entity.Parks;

@Repository("parksDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到ParksServiceImpl中
public interface ParksDAO {

	/**
	* ParksDAO 接口 可以按名称直接调用parks.xml配置文件的SQL语句
	*/

	// 插入停车位表数据 调用mapper包parks.xml里的insertParks配置 返回值0(失败),1(成功)
	public int insertParks(Parks parks);

	// 更新停车位表数据 调用mapper包parks.xml里的updateParks配置 返回值0(失败),1(成功)
	public int updateParks(Parks parks);

	// 按主键删除停车位表数据 调用mapper包parks.xml里的deleteParks配置 返回值0(失败),1(成功)
	public int deleteParks(String parksid);

	// 批量删除停车位表数据 调用mapper包parks.xml里的deleteParksByIds配置 返回值0(失败),大于0(成功)
	public int deleteParksByIds(String[] ids);

	// 查询停车位表全部数据 调用mapper包parks.xml里的getAllParks配置 返回List<Parks>类型的数据
	public List<Parks> getAllParks();

	// 按照Parks类里面的值精确查询 调用mapper包parks.xml里的getParksByCond配置 返回List<Parks>类型的数据
	public List<Parks> getParksByCond(Parks parks);

	// 按照Parks类里面的值模糊查询 调用mapper包parks.xml里的getParksByLike配置 返回List<Parks>类型的数据
	public List<Parks> getParksByLike(Parks parks);

	// 按主键查询停车位表返回单一的Parks实例 调用mapper包parks.xml里的getParksById配置
	public Parks getParksById(String parksid);

}


