package uz.pdp.networkcompany.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.networkcompany.dto.request.ServiceRequest;
import uz.pdp.networkcompany.dto.request.SetCategoryRequest;
import uz.pdp.networkcompany.dto.view.service.ServiceView;
import uz.pdp.networkcompany.entity.Service;

public interface ServiceService {
    Page<ServiceView> getAll(Pageable pageable);

    ServiceView getById(Long id);

    ServiceView create(ServiceRequest request);

    ServiceView update(ServiceRequest request, Long id);

    ServiceView setCategory(SetCategoryRequest request, Long id);

    Service save(Service service);

    Service findById(Long id);

    void deleteById(Long id);
}
