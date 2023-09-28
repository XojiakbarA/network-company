package uz.pdp.networkcompany.enums;

import org.springframework.security.core.GrantedAuthority;

public enum AuthorityType implements GrantedAuthority {
    CRUD_ALL,
    GET_MANAGERS, GET_MANAGER, CREATE_MANAGER, UPDATE_MANAGER, DELETE_MANAGER, CRUD_MANAGER,
    GET_BRANCH_LEADERS, GET_BRANCH_LEADER, CREATE_BRANCH_LEADER, UPDATE_BRANCH_LEADER, DELETE_BRANCH_LEADER, CRUD_BRANCH_LEADER,
    GET_WORKERS, GET_WORKER, CREATE_WORKER, UPDATE_WORKER, DELETE_WORKER, CRUD_WORKER,
    GET_BRANCHES, GET_BRANCH, CREATE_BRANCH, UPDATE_BRANCH, DELETE_BRANCH, SET_BRANCH_LEADER, CRUD_BRANCH,
    GET_CLIENTS, GET_CLIENT, CREATE_CLIENT, UPDATE_CLIENT, DELETE_CLIENT, CRUD_CLIENT,
    GET_PASSPORTS, GET_PASSPORT, CREATE_PASSPORT, UPDATE_PASSPORT, DELETE_PASSPORT, CRUD_PASSPORT,
    GET_SIM_CARDS, GET_SIM_CARD, CREATE_SIM_CARD, UPDATE_SIM_CARD, DELETE_SIM_CARD, SET_TARIFF, SET_PASSPORT, ADD_BALANCE, ADD_SERVICE, REMOVE_SERVICE, ADD_PACKAGE, CRUD_SIM_CARD,
    GET_TARIFFS, GET_TARIFF, CREATE_TARIFF, UPDATE_TARIFF, DELETE_TARIFF, CRUD_TARIFF,
    GET_CATEGORIES, GET_CATEGORY, CREATE_CATEGORY, UPDATE_CATEGORY, DELETE_CATEGORY, CRUD_CATEGORY,
    GET_SERVICES, GET_SERVICE, CREATE_SERVICE, UPDATE_SERVICE, DELETE_SERVICE, SET_CATEGORY, CRUD_SERVICE,
    GET_PACKAGES, GET_PACKAGE, CREATE_PACKAGE, UPDATE_PACKAGE, DELETE_PACKAGE, CRUD_PACKAGE;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
