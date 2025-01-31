package com.article.micro.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.article.micro.entities.ArticleDTO;
import com.article.micro.models.ArticleSupplier;
import com.article.micro.services.IArticleService;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
	
    @Autowired
    private IArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {
        ArticleDTO createdArticle = articleService.createArticle(articleDTO);
        return ResponseEntity.ok(createdArticle);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
        ArticleDTO updatedArticle = articleService.updateArticle(id, articleDTO);
        return ResponseEntity.ok(updatedArticle);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<ArticleDTO> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
        ArticleDTO articleDTO = articleService.getArticleById(id);
        return ResponseEntity.ok(articleDTO);
    }
    
    
    //@GetMapping("/{id}/supplier")
    //public ResponseEntity<ArticleSupplier> getArticleWithSupplier(@PathVariable Long id) {
      //  ArticleSupplier articleSupplier = articleService.getArticleWithSupplier(id);
        //return ResponseEntity.ok(articleSupplier);
    //}
    
    @GetMapping("/{id}/with-supplier")
    public ResponseEntity<ArticleSupplier> getArticleWithSupplier(@PathVariable Long id) {
        ArticleSupplier articleSupplier = articleService.getArticleWithSupplier(id);
        return ResponseEntity.ok(articleSupplier);
    }

}
