package mat.study.store.product.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import mat.study.store.product.model.enums.ProductStatus;

import java.util.Date;

@Entity
@Table(name = "products")
@Builder
@NamedEntityGraph(name = "Product.category",
    attributeNodes = @NamedAttributeNode("category"))
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "Name may not be empty")
  private String name;
  private String description;

  @Min(value = 0, message = "Stock should not be less than 0")
  private Double stock = 0.0;
  private Double price;

  @Enumerated(EnumType.STRING)
  private ProductStatus status;

  @Column(name = "create_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createAt;

  @NotNull(message = "Category may not be empty")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  private Category category;

  public Product() {
  }

  public Product(Long id, String name, String description, Double stock, Double price, ProductStatus status, Date createAt, Category category) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.stock = stock;
    this.price = price;
    this.status = status;
    this.createAt = createAt;
    this.category = category;
  }

  public Product(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.description = builder.description;
    this.stock = builder.stock;
    this.price = builder.price;
    this.status = builder.status;
    this.createAt = builder.createAt;
    this.category = builder.category;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStock(Double stock) {
    this.stock = stock;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public void setStatus(ProductStatus status) {
    this.status = status;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Double getStock() {
    return stock;
  }

  public Double getPrice() {
    return price;
  }

  public ProductStatus getStatus() {
    return status;
  }

  public Date getCreateAt() {
    return createAt;
  }

  public Category getCategory() {
    return category;
  }

  public static class Builder {
    private Long id;
    private String name;
    private String description;
    private Double stock;
    private Double price;
    private ProductStatus status;
    private Date createAt;
    private Category category;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder stock(Double stock) {
      this.stock = stock;
      return this;
    }

    public Builder price(Double price) {
      this.price = price;
      return this;
    }

    public Builder status(ProductStatus status) {
      this.status = status;
      return this;
    }

    public Builder createAt(Date createAt) {
      this.createAt = createAt;
      return this;
    }

    public Builder category(Category category) {
      this.category = category;
      return this;
    }

    public Product build() {
      return new Product(this);
    }

  }

}
