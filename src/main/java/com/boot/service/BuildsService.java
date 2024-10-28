package com.boot.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.boot.entity.Builds;
@Service("buildsService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface BuildsService {
	// 插入楼栋表数据 调用buildsDAO里的insertBuilds配置
	public int insertBuilds(Builds builds);

	// 更新楼栋表数据 调用buildsDAO里的updateBuilds配置
	public int updateBuilds(Builds builds);

	// 按主键删除楼栋表数据 调用buildsDAO里的deleteBuilds配置
	public int deleteBuilds(String buildsid);

	// 批量删除楼栋表数据 调用mapper包builds.xml里的deleteBuildsByIds配置 返回值0(失败),大于0(成功)
	public int deleteBuildsByIds(String[] ids);

	// 查询全部数据 调用buildsDAO里的getAllBuilds配置
	public List<Builds> getAllBuilds();

	// 按照Builds类里面的字段名称精确查询 调用buildsDAO里的getBuildsByCond配置
	public List<Builds> getBuildsByCond(Builds builds);

	// 按照Builds类里面的字段名称模糊查询 调用buildsDAO里的getBuildsByLike配置
	public List<Builds> getBuildsByLike(Builds builds);

	// 按主键查询表返回单一的Builds实例 调用buildsDAO里的getBuildsById配置
	public Builds getBuildsById(String buildsid);

}

