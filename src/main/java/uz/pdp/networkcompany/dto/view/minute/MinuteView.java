package uz.pdp.networkcompany.dto.view.minute;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MinuteView {
    private Long id;
    private Long senderNumber;
    private Long receiverNumber;
    private Integer duration;
    private LocalDateTime createdAt;
}
