package com.article.micro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.article.micro.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
