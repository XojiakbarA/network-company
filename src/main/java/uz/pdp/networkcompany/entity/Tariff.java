package uz.pdp.networkcompany.entity;

import jakarta.persistence.*;
import lombok.Data;
import uz.pdp.networkcompany.enums.ClientType;

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
}
