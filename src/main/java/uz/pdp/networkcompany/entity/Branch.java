package uz.pdp.networkcompany.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "branches")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(optional = false)
    private Employee leader;
}
