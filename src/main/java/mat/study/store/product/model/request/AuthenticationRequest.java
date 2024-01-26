package mat.study.store.product.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    @Schema(example = "Antoni.Flores@gmail.com")
    private String email;
    @Schema(example = "1234")
    private String password;
}
