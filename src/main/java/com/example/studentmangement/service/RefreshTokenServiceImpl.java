package com.example.studentmangement.service;

import com.example.studentmangement.entity.RefreshToken;
import com.example.studentmangement.exception.TokenExpiredException;
import com.example.studentmangement.repo.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{
    private final Long refreshTokenDurationMs;
    private final StudentService studentService;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(StudentService studentService,
                                   @Value("${app-refresh-token-expiration-milliseconds}") Long refreshTokenDurationMs,
                                   RefreshTokenRepository refreshTokenRepository) {
        this.studentService = studentService;
        this.refreshTokenDurationMs = refreshTokenDurationMs;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(String userEmail) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setStudent(studentService.findByEmail(userEmail).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenExpiredException("Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    @Override
    public int deleteByStudentEmail(String studentEmail) {
        return refreshTokenRepository.deleteByStudent(studentService.findByEmail(studentEmail).get());
    }
}
