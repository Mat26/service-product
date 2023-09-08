package mat.study.store.product.service;

import mat.study.store.product.exeption.NoFoundCategoryException;
import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.request.CategoryInDTO;
import mat.study.store.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Category createCategory(CategoryInDTO categoryInDTO) {
    Category category = Category.builder()
        .name(categoryInDTO.getName())
        .build();
    return categoryRepository.save(category);
  }

  public Category getCategoryByName(String name) {
    return categoryRepository.findCategoryByName(name)
        .orElseThrow(() -> new NoFoundCategoryException(name));
  }

}
