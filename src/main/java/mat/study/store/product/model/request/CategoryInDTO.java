package mat.study.store.product.model.request;

import jakarta.validation.constraints.NotEmpty;


public record CategoryInDTO(Long id,
                            @NotEmpty(message = "Name may not be empty") String name) {

}
