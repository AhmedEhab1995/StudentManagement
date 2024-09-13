package com.example.studentmangement.service;

import com.example.studentmangement.dto.LoginDTO;

public interface AuthenticationService {
    String login(LoginDTO loginDTO);

    String generateTokenFromEmail(String email);
}
