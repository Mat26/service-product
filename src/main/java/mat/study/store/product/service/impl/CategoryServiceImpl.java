package mat.study.store.product.service.impl;

import mat.study.store.product.exeption.NoFoundCategoryException;
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

  @Override
  public Category getCategory(Long id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new NoFoundCategoryException(id));
  }

  public Category createCategory(CategoryInDTO categoryInDTO) {
    Category category = new Category(categoryInDTO.name());
    return categoryRepository.save(category);
  }

  @Override
  public List<Product> getProductsByCategory(Long categoryId) {
    return productRepository.findAllProductsByCategory(categoryId);
  }


}
