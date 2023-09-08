package mat.study.store.product.mapper;

import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.enums.ProductStatus;
import mat.study.store.product.model.request.ProductInDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProductInDTOToProduct implements IMapper<ProductInDTO, Product> {

  @Override
  public Product map(ProductInDTO in) {
    Category category = Category.builder()
        .id(in.getCategory().getId())
        .name(in.getCategory().getName())
        .build();

    return Product.builder()
        .name(in.getName())
        .description(in.getDescription())
        .stock(0.0)
        .price(in.getPrice())
        .status(ProductStatus.CREATED)
        .createAt(new Date())
        .category(category)
        .build();
  }

  public void update(Product product, ProductInDTO in) {

    String name = in.getName();
    String description = in.getDescription();
    Double price = in.getPrice();

    if (name != null) {
      product.setName(name);
    }
    if (description != null) {
      product.setDescription(description);
    }
    if (price != null) {
      product.setPrice(price);
    }

  }
}
