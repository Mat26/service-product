package mat.study.store.product.model.response;

import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@SuperBuilder
public class Error {
  private final String code;
  private final String message;
  private final HttpStatus status;
  private final LocalDateTime time;

  public Error(String code, String message, HttpStatus status, LocalDateTime time) {
    this.code = code;
    this.message = message;
    this.status = status;
    this.time = time;
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
}
