package uz.pdp.networkcompany.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.networkcompany.dto.request.CategoryRequest;
import uz.pdp.networkcompany.dto.response.Response;
import uz.pdp.networkcompany.dto.view.category.CategoryView;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.service.CategoryService;

@Validated
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_CATEGORY," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CREATE_CATEGORY)")
    @Validated(OnCreate.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody CategoryRequest request) {
        CategoryView category = categoryService.create(request);

        return new Response(HttpStatus.CREATED.name(), category);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_CATEGORY," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_CATEGORIES)")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<CategoryView> categories = categoryService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), categories);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_CATEGORY," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_CATEGORY)")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        CategoryView category = categoryService.getById(id);

        return new Response(HttpStatus.OK.name(), category);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_CATEGORY," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).UPDATE_CATEGORY)")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody CategoryRequest request, @PathVariable Long id) {
        CategoryView category = categoryService.update(request, id);

        return new Response(HttpStatus.OK.name(), category);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_CATEGORY," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).DELETE_CATEGORY)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
