package mat.study.store.product.service;

import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.request.ProductInDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

  Page<Product> getAll(Pageable pageable);

  Product getProduct(Long id);

  Product createProduct(Long id, ProductInDTO productInDTO);

  Product updateProduct(Long idCategory, Long idProduct, ProductInDTO productInDTO);

  Product updateStock(Long id, Double quantity);

  void deleteProduct(Long id);

}
