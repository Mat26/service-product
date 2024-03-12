package mat.study.store.product.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryLinkOut extends RepresentationModel<CategoryLinkOut> {
  private Long id;
  private String category;
}
