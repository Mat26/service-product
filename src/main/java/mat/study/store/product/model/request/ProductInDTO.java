package mat.study.store.product.model.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ProductInDTO(@NotBlank(message = "Name may not be empty") String name,
                           String description,
                           @DecimalMin(value = "0.0", message = "Price should be greater than 0")
                           @NotNull(message = "Price may not be empty") Double price) {
}
