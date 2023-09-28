package uz.pdp.networkcompany.dto.view.service;

import lombok.Builder;
import lombok.Data;
import uz.pdp.networkcompany.enums.ServiceType;

@Data
@Builder
public class ServiceView {
    private Long id;
    private String name;
    private ServiceType type;
    private Double price;
    private CategoryView category;
}
