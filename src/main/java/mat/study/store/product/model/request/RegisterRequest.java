package mat.study.store.product.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Builder
public record RegisterRequest(@Schema(example = "Antoni")
                              String firstName,
                              @Schema(example = "Flores")
                              String lastName,
                              @Schema(example = "Antoni.Flores@gmail.com")
                              String email,
                              @Schema(example = "1234")
                              String password) {

}
