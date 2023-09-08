package mat.study.store.product.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductInDTO {

  @NotEmpty(message = "Name may not be empty")
  private String name;
  private String description;
  private Double price;
  @NotNull(message = "Category may not be empty")
  private CategoryInDTO category;
}
