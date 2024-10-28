package com.boot.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.BuildsDAO;
import com.boot.entity.Builds;
import com.boot.service.BuildsService;

@Service("buildsService") //
public class BuildsServiceImpl implements BuildsService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private BuildsDAO buildsDAO;
	@Override // 继承接口的新增楼栋表数据 返回值0(失败),1(成功)
	public int insertBuilds(Builds builds) {
		return this.buildsDAO.insertBuilds(builds);
	}

	@Override // 继承接口的更新楼栋表数据 返回值0(失败),1(成功)
	public int updateBuilds(Builds builds) {
		return this.buildsDAO.updateBuilds(builds);
	}

	@Override // 继承接口的按主键删除楼栋表数据 返回值0(失败),1(成功)
	public int deleteBuilds(String buildsid) {
		return this.buildsDAO.deleteBuilds(buildsid);
	}

	@Override // 继承接口的批量删除楼栋表数据 返回值0(失败),大于0(成功)
	public int deleteBuildsByIds(String[] ids) {
		return this.buildsDAO.deleteBuildsByIds(ids);
	}

	@Override // 继承接口的查询楼栋表全部数据
	public List<Builds> getAllBuilds() {
		return this.buildsDAO.getAllBuilds();
	}

	@Override // 继承接口的按条件精确查询楼栋表数据
	public List<Builds> getBuildsByCond(Builds builds) {
		return this.buildsDAO.getBuildsByCond(builds);
	}

	@Override // 继承接口的按条件模糊查询楼栋表数据
	public List<Builds> getBuildsByLike(Builds builds) {
		return this.buildsDAO.getBuildsByLike(builds);
	}

	@Override // 继承接口的按主键查询楼栋表数据 返回Entity实例
	public Builds getBuildsById(String buildsid) {
		return this.buildsDAO.getBuildsById(buildsid);
	}

}

