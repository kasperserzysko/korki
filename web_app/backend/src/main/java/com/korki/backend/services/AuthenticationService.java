package com.korki.backend.services;


import com.korki.backend.exceptions.AccountNotEnabledException;
import com.korki.backend.models.SecurityUser;
import com.korki.backend.models.UserCredentialsDto;
import com.korki.common.models.User;
import com.korki.common.repositories.UserRepository;
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
  private final EmailService emailService;

  public void register(UserCredentialsDto dto) throws UsernameNotFoundException{
      var user = new User();
      user.setEmail(dto.getEmail());
      user.setPassword(passwordEncoder.encode(dto.getPassword()));
      emailService.sendActivationLink(dto.getEmail());
      userRepository.save(user);
  }

  public void login(UserCredentialsDto dto) throws AuthenticationException, AccountNotEnabledException, UsernameNotFoundException {
      var userEntity = userRepository.findUserByEmail(dto.getEmail())
              .orElseThrow(() -> new UsernameNotFoundException("Email not found!"));
      if (!userEntity.isEnabled()){
          throw new AccountNotEnabledException();
      }
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                dto.getEmail(),
                dto.getPassword()
            )
      );

  }

}
