package com.article.micro.services;

import java.util.List;

import com.article.micro.entities.ArticleDTO;
import com.article.micro.models.ArticleSupplier;

public interface IArticleService {
	
    ArticleDTO createArticle(ArticleDTO articleDTO);
    
    ArticleDTO updateArticle(Long id, ArticleDTO articleDTO);
    
    void deleteArticle(Long id);
    
    List<ArticleDTO> getAllArticles();
    
    ArticleDTO getArticleById(Long id);
    
    ArticleSupplier getArticleWithSupplier(Long id);

}
