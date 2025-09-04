package com.example.Hospital.Managment.System.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Hospital.Managment.System.dto.LoginRequestDto;
import com.example.Hospital.Managment.System.dto.LoginResponseDto;
import com.example.Hospital.Managment.System.dto.SignUPResponseDto;
import com.example.Hospital.Managment.System.security.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //LoginController -> LoginService -> AuthenticationManager -> DaoAuthenticationProvider (default provider) -> CustomUserDetailService -> UserRepository -> UserDetails(User) -> DaoAuthenticationProvider (default provider) -> AuthenticationManager ->  LoginService

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUPResponseDto> postMethodName(@RequestBody LoginRequestDto signUpRequestDto) {
        return ResponseEntity.ok(authService.signUp(signUpRequestDto));   
        
    }
    
    
}