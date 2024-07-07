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
public class ProviderRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private ProviderRepository repository;

  @Test
  public void findAll_ReturnsAllProvider() {
    // Arrange
    entityManager.persist(new Provider("P1")); 
    entityManager.persist(new Provider("P1"));

    // Act
    List<Provider> Provider = repository.findAll();

    // Assert
    assertEquals(2, Provider.size());
  }

}