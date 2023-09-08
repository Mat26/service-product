package mat.study.store.product.controller;

import jakarta.validation.Valid;
import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.request.CategoryInDTO;
import mat.study.store.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/categories")
public class CategoryController {
  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping
  public ResponseEntity<Category> createCategory
      (@Valid @RequestBody CategoryInDTO categoryInDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(categoryService.createCategory(categoryInDTO));
  }

  //El producto puede modificar la categoria, pero la Categoria puede modificarse, para luego modificarse para cada producto?
}
