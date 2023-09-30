package uz.pdp.networkcompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.dto.request.LoginRequest;
import uz.pdp.networkcompany.enums.AuthorityType;
import uz.pdp.networkcompany.security.JWTProvider;
import uz.pdp.networkcompany.service.AuthService;

import java.util.Arrays;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private UserDetailsService userDetailsService;

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

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        return jwtProvider.generateToken(Map.of("roles", userDetails.getAuthorities()), userDetails);
    }
}
