package br.com.emendes.powerkrtestapi.config.security;

import br.com.emendes.powerkrtestapi.config.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configurações de segurança.
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AuthenticationProvider authenticationProvider;

  private static final String[] POST_WHITELIST = {"/api/v1/auth", "/api/v1/users"};
  private static final String[] SWAGGER_WHITELIST = {
      "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/favicon.ico"};

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable()

        .authorizeHttpRequests()
        .requestMatchers(HttpMethod.POST, POST_WHITELIST).permitAll()
        .requestMatchers(HttpMethod.GET, SWAGGER_WHITELIST).permitAll()
        .anyRequest().authenticated()

        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

    return httpSecurity.build();
  }

}
