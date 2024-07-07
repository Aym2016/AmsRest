package com.sip.ams.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sip.ams.entities.*;
import your.package.ArticleRepository;
import your.package.TestDataSourceConfiguration;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ArticleRepositoryTest {

@Autowired
private TestEntityManager entityManager;

@Autowired
private ArticleRepository repository;

@Test
public void findAll_ReturnsAllArticle() {

reasonml
Copier
// Arrange
entityManager.persist(new Article("A1"));
entityManager.persist(new Article("A2"));  

// Act
List<Article> articles = repository.findAll();

// Assert
assertEquals(2, articles.size());
}

}