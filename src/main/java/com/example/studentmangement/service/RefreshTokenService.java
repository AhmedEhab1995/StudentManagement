package com.example.studentmangement.service;

import com.example.studentmangement.entity.RefreshToken;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);

    RefreshToken createRefreshToken(String userEmail);

    RefreshToken verifyExpiration(RefreshToken token);

    @Transactional
    int deleteByStudentEmail(String studentEmail);
}
