package mat.study.store.product.controller;

import mat.study.store.product.controller.api.CategoryApi;
import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.request.CategoryInDTO;
import mat.study.store.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {
  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  public Category getCategory(Long id) {
    return categoryService.getCategory(id);
  }

  @Override
  public List<Product> getProductsByCategory(Long id) {
    return categoryService.getProductsByCategory(id);
  }

  @Override
  public ResponseEntity<Category> createCategory
      (CategoryInDTO categoryInDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(categoryService.createCategory(categoryInDTO));
  }

}
