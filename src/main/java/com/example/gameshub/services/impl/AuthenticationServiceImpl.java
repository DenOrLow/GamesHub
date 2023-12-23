package com.example.gameshub.services.impl;


import com.example.gameshub.domain.dao.request.SignInRequest;
import com.example.gameshub.domain.dao.request.SignUpRequest;
import com.example.gameshub.domain.dao.response.JwtAuthenticationResponse;
import com.example.gameshub.domain.models.User;
import com.example.gameshub.adapter.repositories.UserRepo;
import com.example.gameshub.services.AuthenticationService;
import com.example.gameshub.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var user = User.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .build();
        user = userService.save(user, request.getRole());
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepo.findUserByEmail(request.getEmail()).orElseThrow(() ->
            new UsernameNotFoundException(""));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
