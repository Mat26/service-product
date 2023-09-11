package mat.study.store.product.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record ProductInDTO(@NotEmpty(message = "Name may not be empty") String name,
                           String description,
                           Double price,
                           @NotNull(message = "Category may not be empty") CategoryInDTO category) {
}
