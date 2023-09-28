package uz.pdp.networkcompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import uz.pdp.networkcompany.enums.ClientType;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "tariffs")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClientType type;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double connectionPrice;

    @Column(nullable = false)
    private Integer perMonthMinuteLimit;

    @Column(nullable = false)
    private Integer perMonthMBLimit;

    @Column(nullable = false)
    private Integer perMonthSMSLimit;

    @Column(nullable = false)
    private Double perMinutePrice;

    @Column(nullable = false)
    private Double perMBPrice;

    @Column(nullable = false)
    private Double perSMSPrice;

    @OneToMany(mappedBy = "tariff")
    private Set<SIMCard> simCards;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "tariffs")
    private Set<Package> packages = new HashSet<>();

    public void addPackage(Package pack) {
        this.packages.add(pack);
        pack.getTariffs().add(this);
    }
    public void removePackage(Package pack) {
        this.packages.remove(pack);
        pack.getTariffs().remove(this);
    }
}
