package uz.pdp.networkcompany.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.networkcompany.dto.request.CategoryRequest;
import uz.pdp.networkcompany.dto.view.category.CategoryView;
import uz.pdp.networkcompany.entity.Category;

public interface CategoryService {
    Page<CategoryView> getAll(Pageable pageable);

    CategoryView getById(Long id);

    CategoryView create(CategoryRequest request);

    CategoryView update(CategoryRequest request, Long id);

    Category save(Category category);

    Category findById(Long id);

    void deleteById(Long id);
}
