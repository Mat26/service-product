package mat.study.store.product.exeption;

import lombok.Getter;

@Getter
public class NoFoundProductException extends RuntimeException {
  private final static String MSG = "There is not any product with the id: ";
  private Long id;

  public NoFoundProductException(Long id) {
    super(String.format(MSG + "%s",id));
    this.id = id;
  }
}
