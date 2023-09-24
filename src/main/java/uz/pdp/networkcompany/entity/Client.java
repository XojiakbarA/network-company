package uz.pdp.networkcompany.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.networkcompany.entity.abs.User;
import uz.pdp.networkcompany.enums.ClientType;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "clients")
public class Client extends User {
    @Column(nullable = false)
    private ClientType type;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Passport passport;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return type.getAuthorities();
    }
}
