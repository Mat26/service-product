package mat.study.store.product.exeption;

public class NoFoundCategoryException extends RuntimeException {
  private final static String MSG_BY_NAME = "There is not any category with the name: ";
  private final static String MSG_BY_ID = "There is not any category with the id: ";

  public NoFoundCategoryException(String name) {
    super(String.format(MSG_BY_NAME + "%s", name));
  }
  public NoFoundCategoryException(Long id) {
    super(String.format(MSG_BY_ID + "%s", id));
  }
}
