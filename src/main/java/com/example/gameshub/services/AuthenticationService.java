package com.example.gameshub.services;


import com.example.gameshub.domain.dao.request.SignInRequest;
import com.example.gameshub.domain.dao.request.SignUpRequest;
import com.example.gameshub.domain.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);
    JwtAuthenticationResponse signIn(SignInRequest request);
}
