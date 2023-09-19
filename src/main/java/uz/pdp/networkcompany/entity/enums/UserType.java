package uz.pdp.networkcompany.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority {
    DIRECTOR, BRANCH_MANAGER, TARIFF_MANAGER, BRANCH_LEADER, EMPLOYEE;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
