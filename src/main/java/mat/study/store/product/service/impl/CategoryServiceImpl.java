package mat.study.store.product.service.impl;

import mat.study.store.product.exeption.NoFoundCategoryException;
import mat.study.store.product.exeption.NoFoundProductException;
import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.request.CategoryInDTO;
import mat.study.store.product.repository.CategoryRepository;
import mat.study.store.product.repository.ProductRepository;
import mat.study.store.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  private final ProductRepository productRepository;

  @Autowired
  public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
    this.categoryRepository = categoryRepository;
    this.productRepository = productRepository;
  }

  public Category createCategory(CategoryInDTO categoryInDTO) {
    Category category = new Category(categoryInDTO.name());
    return categoryRepository.save(category);
  }

  public Category getCategoryByName(String name) {
    return categoryRepository.findCategoryByName(name)
        .orElseThrow(() -> new NoFoundCategoryException(name));
  }

  @Override
  public List<Product> getProductsByCategory(Long categoryId) {
    List<Product> products = productRepository.findAllByCategoryId(categoryId);
    if (products.isEmpty()) {
      throw new NoFoundProductException();
    }
    return products;
  }

}
