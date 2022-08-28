package com.portfolio.socialbooksotre.users.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserResponseDto {

    @AllArgsConstructor
    @Builder
    @Getter
    public static class TokenInfo{

        private String grantType;
        private String accessToken;
        private String refreshToken;
        private Long refreshTokenExpirationTime;

        private Long userId;
        private String userName;
    }
}
