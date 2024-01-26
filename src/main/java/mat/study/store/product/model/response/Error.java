package mat.study.store.product.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@AllArgsConstructor
public class Error {
  @Schema(example = "PR-1")
  private final String code;
  @Schema(example = "Price should be greater than 0")
  private final String message;
  @Schema(example = "BAD REQUEST")
  private final HttpStatus status;
  @Schema(example = "2024-01-26T02:46:02.932Z")
  private final LocalDateTime time;
}
