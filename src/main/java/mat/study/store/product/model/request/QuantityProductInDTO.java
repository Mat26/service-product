package mat.study.store.product.model.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record QuantityProductInDTO(
    @NotNull(message = "Stock may not be empty")
    @DecimalMin(value = "0.0", message = "Stock should be greater than 0") Double quantity) {
}
