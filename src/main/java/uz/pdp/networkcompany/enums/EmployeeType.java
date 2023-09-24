package uz.pdp.networkcompany.enums;

import lombok.Getter;

import java.util.Set;

@Getter
public enum EmployeeType {
    DIRECTOR(Set.of(AuthorityType.CRUD_ALL)),
    TARIFF_MANAGER(Set.of(AuthorityType.CRUD_TARIFF)),
    SIM_CARD_MANAGER(Set.of(AuthorityType.CRUD_SIM_CARD)),
    BRANCH_MANAGER(Set.of(AuthorityType.CRUD_BRANCH_LEADER, AuthorityType.CRUD_BRANCH, AuthorityType.CRUD_WORKER)),
    BRANCH_LEADER(Set.of(AuthorityType.CRUD_BRANCH, AuthorityType.CRUD_WORKER)),
    WORKER(Set.of(AuthorityType.CRUD_CLIENT, AuthorityType.CRUD_PASSPORT));

    private final Set<AuthorityType> authorities;

    EmployeeType(Set<AuthorityType> authorities) {
        this.authorities = authorities;
    }
}
