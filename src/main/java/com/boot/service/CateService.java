package com.boot.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.boot.entity.Cate;
@Service("cateService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface CateService {
	// 插入收费类型表数据 调用cateDAO里的insertCate配置
	public int insertCate(Cate cate);

	// 更新收费类型表数据 调用cateDAO里的updateCate配置
	public int updateCate(Cate cate);

	// 按主键删除收费类型表数据 调用cateDAO里的deleteCate配置
	public int deleteCate(String cateid);

	// 批量删除收费类型表数据 调用mapper包cate.xml里的deleteCateByIds配置 返回值0(失败),大于0(成功)
	public int deleteCateByIds(String[] ids);

	// 查询全部数据 调用cateDAO里的getAllCate配置
	public List<Cate> getAllCate();

	public List<Cate> getCateFront();
	// 按照Cate类里面的字段名称精确查询 调用cateDAO里的getCateByCond配置
	public List<Cate> getCateByCond(Cate cate);

	// 按照Cate类里面的字段名称模糊查询 调用cateDAO里的getCateByLike配置
	public List<Cate> getCateByLike(Cate cate);

	// 按主键查询表返回单一的Cate实例 调用cateDAO里的getCateById配置
	public Cate getCateById(String cateid);

}

