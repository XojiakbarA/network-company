package uz.pdp.networkcompany.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum EmployeeType implements GrantedAuthority {
    DIRECTOR, BRANCH_MANAGER, TARIFF_MANAGER, BRANCH_LEADER, WORKER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
