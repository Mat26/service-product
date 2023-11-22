package mat.study.store.product.model.builder;

import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.enums.ProductStatus;

import java.util.Date;

public class ProductBuilder implements Builder{

  private String name;
  private String description;
  private Double stock;
  private Double price;
  private ProductStatus status;
  private Date createAt;
  private Category category;


  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public void setStock(Double stock) {
    this.stock =stock;
  }

  @Override
  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public void setStatus(ProductStatus status) {
    this.status = status;
  }

  @Override
  public void setCreateAt(Date createAt) {
    this.createAt = createAt;
  }

  @Override
  public void setCategory(Category category) {
    this.category = category;
  }

  public Product getProduct() {
    return new Product();
  }
}
