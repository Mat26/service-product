package mat.study.store.product.controller;

import jakarta.validation.Valid;
import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.request.CategoryInDTO;
import mat.study.store.product.model.request.ProductInDTO;
import mat.study.store.product.service.CategoryService;
import mat.study.store.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/categories")
public class CategoryController {
  private final CategoryService categoryService;
  private final ProductService productService;

  @Autowired
  public CategoryController(CategoryService categoryService, ProductService productService) {
    this.categoryService = categoryService;
    this.productService = productService;
  }

  @GetMapping("/{id}")
  public Category getCategory(@PathVariable("id") Long id) {
    return categoryService.getCategory(id);
  }

  @GetMapping("/{id}/products")
  public List<Product> getProductsByCategory(@PathVariable("id") Long id) {
    return categoryService.getProductsByCategory(id);
  }

  @PostMapping
  public ResponseEntity<Category> createCategory
      (@Valid @RequestBody CategoryInDTO categoryInDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(categoryService.createCategory(categoryInDTO));
  }

  @PostMapping("/{id}")
  public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductInDTO productInDTO,
                                               @PathVariable("id") Long id) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(productService.createProduct(id, productInDTO));
  }

  @PatchMapping(value = "/{idCategory}/products/{idProduct}")
  public ResponseEntity<Void> updateProduct(@PathVariable("idCategory") Long idCategory,
                                            @PathVariable("idProduct") Long idProduct,
                                            @RequestBody ProductInDTO productInDTO) {
    productService.updateProduct(idCategory, idProduct, productInDTO);
    return ResponseEntity.noContent().build();
  }


}
