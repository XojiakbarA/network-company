package uz.pdp.networkcompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import uz.pdp.networkcompany.enums.PackageType;

import java.time.LocalDateTime;

@Data
@Entity(name = "taken_packages")
public class TakenPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(optional = false)
    private SIMCard simCard;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(optional = false)
    private Package pack;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @Column(nullable = false)
    private Integer amount;

    public Boolean isMinutePackage() {
        return this.getPack().getType().equals(PackageType.MINUTE);
    }
    public Boolean isMBPackage() {
        return this.getPack().getType().equals(PackageType.MB);
    }
    public Boolean isSMSPackage() {
        return this.getPack().getType().equals(PackageType.SMS);
    }
}
