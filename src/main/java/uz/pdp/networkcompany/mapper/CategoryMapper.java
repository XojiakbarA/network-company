package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.view.category.CategoryView;
import uz.pdp.networkcompany.entity.Category;

@Component
public class CategoryMapper {
    public CategoryView mapToCategoryView(Category category) {
        if (category == null) return null;
        return CategoryView.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
