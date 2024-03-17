package mat.study.store.product.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import mat.study.store.product.model.entity.Category;
import mat.study.store.product.model.request.CategoryInDTO;
import mat.study.store.product.model.response.CategoryLinkOut;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Tag(name = "Category")
@RequestMapping(value = "/api/v1/categories")
public interface CategoryApi {

  @Operation(
      summary = "Get category by Id",
      description = "Get category by Id",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Success Response",
              content = {@Content(schema = @Schema(implementation = Category.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)}
          ),
          @ApiResponse(
              responseCode = "403",
              content = {@Content(schema = @Schema())}
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Invalid id supplied",
              content = {@Content(schema = @Schema(implementation = ProblemDetail.class),
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)}
          ),
          @ApiResponse(responseCode = "500",
              content = {@Content(schema = @Schema())})
      }, security = {
      @SecurityRequirement(name = "bearerAuth")
  }
  )
  @GetMapping("/{id}")
  Category getCategory(@PathVariable("id") Long id);

  @Operation(
      summary = "Get all products by category",
      description = "Get all products by category",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Success Response",
              content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CategoryLinkOut.class)),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)}
          ),
          @ApiResponse(
              responseCode = "403",
              content = {@Content(schema = @Schema())}
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Invalid id supplied",
              content = {@Content(schema = @Schema(implementation = ProblemDetail.class),
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)}
          ),
          @ApiResponse(responseCode = "500",
              content = {@Content(schema = @Schema())})
      }, security = {
      @SecurityRequirement(name = "bearerAuth")
  }
  )
  @GetMapping("/{id}/products")
  CategoryLinkOut getProductsByCategory(@PathVariable("id") Long id);

  @Operation(
      summary = "Create Category",
      description = "Create Category",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Success Response",
              content = {@Content(schema = @Schema(implementation = Category.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)}
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Error body request",
              content = {@Content(schema = @Schema(implementation = ProblemDetail.class),
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)}
          ),
          @ApiResponse(
              responseCode = "403",
              content = {@Content(schema = @Schema())}
          ),
          @ApiResponse(responseCode = "500",
              content = {@Content(schema = @Schema())})
      }, security = {
      @SecurityRequirement(name = "bearerAuth")
  }
  )
  @PostMapping
  ResponseEntity<Category> createCategory
      (@Valid @RequestBody CategoryInDTO categoryInDTO);

}
