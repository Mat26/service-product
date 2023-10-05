package mat.study.store.product.model.response;

import lombok.experimental.SuperBuilder;


@SuperBuilder
public class ErrorDetail extends Error {
  private final String detail;

  public String getDetail() {
    return detail;
  }
}
