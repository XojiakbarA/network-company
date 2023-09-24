package uz.pdp.networkcompany.service.impl;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.enums.AuthorityType;
import uz.pdp.networkcompany.service.AuthService;

import java.util.Arrays;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public boolean hasAnyAuthority(AuthorityType... authorities) {
        String[] array = new String[authorities.length];
        for (int i = 0; i < authorities.length; i++) {
            array[i] = authorities[i].getAuthority();
        }
        SecurityExpressionRoot root = new SecurityExpressionRoot(getAuthentication()) {};
        return root.hasAnyAuthority(array);
    }
}
