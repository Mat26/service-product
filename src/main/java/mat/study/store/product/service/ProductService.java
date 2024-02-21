package mat.study.store.product.service;

import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.request.ProductInDTO;
import mat.study.store.product.model.request.QuantityProductInDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

  Page<Product> getAll(Pageable pageable);

  Product getProduct(Long id);

  Page<Product> findByName(String name, Pageable pageable);

  Page<Product> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);

  Product createProduct(ProductInDTO productInDTO);

  Product updateProduct(Long idProduct, ProductInDTO productInDTO);

  Product updateStock(Long id, QuantityProductInDTO quantity);

  void deleteProduct(Long id);

}
