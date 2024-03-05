package mat.study.store.product.service.impl;

import lombok.RequiredArgsConstructor;
import mat.study.store.product.exeption.NonUniqueUserException;
import mat.study.store.product.model.entity.User;
import mat.study.store.product.model.enums.Role;
import mat.study.store.product.model.request.AuthenticationRequest;
import mat.study.store.product.model.request.RegisterRequest;
import mat.study.store.product.model.response.JwtAuthenticationResponse;
import mat.study.store.product.repository.UserRepository;
import mat.study.store.product.service.AuthenticationService;
import mat.study.store.product.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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
  public JwtAuthenticationResponse signup(RegisterRequest request) {
    var user = User.builder()
        .firstName(request.firstName())
        .lastName(request.lastName())
        .email(request.email())
        .password(passwordEncoder.encode(request.password()))
        .role(Role.USER)
        .build();
    try {
      userRepository.save(user);
    } catch (Exception e) {
      throw new NonUniqueUserException();
    }

    var jwt = jwtService.generateToken(user);
    return JwtAuthenticationResponse.builder()
        .token(jwt)
        .build();
  }

  @Override
  public JwtAuthenticationResponse signin(AuthenticationRequest request) {
    User user;
    try {
      user = (User) authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.email(), request.password())).getPrincipal();
    }catch(AuthenticationException ex){
      throw new IllegalArgumentException();
    }
    var jwt = jwtService.generateToken(user);
    return JwtAuthenticationResponse.builder().token(jwt).build();
  }
}
