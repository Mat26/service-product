package mat.study.store.product.model.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ErrorDetail extends Error {
  private final String detail;

}
