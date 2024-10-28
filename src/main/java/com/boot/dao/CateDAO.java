package com.boot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.boot.entity.Cate;

@Repository("cateDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到CateServiceImpl中
public interface CateDAO {

	/**
	* CateDAO 接口 可以按名称直接调用cate.xml配置文件的SQL语句
	*/

	// 插入收费类型表数据 调用mapper包cate.xml里的insertCate配置 返回值0(失败),1(成功)
	public int insertCate(Cate cate);

	// 更新收费类型表数据 调用mapper包cate.xml里的updateCate配置 返回值0(失败),1(成功)
	public int updateCate(Cate cate);

	// 按主键删除收费类型表数据 调用mapper包cate.xml里的deleteCate配置 返回值0(失败),1(成功)
	public int deleteCate(String cateid);

	// 批量删除收费类型表数据 调用mapper包cate.xml里的deleteCateByIds配置 返回值0(失败),大于0(成功)
	public int deleteCateByIds(String[] ids);

	// 查询收费类型表全部数据 调用mapper包cate.xml里的getAllCate配置 返回List<Cate>类型的数据
	public List<Cate> getAllCate();

	// 查询N个商品类型信息 首页显示
	public List<Cate> getCateFront();

	// 按照Cate类里面的值精确查询 调用mapper包cate.xml里的getCateByCond配置 返回List<Cate>类型的数据
	public List<Cate> getCateByCond(Cate cate);

	// 按照Cate类里面的值模糊查询 调用mapper包cate.xml里的getCateByLike配置 返回List<Cate>类型的数据
	public List<Cate> getCateByLike(Cate cate);

	// 按主键查询收费类型表返回单一的Cate实例 调用mapper包cate.xml里的getCateById配置
	public Cate getCateById(String cateid);

}


