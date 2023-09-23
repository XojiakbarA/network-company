package uz.pdp.networkcompany.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.networkcompany.entity.enums.ClientType;

import java.util.Collection;
import java.util.Collections;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "clients")
public class Client extends User {
    @Column(nullable = false)
    private ClientType type;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Passport passport;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(type);
    }
}
