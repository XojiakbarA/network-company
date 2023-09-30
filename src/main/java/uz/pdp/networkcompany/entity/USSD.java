package uz.pdp.networkcompany.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "ussd")
public class USSD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;
}
