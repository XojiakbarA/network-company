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
import uz.pdp.networkcompany.enums.USSDType;
import uz.pdp.networkcompany.mapper.ServiceMapper;
import uz.pdp.networkcompany.repository.ServiceRepository;
import uz.pdp.networkcompany.service.CategoryService;
import uz.pdp.networkcompany.service.ServiceService;
import uz.pdp.networkcompany.service.USSDService;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private USSDService ussdService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ServiceMapper serviceMapper;
    private final String existsByName = "Service with name = %s already exists";
    private final String existsByCode = "USSD with code = %s already exists";
    private final String notFoundById = "Service with id = %d not found";
    private final String notFoundByCode = "Service with code = %s not found";

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
        for (USSDType value : USSDType.values()) {
            if (value.getCode().equals(request.getUssdCode())) {
                throw new EntityExistsException(String.format(existsByCode, request.getUssdCode()));
            }
        }
        if (ussdService.existsByCode(request.getUssdCode())) {
            throw new EntityExistsException(String.format(existsByCode, request.getUssdCode()));
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

        for (USSDType value : USSDType.values()) {
            if (value.getCode().equals(request.getUssdCode())) {
                throw new EntityExistsException(String.format(existsByCode, request.getUssdCode()));
            }
        }
        if (ussdService.existsByCodeAndIdNot(request.getUssdCode(), service.getUssd().getId())) {
            throw new EntityExistsException(String.format(existsByCode, request.getUssdCode()));
        }

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
    public Service findByUSSDCode(String code) {
        return serviceRepository.findByUssdCode(code).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundByCode, code))
        );
    }

    @Override
    public boolean existsByCode(String code) {
        return serviceRepository.existsByUssdCode(code);
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
