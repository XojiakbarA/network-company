package uz.pdp.networkcompany.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.networkcompany.dto.request.ServiceRequest;
import uz.pdp.networkcompany.dto.request.SetCategoryRequest;
import uz.pdp.networkcompany.dto.view.service.ServiceView;
import uz.pdp.networkcompany.entity.Category;
import uz.pdp.networkcompany.entity.SIMCard;
import uz.pdp.networkcompany.entity.Service;
import uz.pdp.networkcompany.mapper.ServiceMapper;
import uz.pdp.networkcompany.repository.ServiceRepository;
import uz.pdp.networkcompany.service.CategoryService;
import uz.pdp.networkcompany.service.ServiceService;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ServiceMapper serviceMapper;
    private final String existsByName = "Service with name = %s already exists";
    private final String notFoundById = "Service with id = %d not found";

    @Override
    public Page<ServiceView> getAll(Pageable pageable) {
        return serviceRepository.findAll(pageable).map(s -> serviceMapper.mapToServiceView(s));
    }

    @Override
    public ServiceView getById(Long id) {
        return serviceMapper.mapToServiceView(findById(id));
    }

    @Override
    public ServiceView create(ServiceRequest request) {
        if (serviceRepository.existsByName(request.getName())) {
            throw new EntityExistsException(String.format(existsByName, request.getName()));
        }
        Service service = serviceMapper.mapToService(request);

        return serviceMapper.mapToServiceView(save(service));
    }

    @Override
    public ServiceView update(ServiceRequest request, Long id) {
        if (serviceRepository.existsByNameAndIdNot(request.getName(), id)) {
            throw new EntityExistsException(String.format(existsByName, request.getName()));
        }
        Service service = findById(id);

        serviceMapper.mapToService(request, service);

        return serviceMapper.mapToServiceView(save(service));
    }

    @Override
    public ServiceView setCategory(SetCategoryRequest request, Long id) {
        Service service = findById(id);
        Category category = categoryService.findById(request.getCategoryId());

        service.setCategory(category);

        return serviceMapper.mapToServiceView(save(service));
    }

    @Override
    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Service findById(Long id) {
        return serviceRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundById, id))
        );
    }

    @Override
    public void deleteById(Long id) {
        Service service = findById(id);
        for (SIMCard simCard : service.getSimCards()) {
            service.removeSIMCard(simCard);
        }
        serviceRepository.deleteById(id);
    }
}
