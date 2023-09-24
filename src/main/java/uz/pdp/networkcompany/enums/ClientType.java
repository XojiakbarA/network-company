package uz.pdp.networkcompany.enums;

import lombok.Getter;

import java.util.Set;

@Getter
public enum ClientType {
    LEGAL(Set.of()),
    PHYSICAL(Set.of());

    private final Set<AuthorityType> authorities;

    ClientType(Set<AuthorityType> authorities) {
        this.authorities = authorities;
    }
}
