package uz.pdp.networkcompany.dto.view.mb;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MBView {
    private Long id;
    private Long userNumber;
    private Integer bytes;
    private LocalDateTime createdAt;
}
