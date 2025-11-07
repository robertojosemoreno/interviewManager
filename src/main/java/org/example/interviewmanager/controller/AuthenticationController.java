package org.example.interviewmanager.controller;

import org.example.interviewmanager.dto.LoginResponseDTO;
import org.example.interviewmanager.dto.LoginUserDTO;
import org.example.interviewmanager.dto.RegisterUserDTO;
import org.example.interviewmanager.dto.UserDTO;
import org.example.interviewmanager.service.AuthenticationService;
import org.example.interviewmanager.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterUserDTO registerUserDTO) {
        UserDTO registeredUser = authenticationService.signup(registerUserDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginUserDTO loginUserDTO) throws NoSuchAlgorithmException {
        UserDTO authUser = authenticationService.authenticate(loginUserDTO);
        String jwtToken = jwtService.generateToken(authUser);
        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
