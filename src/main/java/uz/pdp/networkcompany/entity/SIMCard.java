package uz.pdp.networkcompany.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "sim_cards")
public class SIMCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long number;

    @Column(nullable = false)
    private Double balance = 0D;

    @Column(nullable = false)
    private Integer minuteLimit = 0;

    @Column(nullable = false)
    private Integer mbLimit = 0;

    @Column(nullable = false)
    private Integer smsLimit = 0;

    @Column(nullable = false)
    private Boolean active = false;

    @OneToOne
    private Passport passport;

    @ManyToOne
    private Tariff tariff;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Service> services = new HashSet<>();

    public void addService(Service service) {
        this.services.add(service);
        service.getSimCards().add(this);
    }
    public void removeService(Service service) {
        this.services.remove(service);
        service.getSimCards().remove(this);
    }

    public Boolean getHasClient() {
        return this.passport != null;
    }
}
