package com.article.micro.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.article.micro.entities.Article;
import com.article.micro.entities.ArticleDTO;
import com.article.micro.models.ArticleSupplier;
import com.article.micro.models.Supplier;
import com.article.micro.repositories.ArticleRepository;

@Service
public class ArticleServiceImpl implements IArticleService {
	
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private RestTemplate restTemplate;
    
    
    @Override
    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        Article article = new Article(null, articleDTO.name(), articleDTO.price(), articleDTO.supplierId());
        Article savedArticle = articleRepository.save(article);
        return new ArticleDTO(savedArticle.getId(), savedArticle.getName(), savedArticle.getPrice(), savedArticle.getSupplierId());
    }
    
    @Override
    public ArticleDTO updateArticle(Long id, ArticleDTO articleDTO) {
        // Find the article by its ID, throw an exception if not found
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Article not found"));
        
        // Update only the fields that are not null in the DTO
        if (articleDTO.name() != null) {
            article.setName(articleDTO.name());
        }
        if (articleDTO.price() != 0) { // Assuming price 0 is not a valid price to keep unchanged
            article.setPrice(articleDTO.price());
        }
        if (articleDTO.supplierId() != null) {
            article.setSupplierId(articleDTO.supplierId());
        }

        // Save the updated article
        Article updatedArticle = articleRepository.save(article);
        
        // Return the updated ArticleDTO
        return new ArticleDTO(
            updatedArticle.getId(), 
            updatedArticle.getName(), 
            updatedArticle.getPrice(), 
            updatedArticle.getSupplierId()
        );
    }

    
    
    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
    
    
    @Override
    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(article -> new ArticleDTO(article.getId(), article.getName(), article.getPrice(), article.getSupplierId()))
                .collect(Collectors.toList());
    }
    
    
    @Override
    public ArticleDTO getArticleById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
        return new ArticleDTO(article.getId(), article.getName(), article.getPrice(), article.getSupplierId());
    }
    
        
    @Override
    public ArticleSupplier getArticleWithSupplier(Long articleId) {
        ArticleSupplier articleSupplier = new ArticleSupplier();
        
        
        // Find the article by its ID
        Article article = articleRepository.findById(articleId)
                                           .orElseThrow(() -> new RuntimeException("Article not found"));
        
        // Fetch the supplier information from the supplier microservice
        Supplier supplier = restTemplate.getForObject(
            "http://127.0.0.1:8001/api/suppliers/" + article.getSupplierId(),
            Supplier.class
        );
        
        // Set the article and supplier in the response model
        articleSupplier.setArticle(article);
        articleSupplier.setSupplier(supplier);
        
        return articleSupplier;
    }

    

}
