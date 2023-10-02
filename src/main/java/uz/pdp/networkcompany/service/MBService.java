package uz.pdp.networkcompany.service;

import uz.pdp.networkcompany.dto.request.MBRequest;
import uz.pdp.networkcompany.dto.view.mb.MBView;
import uz.pdp.networkcompany.entity.MB;

public interface MBService {
    MBView create(MBRequest request, String username);

    MB save(MB mb);
}
