package mat.study.store.product.service;

import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.request.CategoryInDTO;

import java.util.List;

public interface CategoryService {
  Category getCategory(Long id);
  
  Category createCategory(CategoryInDTO categoryInDTO);

  Category getCategoryByName(String name);

  List<Product> getProductsByCategory(Long id);
}
