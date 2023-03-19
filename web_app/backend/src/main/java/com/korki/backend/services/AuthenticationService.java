package com.korki.backend.services;


import com.korki.backend.exceptions.AccountNotEnabledException;
import com.korki.backend.dtos.UserCredentialsDto;
import com.korki.common.models.User;
import com.korki.common.repositories.UserRepository;
import com.korki.email_service.EmailService;
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
      var userEntity = new User();
      userEntity.setEmail(dto.getEmail());
      userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
      emailService.sendActivationLink(userEntity.getEmail(), userEntity.getActivationLink());
      userRepository.save(userEntity);
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
  public void enableAccount(String url) throws UsernameNotFoundException{
      var userEntity = userRepository.findUserByActivationLink(url)
              .orElseThrow(() -> new UsernameNotFoundException("Zły link"));
      System.out.println("Tu jestem");
      userEntity.setEnabled(true);
      userRepository.save(userEntity);
  }

}
