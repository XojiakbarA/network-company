package uz.pdp.networkcompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import uz.pdp.networkcompany.enums.ServiceType;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceType type;

    @Column(nullable = false)
    private Double price;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private USSD ussd;

    @ManyToOne
    private Category category;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "services")
    private Set<SIMCard> simCards = new HashSet<>();

    public void addSIMCard(SIMCard simCard) {
        this.simCards.add(simCard);
        simCard.getServices().add(this);
    }
    public void removeSIMCard(SIMCard simCard) {
        this.simCards.remove(simCard);
        simCard.getServices().remove(this);
    }
}
