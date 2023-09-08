package mat.study.store.product.exeption;

public class NoFoundProductException extends RuntimeException {
  private final static String MSG = "There aren't products";
  private final static String MSG_BY_ID = "There is not any product with the id: ";

  public NoFoundProductException() {
    super(MSG);
  }

  public NoFoundProductException(Long id) {
    super(String.format(MSG_BY_ID + "%s",id));
  }
}
