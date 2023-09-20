package uz.pdp.networkcompany.dto.view.passport;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PassportView {
    private Long id;
    private String number;
    private Date dateOfBirth;
    private Date dateOfIssue;
    private Date dateOfExpiration;
    private ClientView client;
}
