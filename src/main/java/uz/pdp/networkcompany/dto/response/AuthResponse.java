package uz.pdp.networkcompany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthResponse {
    private String token;
    private String message;
}
