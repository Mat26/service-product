package mat.study.store.product.service;

import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.request.CategoryInDTO;

public interface CategoryService {
  Category createCategory(CategoryInDTO categoryInDTO);
  Category getCategoryByName(String name);
}
