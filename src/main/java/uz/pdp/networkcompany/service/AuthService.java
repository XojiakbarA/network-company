package uz.pdp.networkcompany.service;

import org.springframework.security.core.Authentication;
import uz.pdp.networkcompany.dto.request.LoginRequest;
import uz.pdp.networkcompany.enums.AuthorityType;

public interface AuthService {
    Authentication getAuthentication();
    boolean hasAnyAuthority(AuthorityType... authorities);
    String login(LoginRequest request);
}
