package mat.study.store.product.controller.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Cache")
@RequestMapping(value = "/api/v1/cache")
public interface CacheApi {

  @Operation(
      summary = "Get all cache entries",
      description = "Return all entries by cache",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Success Response",
              content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Object.class)),
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
  @GetMapping("/all/entries")
  ResponseEntity<Object> getAllCachesEntries();
}
