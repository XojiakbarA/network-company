package uz.pdp.networkcompany.enums;

import lombok.Getter;

import java.util.Set;

import static uz.pdp.networkcompany.enums.AuthorityType.*;

@Getter
public enum EmployeeType {
    DIRECTOR(Set.of(CRUD_ALL)),
    TARIFF_MANAGER(Set.of(CRUD_TARIFF, GET_PAYMENTS)),
    SIM_CARD_MANAGER(Set.of(CRUD_SIM_CARD, SET_TARIFF, SET_PASSPORT, ADD_BALANCE, ADD_SERVICE, REMOVE_SERVICE, ADD_PACKAGE, GET_PAYMENTS)),
    BRANCH_MANAGER(Set.of(CRUD_BRANCH_LEADER, CRUD_BRANCH, CRUD_WORKER, SET_BRANCH_LEADER)),
    SERVICE_MANAGER(Set.of(CRUD_CATEGORY, CRUD_SERVICE, SET_CATEGORY, GET_PAYMENTS)),
    PACKAGE_MANAGER(Set.of(CRUD_PACKAGE, GET_PAYMENTS)),
    BRANCH_LEADER(Set.of(CRUD_BRANCH, CRUD_WORKER)),
    WORKER(Set.of(CRUD_CLIENT, CRUD_PASSPORT));

    private final Set<AuthorityType> authorities;

    EmployeeType(Set<AuthorityType> authorities) {
        this.authorities = authorities;
    }
}
