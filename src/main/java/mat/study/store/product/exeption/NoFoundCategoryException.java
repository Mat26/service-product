package mat.study.store.product.exeption;

public class NoFoundCategoryException extends RuntimeException {
  private final static String MSG_BY_ID = "There is not any category with the name: ";

  public NoFoundCategoryException(String name) {
    super(String.format(MSG_BY_ID + "%s", name));
  }
}
