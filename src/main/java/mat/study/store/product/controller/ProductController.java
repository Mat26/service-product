package mat.study.store.product.controller;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;
import mat.study.store.product.model.entity.Product;
import mat.study.store.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "/v1/products")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public Page<Product> getProducts(@PageableDefault(size = 20) Pageable pageable) {
    return productService.getAll(pageable);
  }

  @GetMapping(value = "/{id}")
  public Product getProduct(@PathVariable("id") Long id) {
    return productService.getProduct(id);
  }

  @PutMapping(value = "/{id}/stock")
  public ResponseEntity<Product> updateStockProduct
      (@PathVariable Long id, @RequestParam(name = "quantity")
      @DecimalMin(value = "0.0", message = "Stock should be greater than 0") Double quantity) {
    productService.updateStock(id, quantity);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }

}
