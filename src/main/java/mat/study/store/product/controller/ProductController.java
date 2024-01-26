package mat.study.store.product.controller;

import mat.study.store.product.controller.api.ProductApi;
import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.request.ProductInDTO;
import mat.study.store.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController implements ProductApi {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @Override
  public Page<Product> getProducts(Pageable pageable) {
    return productService.getAll(pageable);
  }

  @Override
  public Product getProduct(Long id) {
    return productService.getProduct(id);
  }

  @Override
  public ResponseEntity<Product> createProduct(ProductInDTO productInDTO,
                                               Long id) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(productService.createProduct(id, productInDTO));
  }

  @Override
  public ResponseEntity<Void> updateStockProduct
      (Long id, Double quantity) {
    productService.updateStock(id, quantity);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> deleteProduct(Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }

}
