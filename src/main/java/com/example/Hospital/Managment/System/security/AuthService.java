package com.example.Hospital.Managment.System.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.Hospital.Managment.System.dto.LoginRequestDto;
import com.example.Hospital.Managment.System.dto.LoginResponseDto;
import com.example.Hospital.Managment.System.dto.SignUPResponseDto;
import com.example.Hospital.Managment.System.entity.User;
import com.example.Hospital.Managment.System.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
       /*  this will take username and password from login screen and then 
        AuthenticationManager will call loadUserByusername and fetch userDetails from db
        then if user is found it go to userDetails and format in proper way and return to AuthenticationManager
        then it will match password and if it matches then it will return authentication object*/
        Authentication authentication=authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

       
        User user=(User)authentication.getPrincipal();

         //And here we are generating token for that authenticaion object
        String token=authUtil.generaterAccessToken(user);
        return new LoginResponseDto(token,user.getId());
    }

    public SignUPResponseDto signUp(LoginRequestDto loginRequestDto) {
        User user=userRepository.findByUsername(loginRequestDto.getUsername()).orElse(null);

        if(user !=null) throw new IllegalArgumentException("User already exists");

        user=userRepository.save(User.builder()
            .username(loginRequestDto.getUsername())
            .password(passwordEncoder.encode(loginRequestDto.getPassword()))
            .build()
        );
        return new SignUPResponseDto(user.getId(),user.getUsername());
    }
    
}
