package mat.study.store.product.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ErrorDetail extends Error {
  @Schema(example = "Error 1: {Price should be greater than 0}")
  private final String detail;

}
