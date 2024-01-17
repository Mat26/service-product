package mat.study.store.product.service;

import mat.study.store.product.model.request.RegisterRequest;
import mat.study.store.product.model.request.AuthenticationRequest;
import mat.study.store.product.model.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(RegisterRequest request) throws Exception;
    JwtAuthenticationResponse signin(AuthenticationRequest request);
}
