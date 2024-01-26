package mat.study.store.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mat.study.store.product.controller.api.AuthenticationApi;
import mat.study.store.product.model.request.AuthenticationRequest;
import mat.study.store.product.model.request.RegisterRequest;
import mat.study.store.product.model.response.JwtAuthenticationResponse;
import mat.study.store.product.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {
  private final AuthenticationService authenticationService;

  @Override
  public ResponseEntity<JwtAuthenticationResponse> register(@Valid RegisterRequest request) throws Exception {
    return ResponseEntity.ok(authenticationService.signup(request));
  }

  @Override
  public ResponseEntity<JwtAuthenticationResponse> authenticate(@Valid AuthenticationRequest request) {
    return ResponseEntity.ok(authenticationService.signin(request));
  }
}
