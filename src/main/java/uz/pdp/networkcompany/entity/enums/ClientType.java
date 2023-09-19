package uz.pdp.networkcompany.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum ClientType implements GrantedAuthority {
    LEGAL, PHYSICAL;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
