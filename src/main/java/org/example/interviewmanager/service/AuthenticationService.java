package org.example.interviewmanager.service;

import org.example.interviewmanager.dto.LoginUserDTO;
import org.example.interviewmanager.dto.RegisterUserDTO;
import org.example.interviewmanager.dto.UserDTO;
import org.example.interviewmanager.repository.UserRepository;
import org.example.interviewmanager.repository.entity.User;
import org.example.interviewmanager.utils.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public UserDTO signup(RegisterUserDTO input) {
        User user = new User();
        user.setFullName(input.fullName());
        user.setEmail(input.email());
        user.setPassword(passwordEncoder.encode(input.password()));
        return UserMapper.toDTO(userRepository.save(user));
    }

    public UserDTO authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.password()
                )
        );
        return UserMapper.toDTO(userRepository.findByEmail(input.email()).orElseThrow(() -> new UsernameNotFoundException("User email not found")));
    }
}
