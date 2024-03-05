package mat.study.store.product.model.enums;


public enum InfoError {
  NOT_FOUND("PR-01", ""),
  MISSING_PATH_VARIABLE("PR-02", " parameter is missing in the path"),
  MISSING_REQUEST_PARAMETER("PR-03", " parameter is missing"),
  ARGUMENT_NOT_VALID("PR-04", "Error body request"),
  CONSTRAIN_VIOLATION("PR-05", "Violation of parameter restriction"),
  UNIQUE_USER("PR-06", "This user already exists"),
  USER_PASSWORD_ERROR("PR-07", "Invalid email or password");

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
