package uz.pdp.networkcompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.networkcompany.dto.request.SMSRequest;
import uz.pdp.networkcompany.dto.view.sms.SMSView;
import uz.pdp.networkcompany.entity.SMS;
import uz.pdp.networkcompany.mapper.SMSMapper;
import uz.pdp.networkcompany.repository.SMSRepository;
import uz.pdp.networkcompany.service.SIMCardService;
import uz.pdp.networkcompany.service.SMSService;

@Service
public class SMSServiceImpl implements SMSService {
    @Autowired
    private SMSRepository smsRepository;
    @Autowired
    private SIMCardService simCardService;
    @Autowired
    private SMSMapper smsMapper;

    @Transactional
    @Override
    public SMSView create(SMSRequest request, String username) {
        simCardService.payForSms(username);

        SMS sms = new SMS();

        sms.setContent(request.getContent());
        sms.setSender(simCardService.findByUsername(username));
        sms.setReceiver(simCardService.findByNumber(request.getReceiverNumber()));

        return smsMapper.mapToSMSView(save(sms));
    }

    @Override
    public SMS save(SMS sms) {
        return smsRepository.save(sms);
    }
}
