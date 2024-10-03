package com.article.micro.models;

import com.article.micro.entities.Article;

public class ArticleSupplier {

	private Article article;
	private Supplier supplier;

	public ArticleSupplier() {}

	public ArticleSupplier(Article article, Supplier supplier) {

		this.article = article;
		this.supplier = supplier;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
