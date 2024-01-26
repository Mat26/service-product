package mat.study.store.product.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import mat.study.store.product.model.entity.Product;
import mat.study.store.product.model.request.ProductInDTO;
import mat.study.store.product.model.response.Error;
import mat.study.store.product.model.response.ErrorDetail;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Tag(name = "Product")
@RequestMapping(value = "/api/v1/category")
@Validated
public interface ProductApi {
  @Operation(
      summary = "Get all products",
      description = "Return all products per page",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Success Response",
              content = {@Content(schema = @Schema(implementation = Page.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)}
          ),
          @ApiResponse(
              responseCode = "403",
              content = {@Content(schema = @Schema())}
          ),
          @ApiResponse(responseCode = "500",
              content = {@Content(schema = @Schema())})
      },
      security = {
          @SecurityRequirement(name = "bearerAuth")
      }
  )
  @GetMapping("/products")
  Page<Product> getProducts(@ParameterObject @PageableDefault(size = 20) Pageable pageable);

  @Operation(
      summary = "Get product",
      description = "Return product by id",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Success Response",
              content = {@Content(schema = @Schema(implementation = Product.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)}
          ),
          @ApiResponse(
              responseCode = "403",
              content = {@Content(schema = @Schema())}
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Invalid id supplied",
              content = {@Content(schema = @Schema(implementation = Error.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)}
          ),
          @ApiResponse(responseCode = "500",
              content = {@Content(schema = @Schema())})
      },
      security = {
          @SecurityRequirement(name = "bearerAuth")
      }
  )
  @GetMapping(value = "/products/{id}")
  Product getProduct(@PathVariable("id") Long id);

  @Operation(
      summary = "Create Product",
      description = "Create Product",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Success Response",
              content = {@Content(schema = @Schema(implementation = Product.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)}
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Error body request",
              content = {@Content(schema = @Schema(implementation = ErrorDetail.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)}
          ),
          @ApiResponse(
              responseCode = "403",
              content = {@Content(schema = @Schema())}
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Invalid id supplied",
              content = {@Content(schema = @Schema(implementation = Error.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)}
          ),
          @ApiResponse(responseCode = "500",
              content = {@Content(schema = @Schema())})
      }, security = {
      @SecurityRequirement(name = "bearerAuth")
  }
  )
  @PostMapping("/{id}/products")
  ResponseEntity<Product> createProduct(@Valid @RequestBody ProductInDTO productInDTO,
                                        @PathVariable("id") Long id);

  @Operation(
      summary = "Update stock",
      description = "Update product stock",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Success Response",
              content = {@Content(schema = @Schema())}
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid stock supplied",
              content = {@Content(schema = @Schema(implementation = ErrorDetail.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)}
          ),
          @ApiResponse(
              responseCode = "403",
              content = {@Content(schema = @Schema())}
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Invalid id supplied",
              content = {@Content(schema = @Schema(implementation = Error.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)}
          ),
          @ApiResponse(responseCode = "500",
              content = {@Content(schema = @Schema(),examples = {})})
      },
      security = {
          @SecurityRequirement(name = "bearerAuth")
      }
  )
  @PutMapping(value = "/products/{id}/stock")
  ResponseEntity<Void> updateStockProduct
  (@PathVariable Long id, @RequestParam(name = "quantity")
  @DecimalMin(value = "0.0", message = "Stock should be greater than 0") Double quantity);

  @Operation(
      summary = "Delete product",
      description = "Change the status of a product from created to deleted",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Success Response",
              content = {@Content(schema = @Schema())}
          ),
          @ApiResponse(
              responseCode = "403",
              content = {@Content(schema = @Schema())}
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Invalid id supplied",
              content = {@Content(schema = @Schema(implementation = Error.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)}
          ),
          @ApiResponse(responseCode = "500",
              content = {@Content(schema = @Schema())})
      },
      security = {
          @SecurityRequirement(name = "bearerAuth")
      }
  )
  @DeleteMapping(value = "/products/{id}")
  ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id);
}