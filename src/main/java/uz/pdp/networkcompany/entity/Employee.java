package uz.pdp.networkcompany.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.networkcompany.entity.abs.User;
import uz.pdp.networkcompany.enums.EmployeeType;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "employees")
public class Employee extends User {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeType type;

    @OneToOne(mappedBy = "leader", cascade = CascadeType.REMOVE)
    private Branch branch;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return type.getAuthorities();
    }
}
