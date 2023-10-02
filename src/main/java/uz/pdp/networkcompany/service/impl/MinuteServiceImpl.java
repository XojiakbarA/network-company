package uz.pdp.networkcompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.networkcompany.dto.request.MinuteRequest;
import uz.pdp.networkcompany.dto.view.minute.MinuteView;
import uz.pdp.networkcompany.entity.Minute;
import uz.pdp.networkcompany.mapper.MinuteMapper;
import uz.pdp.networkcompany.repository.MinuteRepository;
import uz.pdp.networkcompany.service.MinuteService;
import uz.pdp.networkcompany.service.SIMCardService;

@Service
public class MinuteServiceImpl implements MinuteService {
    @Autowired
    private MinuteRepository minuteRepository;
    @Autowired
    private SIMCardService simCardService;
    @Autowired
    private MinuteMapper minuteMapper;

    @Transactional
    @Override
    public MinuteView create(MinuteRequest request, String username) {
        Integer remainingDuration = simCardService.payForMinute(username, request.getDuration());

        Minute minute = new Minute();

        minute.setDuration(request.getDuration() - remainingDuration);
        minute.setSender(simCardService.findByUsername(username));
        minute.setReceiver(simCardService.findByNumber(request.getReceiverNumber()));

        return minuteMapper.mapToMinuteView(save(minute));
    }

    @Override
    public Minute save(Minute minute) {
        return minuteRepository.save(minute);
    }
}
