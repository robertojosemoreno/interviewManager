package org.example.interviewmanager.service;

import org.example.interviewmanager.dto.LoginUserDTO;
import org.example.interviewmanager.dto.RegisterUserDTO;
import org.example.interviewmanager.dto.UserDTO;
import org.example.interviewmanager.repository.UserRepository;
import org.example.interviewmanager.repository.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            ModelMapper modelMapper
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }

    public UserDTO signup(RegisterUserDTO input) {
        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    public UserDTO authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return modelMapper.map(userRepository.findByEmail(input.getEmail()).orElseThrow(), UserDTO.class);
    }
}
