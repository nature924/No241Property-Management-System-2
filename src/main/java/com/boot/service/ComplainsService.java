package com.boot.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.boot.entity.Complains;
@Service("complainsService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface ComplainsService {
	// 插入意见反馈表数据 调用complainsDAO里的insertComplains配置
	public int insertComplains(Complains complains);

	// 更新意见反馈表数据 调用complainsDAO里的updateComplains配置
	public int updateComplains(Complains complains);

	// 按主键删除意见反馈表数据 调用complainsDAO里的deleteComplains配置
	public int deleteComplains(String complainsid);

	// 批量删除意见反馈表数据 调用mapper包complains.xml里的deleteComplainsByIds配置 返回值0(失败),大于0(成功)
	public int deleteComplainsByIds(String[] ids);

	// 查询全部数据 调用complainsDAO里的getAllComplains配置
	public List<Complains> getAllComplains();

	// 按照Complains类里面的字段名称精确查询 调用complainsDAO里的getComplainsByCond配置
	public List<Complains> getComplainsByCond(Complains complains);

	// 按照Complains类里面的字段名称模糊查询 调用complainsDAO里的getComplainsByLike配置
	public List<Complains> getComplainsByLike(Complains complains);

	// 按主键查询表返回单一的Complains实例 调用complainsDAO里的getComplainsById配置
	public Complains getComplainsById(String complainsid);

}

