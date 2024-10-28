package com.boot.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.ArticleDAO;
import com.boot.entity.Article;
import com.boot.service.ArticleService;

@Service("articleService") //
public class ArticleServiceImpl implements ArticleService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private ArticleDAO articleDAO;

	@Override // 继承接口的新增网站内容表数据 返回值0(失败),1(成功)
	public int insertArticle(Article article) {
		return this.articleDAO.insertArticle(article);
	}

	@Override // 继承接口的更新网站内容表数据 返回值0(失败),1(成功)
	public int updateArticle(Article article) {
		return this.articleDAO.updateArticle(article);
	}

	@Override // 继承接口的按主键删除网站内容表数据 返回值0(失败),1(成功)
	public int deleteArticle(String articleid) {
		return this.articleDAO.deleteArticle(articleid);
	}

	@Override // 继承接口的批量删除网站内容表数据 返回值0(失败),大于0(成功)
	public int deleteArticleByIds(String[] ids) {
		return this.articleDAO.deleteArticleByIds(ids);
	}

	@Override // 继承接口的查询网站内容表全部数据
	public List<Article> getAllArticle() {
		return this.articleDAO.getAllArticle();
	}

	@Override
	public List<Article> getFlvArticle() {
		return this.articleDAO.getFlvArticle();
	}

	@Override
	public List<Article> getTopArticle() {
		return this.articleDAO.getTopArticle();
	}

	@Override
	public List<Article> getArticleByBanner(String bannerid) {
		return this.articleDAO.getArticleByBanner(bannerid);
	}

	@Override // 继承接口的按条件精确查询网站内容表数据
	public List<Article> getArticleByCond(Article article) {
		return this.articleDAO.getArticleByCond(article);
	}

	@Override // 继承接口的按条件模糊查询网站内容表数据
	public List<Article> getArticleByLike(Article article) {
		return this.articleDAO.getArticleByLike(article);
	}

	@Override // 继承接口的按主键查询网站内容表数据 返回Entity实例
	public Article getArticleById(String articleid) {
		return this.articleDAO.getArticleById(articleid);
	}

}
