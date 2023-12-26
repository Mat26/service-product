package mat.study.store.product.model.request;

import jakarta.validation.constraints.NotBlank;


public record CategoryInDTO(Long id,
                            @NotBlank(message = "Name may not be empty") String name) {

}
