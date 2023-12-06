package mat.study.store.product.mapper;

import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.enums.ProductStatus;
import mat.study.store.product.model.request.ProductInDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProductInDTOToProduct implements IMapper<ProductInDTO, Product> {

  @Override
  public Product map(ProductInDTO in) {

    return new Product.Builder()
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
