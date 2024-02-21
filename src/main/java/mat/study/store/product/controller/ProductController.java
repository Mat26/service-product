package mat.study.store.product.controller;

import mat.study.store.product.controller.api.ProductApi;
import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.request.ProductInDTO;
import mat.study.store.product.model.request.QuantityProductInDTO;
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
  public Page<Product> searchByName(String name, Pageable pageable) {
    return productService.findByName(name, pageable);
  }

  @Override
  public Page<Product> searchByPriceBetween(Double minPrice, Double maxPrice,
                                     Pageable pageable) {
    return productService.findByPriceBetween(minPrice, maxPrice, pageable);
  }

  @Override
  public ResponseEntity<Product> createProduct(ProductInDTO productInDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(productService.createProduct(productInDTO));
  }

  @Override
  public ResponseEntity<Void> updateStockProduct
      (Long id, QuantityProductInDTO quantity) {
    productService.updateStock(id, quantity);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> updateProduct(Long idProduct,
                                            ProductInDTO productInDTO) {
    productService.updateProduct(idProduct, productInDTO);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> deleteProduct(Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }

}
