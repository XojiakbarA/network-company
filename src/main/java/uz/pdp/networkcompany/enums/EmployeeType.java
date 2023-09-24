package uz.pdp.networkcompany.enums;

import lombok.Getter;

import java.util.Set;

import static uz.pdp.networkcompany.enums.AuthorityType.*;

@Getter
public enum EmployeeType {
    DIRECTOR(Set.of(CRUD_ALL)),
    TARIFF_MANAGER(Set.of(CRUD_TARIFF)),
    SIM_CARD_MANAGER(Set.of(CRUD_SIM_CARD, SET_PASSPORT, ADD_AMOUNT)),
    BRANCH_MANAGER(Set.of(CRUD_BRANCH_LEADER, CRUD_BRANCH, CRUD_WORKER, SET_BRANCH_LEADER)),
    BRANCH_LEADER(Set.of(CRUD_BRANCH, CRUD_WORKER)),
    WORKER(Set.of(CRUD_CLIENT, CRUD_PASSPORT));

    private final Set<AuthorityType> authorities;

    EmployeeType(Set<AuthorityType> authorities) {
        this.authorities = authorities;
    }
}
