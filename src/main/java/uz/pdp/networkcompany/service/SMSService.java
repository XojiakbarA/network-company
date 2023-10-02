package uz.pdp.networkcompany.service;

import uz.pdp.networkcompany.dto.request.SMSRequest;
import uz.pdp.networkcompany.dto.view.sms.SMSView;
import uz.pdp.networkcompany.entity.SMS;

public interface SMSService {
    SMSView create(SMSRequest request, String username);

    SMS save(SMS sms);
}
