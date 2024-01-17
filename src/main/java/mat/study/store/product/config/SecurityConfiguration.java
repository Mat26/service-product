package mat.study.store.product.config;

import lombok.RequiredArgsConstructor;
import mat.study.store.product.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.
        csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(request -> request.requestMatchers(
                antMatcher("/h2-console/**")
                , antMatcher("/v2/api-docs")
                , antMatcher("/v3/api-docs")
                , antMatcher("/v3/api-docs/**")
                , antMatcher("/swagger-resources")
                , antMatcher("/swagger-resources/**")
                , antMatcher("/configuration/ui")
                , antMatcher("/configuration/security")
                , antMatcher("/swagger-ui/**")
                , antMatcher("/webjars/**")
                , antMatcher("/swagger-ui.html"))
            .permitAll())
        .authorizeHttpRequests(request -> request.requestMatchers(antMatcher("/api/v1/auth/**"))
            .permitAll().anyRequest().authenticated())
        .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
        .authenticationProvider(authenticationProvider).addFilterBefore(
            jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

    return http.getOrBuild();
  }
}
