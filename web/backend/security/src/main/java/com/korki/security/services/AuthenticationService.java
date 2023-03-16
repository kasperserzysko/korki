package com.korki.security.services;


import com.korki.common.models.User;
import com.korki.common.repositories.UserRepository;
import com.korki.security.models.SecurityUser;
import com.korki.security.models.UserCredentialsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  public void register(UserCredentialsDto dto) throws UsernameNotFoundException{
      var user = new User();
      user.setEmail(dto.getEmail());
      user.setPassword(passwordEncoder.encode(dto.getPassword()));
      userRepository.save(user);
  }

  public String login(UserCredentialsDto dto) throws AuthenticationException {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            dto.getEmail(),
            dto.getPassword()
        )
    );
    return jwtService.generateToken(userRepository.findUserByEmail(dto.getEmail()).map(SecurityUser::new).get());

  }

}
