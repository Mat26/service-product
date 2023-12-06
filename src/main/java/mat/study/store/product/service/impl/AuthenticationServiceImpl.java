package mat.study.store.product.service.impl;

import lombok.RequiredArgsConstructor;
import mat.study.store.product.exeption.NonUniqueUserException;
import mat.study.store.product.model.entity.User;
import mat.study.store.product.model.enums.Role;
import mat.study.store.product.model.request.SignUpRequest;
import mat.study.store.product.model.request.SigninRequest;
import mat.study.store.product.model.response.JwtAuthenticationResponse;
import mat.study.store.product.repository.UserRepository;
import mat.study.store.product.service.AuthenticationService;
import mat.study.store.product.service.JwtService;
import org.hibernate.exception.DataException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) throws Exception {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        try {
            userRepository.save(user);
        }catch (Exception e){
            throw new NonUniqueUserException();
        }

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
