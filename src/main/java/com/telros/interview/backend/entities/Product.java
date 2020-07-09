package com.telros.interview.backend.entities;

import java.util.Objects;
import javax.persistence.Entity;

@Entity
public class Product extends AbstractEntity implements Cloneable {

  private String name;
  private int price;
  private String description;

  public Product() {
  }

  public Product(String name, int price, String description) {
    this.name = name;
    this.price = price;
    this.description = description;
  }



  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Product{" +
        "name='" + name + '\'' +
        ", price=" + price +
        ", description='" + description + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Product product = (Product) o;
    return price == product.price &&
        Objects.equals(name, product.name) &&
        Objects.equals(description, product.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, price, description);
  }
}