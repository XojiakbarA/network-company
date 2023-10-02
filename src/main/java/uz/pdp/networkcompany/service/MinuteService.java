package uz.pdp.networkcompany.service;

import uz.pdp.networkcompany.dto.request.MinuteRequest;
import uz.pdp.networkcompany.dto.view.minute.MinuteView;
import uz.pdp.networkcompany.entity.Minute;

public interface MinuteService {
    MinuteView create(MinuteRequest request, String username);

    Minute save(Minute minute);
}
