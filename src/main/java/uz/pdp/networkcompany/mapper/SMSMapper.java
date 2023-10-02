package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.view.sms.SMSView;
import uz.pdp.networkcompany.entity.SMS;

@Component
public class SMSMapper {
    public SMSView mapToSMSView(SMS sms) {
        if (sms == null) return null;
        return SMSView.builder()
                .id(sms.getId())
                .senderNumber(sms.getSender().getNumber())
                .receiverNumber(sms.getReceiver().getNumber())
                .content(sms.getContent())
                .createdAt(sms.getCreatedAt())
                .build();
    }
}
