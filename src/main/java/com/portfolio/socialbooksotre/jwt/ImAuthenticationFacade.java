package com.portfolio.socialbooksotre.jwt;

import org.springframework.security.core.Authentication;

public interface ImAuthenticationFacade {

    Authentication getAuthentication();
}
