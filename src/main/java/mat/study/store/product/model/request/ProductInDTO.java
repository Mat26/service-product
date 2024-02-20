package mat.study.store.product.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mat.study.store.product.validator.GeneralValidatorInfo;
import mat.study.store.product.validator.UpdateValidatorInfo;


public record ProductInDTO(@Schema(example = "Adidas Cloudfoam Ultimate")
                           @NotBlank(message = "Name may not be empty", groups = GeneralValidatorInfo.class)
                           String name,
                           @Schema(example = "Walk in the air in the black / black CLOUDFOAM ULTIMATE running shoe from ADIDAS")
                           String description,
                           @Schema(example = "39.99")
                           @DecimalMin(value = "0.0", message = "Price should be greater than 0",
                               groups = {GeneralValidatorInfo.class, UpdateValidatorInfo.class})
                           @NotNull(message = "Price may not be empty",
                               groups = GeneralValidatorInfo.class) Double price,
                           @NotNull(message = "Id Category may not be empty",
                               groups = GeneralValidatorInfo.class) Long idCategory
                           ) {
}
