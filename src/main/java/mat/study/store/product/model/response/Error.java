package mat.study.store.product.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@AllArgsConstructor
public class Error {
  private final String code;
  private final String message;
  private final HttpStatus status;
  private final LocalDateTime time;
}
