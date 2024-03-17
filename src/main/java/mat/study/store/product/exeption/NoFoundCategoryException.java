package mat.study.store.product.exeption;

import lombok.Getter;

@Getter
public class NoFoundCategoryException extends RuntimeException {
  private final static String MSG_BY_ID = "There is not any category with the id: ";
  private Long id;

  public NoFoundCategoryException(Long id) {
    super(String.format(MSG_BY_ID + "%s", id));
    this.id = id;
  }
}
