package com.korki.security.services;

import com.korki.common.models.User;
import com.korki.common.repositories.UserRepository;
import com.korki.security.models.SecurityUser;
import com.korki.security.models.UserRegisterDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginAndRegisterService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginAndRegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find user with email: " + username));
    }

    public void registerUser(UserRegisterDto dto) throws UsernameNotFoundException{
        userRepository.findUserByEmail(dto.getEmail())
                .ifPresentOrElse(user -> {
                    throw new UsernameNotFoundException("Account with this email already exist!");
                }, () -> userRepository.save(mapper(dto)));
    }


    private User mapper(UserRegisterDto dto){
        var user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }
}
