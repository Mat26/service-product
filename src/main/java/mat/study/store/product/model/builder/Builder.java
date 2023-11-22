package mat.study.store.product.model.builder;

import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.enums.ProductStatus;

import java.util.Date;

public interface Builder {

  void setName(String name);
  void setDescription(String description);
  void setStock(Double stock);
  void setPrice(Double price);
  void setStatus(ProductStatus status);
  void setCreateAt(Date createAt);
  void setCategory(Category category);

}
