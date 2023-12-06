package mat.study.store.product.model.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public class Error {
  private final String code;
  private final String message;
  private final HttpStatus status;
  private final LocalDateTime time;

  public Error(Builder builder) {
    code = builder.code;
    message = builder.message;
    status = builder.status;
    time = builder.time;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public static class Builder<T extends Builder<T>> {
    private String code;
    private String message;
    private HttpStatus status;
    private LocalDateTime time;

    public T code(String code) {
      this.code = code;
      return self();
    }

    public T message(String message) {
      this.message = message;
      return self();
    }

    public T status(HttpStatus status) {
      this.status = status;
      return self();
    }

    public T time(LocalDateTime time) {
      this.time = time;
      return self();
    }

    protected T self() {
      return (T) this;
    }

    public Error build() {
      return new Error(this);
    }
  }

}
