package com.korki.backend.services;


import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.dtos.UserDetailsDto;
import com.korki.backend.dtos.UserCredentialsDto;
import com.korki.backend.services.interfaces.IAuthenticationService;
import com.korki.backend.utills.Mapper;
import com.korki.common.models.Student;
import com.korki.common.models.Teacher;
import com.korki.common.models.User;
import com.korki.common.repositories.StudentRepository;
import com.korki.common.repositories.TeacherRepository;
import com.korki.common.repositories.UserRepository;
import com.korki.email_service.EmailService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {


    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final Validator validator;
    private final Mapper mapper;
    private final JwtService jwtService;

    //REPOS
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;


    @Override
    public void register(UserCredentialsDto dto) {

        validate(dto);                                                                                            //Throws ConstraintViolationException

        var userEntity = new User();
        userEntity.setEmail(dto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
    //    emailService.sendActivationLink(userEntity.getEmail(), userEntity.getActivationLink());
        userRepository.save(userEntity);
    }

    @Override
    public String login(UserCredentialsDto dto) throws DisabledException, AuthenticationException, UsernameNotFoundException {
        var userEntity = userRepository.findUserByEmail(dto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Email not found!"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        return jwtService.generateToken(new SecurityUser(userEntity));
    }


    @Override
    public void fillCredentials(UserDetailsDto dto, SecurityUser user) {
        var userEntity = user.getUser();

        validate(dto);

        mapper.mapEntity(userEntity, dto);

        switch (dto.getRole()){
            case ROLE_STUDENT -> {
                var studentEntity = new Student();
                studentEntity.setUser(userEntity);
                userEntity.setStudent(studentEntity);
                studentRepository.save(studentEntity);
            }
            case ROLE_TEACHER -> {
                var teacherEntity = new Teacher();
                teacherEntity.setUser(userEntity);
                userEntity.setTeacher(teacherEntity);
                teacherRepository.save(teacherEntity);
            }
        }
        userRepository.save(userEntity);
    }

    @Override
    public void activate(String activationUrl) throws UsernameNotFoundException {
        var userEntity = userRepository.findUserByActivationLink(activationUrl)
                .orElseThrow(() -> new UsernameNotFoundException("Niepoprawny link aktywacyjny"));
        userEntity.setEnabled(true);
        userRepository.save(userEntity);
    }

    private <T> void validate(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
