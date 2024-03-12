package mat.study.store.product.controller;

import mat.study.store.product.controller.api.CategoryApi;
import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.request.CategoryInDTO;
import mat.study.store.product.model.response.CategoryLinkOut;
import mat.study.store.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CategoryController implements CategoryApi {
  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  public Category getCategory(Long id) {
    return categoryService.getCategory(id);
  }

  @Override
  public CategoryLinkOut getProductsByCategory(Long id) {
    Category category = getCategory(id);
    CategoryLinkOut categoryLinkOut = new CategoryLinkOut(category.getId(), category.getName());
    addCategoryLinks(categoryLinkOut);
    return categoryLinkOut;
  }

  private void addCategoryLinks(CategoryLinkOut categoryLinkOut) {
    List<Product> products = categoryService.getProductsByCategory(categoryLinkOut.getId());
    for (Product product : products) {
      categoryLinkOut.add(linkTo(methodOn(ProductController.class).getProduct(product.getId())).withRel("products"));
    }
    categoryLinkOut.add(linkTo(methodOn(CategoryController.class).getCategory(categoryLinkOut.getId())).withSelfRel());
  }

  @Override
  public ResponseEntity<Category> createCategory
      (CategoryInDTO categoryInDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(categoryService.createCategory(categoryInDTO));
  }

}
