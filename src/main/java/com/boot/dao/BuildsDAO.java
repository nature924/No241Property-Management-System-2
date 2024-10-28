package com.boot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.boot.entity.Builds;

@Repository("buildsDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到BuildsServiceImpl中
public interface BuildsDAO {

	/**
	* BuildsDAO 接口 可以按名称直接调用builds.xml配置文件的SQL语句
	*/

	// 插入楼栋表数据 调用mapper包builds.xml里的insertBuilds配置 返回值0(失败),1(成功)
	public int insertBuilds(Builds builds);

	// 更新楼栋表数据 调用mapper包builds.xml里的updateBuilds配置 返回值0(失败),1(成功)
	public int updateBuilds(Builds builds);

	// 按主键删除楼栋表数据 调用mapper包builds.xml里的deleteBuilds配置 返回值0(失败),1(成功)
	public int deleteBuilds(String buildsid);

	// 批量删除楼栋表数据 调用mapper包builds.xml里的deleteBuildsByIds配置 返回值0(失败),大于0(成功)
	public int deleteBuildsByIds(String[] ids);

	// 查询楼栋表全部数据 调用mapper包builds.xml里的getAllBuilds配置 返回List<Builds>类型的数据
	public List<Builds> getAllBuilds();

	// 按照Builds类里面的值精确查询 调用mapper包builds.xml里的getBuildsByCond配置 返回List<Builds>类型的数据
	public List<Builds> getBuildsByCond(Builds builds);

	// 按照Builds类里面的值模糊查询 调用mapper包builds.xml里的getBuildsByLike配置 返回List<Builds>类型的数据
	public List<Builds> getBuildsByLike(Builds builds);

	// 按主键查询楼栋表返回单一的Builds实例 调用mapper包builds.xml里的getBuildsById配置
	public Builds getBuildsById(String buildsid);

}


