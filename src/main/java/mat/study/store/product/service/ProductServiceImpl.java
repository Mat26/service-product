package mat.study.store.product.service;

import mat.study.store.product.exeption.NoFoundProductException;
import mat.study.store.product.mapper.ProductInDTOToProduct;
import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.enums.ProductStatus;
import mat.study.store.product.model.request.ProductInDTO;
import mat.study.store.product.repository.ProductRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"product"})
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
    Page<Product> products = productRepository.findAll(pageable);
    if (products.isEmpty()) {
      throw new NoFoundProductException();
    }
    return products;
  }

  @Override
  public List<Product> getProductsByCategory(Long categoryId) {
    List<Product> products = productRepository.findAllByCategoryId(categoryId);
    if (products.isEmpty()) {
      throw new NoFoundProductException();
    }
    return products;
  }

  @Override
  @Cacheable(key = "#id")
  public Product getProduct(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new NoFoundProductException(id));
  }

  @Override
  public Product createProduct(ProductInDTO productInDTO) {
    Category category = categoryService.getCategoryByName(productInDTO.category().name());
    Product product = mapper.map(productInDTO);
    product.setCategory(category);
    return productRepository.save(product);
  }

  @Override
  @CachePut(key = "#id")
  public void updateProduct(Long id, ProductInDTO productInDTO) {
    Product productDB = getProduct(id);
    if (productInDTO.category() != null && productInDTO.category().name() != null
        && !productInDTO.category().name().isEmpty()) {
      Category category = categoryService.getCategoryByName(productInDTO.category().name());
      productDB.setCategory(category);
    }
    mapper.update(productDB, productInDTO);
    productRepository.save(productDB);
  }

  @Override
  @CachePut(key = "#id")
  public void updateStock(Long id, Double quantity) {
    Product productDB = getProduct(id);
    Double stock = productDB.getStock() + quantity;
    productDB.setStock(stock);
    productRepository.save(productDB);
  }

  @Override
  @CacheEvict(key = "#id")
  public void deleteProduct(Long id) {
    Product productDB = getProduct(id);
    productDB.setStatus(ProductStatus.DELETED);
    productRepository.save(productDB);
  }
}
