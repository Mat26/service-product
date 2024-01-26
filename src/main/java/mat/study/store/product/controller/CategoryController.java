package mat.study.store.product.controller;

import mat.study.store.product.controller.api.CategoryApi;
import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.request.CategoryInDTO;
import mat.study.store.product.model.request.ProductInDTO;
import mat.study.store.product.service.CategoryService;
import mat.study.store.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {
  private final CategoryService categoryService;
  private final ProductService productService;

  @Autowired
  public CategoryController(CategoryService categoryService, ProductService productService) {
    this.categoryService = categoryService;
    this.productService = productService;
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

  @Override
  public ResponseEntity<Void> updateProduct(Long idCategory,
                                            Long idProduct,
                                            ProductInDTO productInDTO) {
    productService.updateProduct(idCategory, idProduct, productInDTO);
    return ResponseEntity.noContent().build();
  }


}
