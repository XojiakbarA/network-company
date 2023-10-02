package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.view.minute.MinuteView;
import uz.pdp.networkcompany.entity.Minute;

@Component
public class MinuteMapper {
    public MinuteView mapToMinuteView(Minute minute) {
        if (minute == null) return null;
        return MinuteView.builder()
                .id(minute.getId())
                .senderNumber(minute.getSender().getNumber())
                .receiverNumber(minute.getReceiver().getNumber())
                .duration(minute.getDuration())
                .createdAt(minute.getCreatedAt())
                .build();
    }
}
