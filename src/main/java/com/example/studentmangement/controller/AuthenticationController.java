package com.example.studentmangement.controller;

import com.example.studentmangement.dto.JwtAuthResponse;
import com.example.studentmangement.dto.LoginDTO;
import com.example.studentmangement.dto.RefreshTokenRequest;
import com.example.studentmangement.entity.RefreshToken;
import com.example.studentmangement.exception.RefreshTokenException;
import com.example.studentmangement.exception.TokenExpiredException;
import com.example.studentmangement.service.AuthenticationService;
import com.example.studentmangement.service.RefreshTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;



    public AuthenticationController(AuthenticationService authenticationService, RefreshTokenService refreshTokenService) {
        this.authenticationService = authenticationService;

        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDTO){

        String token = authenticationService.login(loginDTO);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        String refreshToken = refreshTokenService.createRefreshToken(loginDTO.getEmail()).getToken();
        jwtAuthResponse.setRefreshToken(refreshToken);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<JwtAuthResponse> refreshtoken(@RequestBody RefreshTokenRequest refreshTokenRequest){

        String requestRefreshToken = refreshTokenRequest.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getStudent)
                .map(student -> {
                    String token = authenticationService.generateTokenFromEmail(student.getEmail());
                    return ResponseEntity.ok(new JwtAuthResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new RefreshTokenException("Refresh token is not in database!"));
    }
}
