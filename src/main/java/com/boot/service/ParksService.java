package com.boot.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.boot.entity.Parks;
@Service("parksService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface ParksService {
	// 插入停车位表数据 调用parksDAO里的insertParks配置
	public int insertParks(Parks parks);

	// 更新停车位表数据 调用parksDAO里的updateParks配置
	public int updateParks(Parks parks);

	// 按主键删除停车位表数据 调用parksDAO里的deleteParks配置
	public int deleteParks(String parksid);

	// 批量删除停车位表数据 调用mapper包parks.xml里的deleteParksByIds配置 返回值0(失败),大于0(成功)
	public int deleteParksByIds(String[] ids);

	// 查询全部数据 调用parksDAO里的getAllParks配置
	public List<Parks> getAllParks();

	// 按照Parks类里面的字段名称精确查询 调用parksDAO里的getParksByCond配置
	public List<Parks> getParksByCond(Parks parks);

	// 按照Parks类里面的字段名称模糊查询 调用parksDAO里的getParksByLike配置
	public List<Parks> getParksByLike(Parks parks);

	// 按主键查询表返回单一的Parks实例 调用parksDAO里的getParksById配置
	public Parks getParksById(String parksid);

}

