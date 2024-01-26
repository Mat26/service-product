package mat.study.store.product.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


@Builder
public record AuthenticationRequest(@Schema(example = "Antoni.Flores@gmail.com")
                                    @Email
                                    @NotBlank(message = "Email may not be empty")
                                    String email,
                                    @Schema(example = "1234")
                                    @NotBlank(message = "Password may not be empty")
                                    String password) {

}
