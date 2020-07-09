package com.telros.interview.backend.service;

import com.telros.interview.backend.entities.Product;
import com.telros.interview.backend.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  @PostConstruct
  public void populateTestData() {
    if (productRepository.count() == 0) {
            List<Product> list = new ArrayList<>();
      list.add(new Product("ACAI BERRY", 100, "grows in South America"));
      list.add(new Product("AGARITA BERRY", 80, "grows in Texas,USA"));
      list.add(new Product("BANEBERRY", 50, "grows in North America"));
      list.add(new Product("BARBADOS CHERRY", 20, "grows in South America"));
      list.forEach(item -> productRepository.save(item));
    }
  }

}
