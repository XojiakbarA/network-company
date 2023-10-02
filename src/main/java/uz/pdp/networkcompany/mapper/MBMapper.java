package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.view.mb.MBView;
import uz.pdp.networkcompany.entity.MB;

@Component
public class MBMapper {
    public MBView mapToMBView(MB mb) {
        if (mb == null) return null;
        return MBView.builder()
                .id(mb.getId())
                .userNumber(mb.getUser().getNumber())
                .bytes(mb.getBytes())
                .createdAt(mb.getCreatedAt())
                .build();
    }
}
