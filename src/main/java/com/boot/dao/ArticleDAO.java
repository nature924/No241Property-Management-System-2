package com.boot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.boot.entity.Article;

@Repository("articleDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到ArticleServiceImpl中
public interface ArticleDAO {

	/**
	 * ArticleDAO 接口 可以按名称直接调用article.xml配置文件的SQL语句
	 */

	// 插入网站内容表数据 调用mapper包article.xml里的insertArticle配置 返回值0(失败),1(成功)
	public int insertArticle(Article article);

	// 更新网站内容表数据 调用mapper包article.xml里的updateArticle配置 返回值0(失败),1(成功)
	public int updateArticle(Article article);

	// 按主键删除网站内容表数据 调用mapper包article.xml里的deleteArticle配置 返回值0(失败),1(成功)
	public int deleteArticle(String articleid);

	// 批量删除网站内容表数据 调用mapper包article.xml里的deleteArticleByIds配置 返回值0(失败),大于0(成功)
	public int deleteArticleByIds(String[] ids);

	// 查询网站内容表全部数据 调用mapper包article.xml里的getAllArticle配置 返回List<Article>类型的数据
	public List<Article> getAllArticle();

	public List<Article> getFlvArticle();

	public List<Article> getTopArticle();

	public List<Article> getArticleByBanner(String bannerid);

	// 按照Article类里面的值精确查询 调用mapper包article.xml里的getArticleByCond配置
	// 返回List<Article>类型的数据
	public List<Article> getArticleByCond(Article article);

	// 按照Article类里面的值模糊查询 调用mapper包article.xml里的getArticleByLike配置
	// 返回List<Article>类型的数据
	public List<Article> getArticleByLike(Article article);

	// 按主键查询网站内容表返回单一的Article实例 调用mapper包article.xml里的getArticleById配置
	public Article getArticleById(String articleid);

}
