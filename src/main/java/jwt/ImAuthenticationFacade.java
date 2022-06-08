package jwt;

import org.springframework.security.core.Authentication;

public interface ImAuthenticationFacade {

    Authentication getAuthentication();
}
