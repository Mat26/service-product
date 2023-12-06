package mat.study.store.product.service;

import mat.study.store.product.model.request.SignUpRequest;
import mat.study.store.product.model.request.SigninRequest;
import mat.study.store.product.model.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request) throws Exception;
    JwtAuthenticationResponse signin(SigninRequest request);
}
