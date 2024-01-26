package mat.study.store.product.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


public record CategoryInDTO(@Schema(example = "shoes")
                            @NotBlank(message = "Name may not be empty") String name) {

}
