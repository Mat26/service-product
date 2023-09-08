package mat.study.store.product.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CategoryInDTO {
  private Long id;
  @NotEmpty(message = "Name may not be empty")
  private String name;
}
