package uz.pdp.networkcompany.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "passports")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private Date dateOfIssue;

    @Column(nullable = false)
    private Date dateOfExpiration;

    @OneToOne(mappedBy = "passport")
    private Client client;

    @OneToOne(mappedBy = "passport")
    private SIMCard simCard;
}
