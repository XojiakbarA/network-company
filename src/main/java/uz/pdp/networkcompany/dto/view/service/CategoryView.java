package uz.pdp.networkcompany.dto.view.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryView {
    private Long id;
    private String name;
}
