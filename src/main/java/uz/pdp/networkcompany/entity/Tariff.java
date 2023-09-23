package uz.pdp.networkcompany.entity;

import jakarta.persistence.*;
import lombok.Data;
import uz.pdp.networkcompany.entity.enums.ClientType;

import java.util.Set;

@Data
@Entity(name = "tariffs")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private ClientType type;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Float connectionPrice;

    @Column(nullable = false)
    private Integer perMonthMinuteLimit;

    @Column(nullable = false)
    private Integer perMonthMBLimit;

    @Column(nullable = false)
    private Integer perMonthSMSLimit;

    @Column(nullable = false)
    private Float perMinutePrice;

    @Column(nullable = false)
    private Float perMBPrice;

    @Column(nullable = false)
    private Float perSMSPrice;

    @OneToMany(mappedBy = "tariff", cascade = CascadeType.REMOVE)
    private Set<SIMCard> simCards;
}
