package mat.study.store.product.model.enums;


public enum InfoError {
  NOT_FOUND("PR-01", ""),
  MISSING_PATH_VARIABLE("PR-02", " parameter is missing in the path"),
  MISSING_REQUEST_PARAMETER("PR-03", " parameter is missing"),
  ARGUMENT_NOT_VALID("PR-04", "Error body request"),
  CONSTRAIN_VIOLATION("PR-05", "It was not possible to process this request");

  private String code;
  private String message;

  InfoError(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
