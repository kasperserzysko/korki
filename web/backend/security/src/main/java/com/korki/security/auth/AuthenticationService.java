package com.korki.security.auth;


import com.korki.common.models.User;
import com.korki.common.repositories.UserRepository;
import com.korki.security.config.JwtService;
import com.korki.security.models.UserCredentialsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public void register(UserCredentialsDto dto) {
    var user = new User();
    user.setEmail(dto.getEmail());
    user.setPassword(passwordEncoder.encode(dto.getPassword()));
    repository.save(user);
  }

  public void login(UserCredentialsDto dto) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            dto.getEmail(),
            dto.getPassword()
        )
    );
  }

}
