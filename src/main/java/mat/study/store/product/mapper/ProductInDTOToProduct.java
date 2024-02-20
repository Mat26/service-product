package mat.study.store.product.mapper;

import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.enums.ProductStatus;
import mat.study.store.product.model.request.ProductInDTO;
import mat.study.store.product.service.CategoryService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProductInDTOToProduct implements IMapper<ProductInDTO, Product> {

  private final CategoryService categoryService;

  public ProductInDTOToProduct(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  public Product map(ProductInDTO in) {

    return Product.builder()
        .name(in.name())
        .description(in.description())
        .stock(0.0)
        .price(in.price())
        .status(ProductStatus.CREATED)
        .createAt(new Date())
        .build();
  }

  public void update(Product product, ProductInDTO in) {

    String name = in.name();
    String description = in.description();
    Double price = in.price();
    Long idCategory = in.idCategory();

    if (name != null) {
      product.setName(name);
    }
    if (description != null) {
      product.setDescription(description);
    }
    if (price != null) {
      product.setPrice(price);
    }
    if (idCategory != null) {
      product.setCategory(categoryService.getCategory(in.idCategory()));
    }

  }
}
