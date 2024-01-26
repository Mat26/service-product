package mat.study.store.product.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Builder
public record AuthenticationRequest(@Schema(example = "Antoni.Flores@gmail.com")
                                    String email,
                                    @Schema(example = "1234")
                                    String password) {

}
