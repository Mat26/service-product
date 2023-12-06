package mat.study.store.product.model.response;

public class ErrorDetail extends Error {
  private final String detail;

  public ErrorDetail(Builder builder) {
    super(builder);
    this.detail = builder.detail;
  }

  public String getDetail() {
    return detail;
  }

  public static class Builder extends Error.Builder<Builder> {
    private String detail;

    public Builder detail(String detail) {
      this.detail = detail;
      return this;
    }

    @Override
    protected Builder self() {
      return this;
    }

    @Override
    public ErrorDetail build() {
      return new ErrorDetail(this);
    }

  }

}
