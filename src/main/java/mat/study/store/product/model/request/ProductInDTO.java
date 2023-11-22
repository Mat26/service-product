package mat.study.store.product.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record ProductInDTO(@NotEmpty(message = "Name may not be empty") String name,
                           String description,
                           @Positive(message = "Price should be positive")
                           @NotNull(message = "Price may not be empty") Double price,
                           @NotNull(message = "Category may not be empty") CategoryInDTO category) {
}
