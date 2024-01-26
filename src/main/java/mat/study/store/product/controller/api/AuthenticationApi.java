package mat.study.store.product.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mat.study.store.product.model.request.AuthenticationRequest;
import mat.study.store.product.model.request.RegisterRequest;
import mat.study.store.product.model.response.JwtAuthenticationResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Authentication")
@RequestMapping("/api/v1/auth")
public interface AuthenticationApi {
  @Operation(
      summary = "Sign In",
      description = "Sign In service",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Success Response",
              content = {@Content(
                  schema = @Schema(implementation = JwtAuthenticationResponse.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE
              )
              }
          ),
          @ApiResponse(
              responseCode = "403",
              description = "User must be unique",
              content = {@Content(schema = @Schema())}
          )
      }

  )
  @PostMapping("/register")
  ResponseEntity<JwtAuthenticationResponse> register(@RequestBody RegisterRequest request) throws Exception;

  @Operation(
      summary = "Authenticate",
      description = "Authenticate In service",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Success Response",
              content = {@Content(
                  schema = @Schema(implementation = JwtAuthenticationResponse.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE
              )
              }
          ),
          @ApiResponse(
              responseCode = "403",
              description = "Invalid email or password",
              content = {@Content(schema = @Schema())}
          )
      }

  )
  @PostMapping("/authenticate")
  ResponseEntity<JwtAuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);
}
