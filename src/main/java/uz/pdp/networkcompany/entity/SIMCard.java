package uz.pdp.networkcompany.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "sim_cards")
public class SIMCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long number;

    @Column(nullable = false)
    private Float balance = 0F;

    @Column(nullable = false)
    private Integer minuteLimit;

    @Column(nullable = false)
    private Integer mbLimit;

    @Column(nullable = false)
    private Integer smsLimit;

    @Column(nullable = false)
    private Boolean active = false;

    @OneToOne
    private Passport passport;

    @OneToOne
    private Tariff tariff;
}
