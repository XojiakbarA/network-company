package uz.pdp.networkcompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.networkcompany.dto.request.MBRequest;
import uz.pdp.networkcompany.dto.view.mb.MBView;
import uz.pdp.networkcompany.entity.MB;
import uz.pdp.networkcompany.mapper.MBMapper;
import uz.pdp.networkcompany.repository.MBRepository;
import uz.pdp.networkcompany.service.MBService;
import uz.pdp.networkcompany.service.SIMCardService;

@Service
public class MBServiceImpl implements MBService {
    @Autowired
    private MBRepository mbRepository;
    @Autowired
    private SIMCardService simCardService;
    @Autowired
    private MBMapper mbMapper;

    @Transactional
    @Override
    public MBView create(MBRequest request, String username) {
        Integer remainingMB = simCardService.payForMB(username, request.getBytes());

        MB mb = new MB();

        mb.setBytes(request.getBytes() - remainingMB);
        mb.setUser(simCardService.findByUsername(username));

        return mbMapper.mapToMBView(save(mb));
    }

    @Override
    public MB save(MB mb) {
        return mbRepository.save(mb);
    }
}
