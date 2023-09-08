package mat.study.store.product.service;

import mat.study.store.product.model.request.ProductInDTO;
import mat.study.store.product.model.entity.Product;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {

  Page<Product> getAll(Pageable pageable);

  List<Product> getProductsByCategory(Long id);

  Product getProduct(Long id);

  Product createProduct(ProductInDTO productInDTO);

  void updateProduct(Long id, ProductInDTO productInDTO);

  void updateStock(Long id, Double quantity);

  void deleteProduct(Long id);

}
