package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.request.ServiceRequest;
import uz.pdp.networkcompany.dto.view.service.CategoryView;
import uz.pdp.networkcompany.dto.view.service.ServiceView;
import uz.pdp.networkcompany.entity.Category;
import uz.pdp.networkcompany.entity.Service;
import uz.pdp.networkcompany.enums.ServiceType;

@Component
public class ServiceMapper {
    public ServiceView mapToServiceView(Service service) {
        if (service == null) return null;
        return ServiceView.builder()
                .id(service.getId())
                .name(service.getName())
                .type(service.getType())
                .price(service.getPrice())
                .category(mapToCategoryView(service.getCategory()))
                .build();
    }
    public Service mapToService(ServiceRequest request) {
        if (request == null) return null;
        Service service = new Service();

        setAttributes(request, service);

        return service;
    }
    public void mapToService(ServiceRequest request, Service service) {
        setAttributes(request, service);
    }
    private void setAttributes(ServiceRequest request, Service service) {
        if (request.getName() != null && !request.getName().isBlank()) {
            service.setName(request.getName());
        }
        if (request.getType() != null && !request.getType().isBlank()) {
            service.setType(ServiceType.valueOf(request.getType().toUpperCase()));
        }
        if (request.getPrice() != null) {
            service.setPrice(service.getPrice());
        }
    }
    private CategoryView mapToCategoryView(Category category) {
        if (category == null) return null;
        return CategoryView.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
