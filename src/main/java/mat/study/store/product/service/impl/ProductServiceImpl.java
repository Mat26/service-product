package mat.study.store.product.service.impl;

import mat.study.store.product.exeption.NoFoundProductException;
import mat.study.store.product.mapper.ProductInDTOToProduct;
import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.enums.ProductStatus;
import mat.study.store.product.model.request.ProductInDTO;
import mat.study.store.product.repository.ProductRepository;
import mat.study.store.product.service.CategoryService;
import mat.study.store.product.service.ProductService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "product")
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  private final CategoryService categoryService;

  private final ProductInDTOToProduct mapper;

  public ProductServiceImpl
      (ProductRepository productRepository, CategoryService categoryService, ProductInDTOToProduct mapper) {
    this.productRepository = productRepository;
    this.categoryService = categoryService;
    this.mapper = mapper;
  }

  @Override
  public Page<Product> getAll(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  @Override
  @Cacheable(key = "#id")
  public Product getProduct(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new NoFoundProductException(id));
  }

  @Override
  public Product createProduct(Long id, ProductInDTO productInDTO) {
    Category category = categoryService.getCategory(id);
    Product product = mapper.map(productInDTO);
    product.setCategory(category);
    return productRepository.save(product);
  }

  @Override
  @CachePut(key = "#idProduct")
  public Product updateProduct(Long idCategory, Long idProduct, ProductInDTO productInDTO) {
    categoryService.getCategory(idCategory);
    Product productDB = getProduct(idProduct);
    mapper.update(productDB, productInDTO);
    return productRepository.save(productDB);
  }

  @Override
  @CachePut(key = "#id")
  public Product updateStock(Long id, Double quantity) {
    Product productDB = getProduct(id);
    Double stockUpdated = calculateStock(productDB.getStock(), quantity);
    productDB.setStock(stockUpdated);
    return productRepository.save(productDB);
  }

  private Double calculateStock(Double old, Double actual) {
    return old + actual;
  }

  @Override
  @CacheEvict(key = "#id")
  public void deleteProduct(Long id) {
    Product productDB = getProduct(id);
    productDB.setStatus(ProductStatus.DELETED);
    productRepository.save(productDB);
  }
}
