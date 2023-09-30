package uz.pdp.networkcompany.entity;

import jakarta.persistence.*;
import lombok.Data;
import uz.pdp.networkcompany.enums.DurationType;
import uz.pdp.networkcompany.enums.PackageType;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PackageType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DurationType durationType;

    @Column(nullable = false)
    private Boolean leftover = false;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private USSD ussd;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tariff> tariffs = new HashSet<>();

    public void addTariff(Tariff tariff) {
        this.tariffs.add(tariff);
        tariff.getPackages().add(this);
    }
    public void removeTariff(Tariff tariff) {
        this.tariffs.remove(tariff);
        tariff.getPackages().remove(this);
    }
}
