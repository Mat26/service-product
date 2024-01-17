package mat.study.store.product.controller;

import lombok.RequiredArgsConstructor;
import mat.study.store.product.model.request.RegisterRequest;
import mat.study.store.product.model.request.AuthenticationRequest;
import mat.study.store.product.model.response.JwtAuthenticationResponse;
import mat.study.store.product.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> register(@RequestBody RegisterRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtAuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
