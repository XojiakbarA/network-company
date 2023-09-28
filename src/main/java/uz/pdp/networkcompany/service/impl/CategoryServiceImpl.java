package uz.pdp.networkcompany.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.dto.request.CategoryRequest;
import uz.pdp.networkcompany.dto.view.category.CategoryView;
import uz.pdp.networkcompany.entity.Category;
import uz.pdp.networkcompany.mapper.CategoryMapper;
import uz.pdp.networkcompany.repository.CategoryRepository;
import uz.pdp.networkcompany.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;
    private final String existsByName = "Category with name = %s already exists";
    private final String notFoundById = "Category with id = %d not found";

    @Override
    public Page<CategoryView> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(c -> categoryMapper.mapToCategoryView(c));
    }

    @Override
    public CategoryView getById(Long id) {
        return categoryMapper.mapToCategoryView(findById(id));
    }

    @Override
    public CategoryView create(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new EntityExistsException(String.format(existsByName, request.getName()));
        }
        Category category = new Category();

        category.setName(request.getName());

        return categoryMapper.mapToCategoryView(save(category));
    }

    @Override
    public CategoryView update(CategoryRequest request, Long id) {
        if (categoryRepository.existsByNameAndIdNot(request.getName(), id)) {
            throw new EntityExistsException(String.format(existsByName, request.getName()));
        }
        Category category = findById(id);

        category.setName(request.getName());

        return categoryMapper.mapToCategoryView(save(category));
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundById, id))
        );
    }

    @Override
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(notFoundById, id));
        }
        categoryRepository.deleteById(id);
    }
}
