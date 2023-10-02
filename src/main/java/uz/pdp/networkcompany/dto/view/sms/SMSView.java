package uz.pdp.networkcompany.dto.view.sms;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SMSView {
    private Long id;
    private Long senderNumber;
    private Long receiverNumber;
    private String content;
    private LocalDateTime createdAt;
}
