package uz.pdp.networkcompany.dto.view.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryView {
    private Long id;
    private String name;
}
