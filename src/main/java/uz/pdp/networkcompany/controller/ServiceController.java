package uz.pdp.networkcompany.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.networkcompany.dto.request.ServiceRequest;
import uz.pdp.networkcompany.dto.request.SetCategoryRequest;
import uz.pdp.networkcompany.dto.response.Response;
import uz.pdp.networkcompany.dto.view.service.ServiceView;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.service.ServiceService;

@Validated
@RestController
@RequestMapping("/services")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_SERVICE," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CREATE_SERVICE)")
    @Validated(OnCreate.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody ServiceRequest request) {
        ServiceView service = serviceService.create(request);

        return new Response(HttpStatus.CREATED.name(), service);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_SERVICE," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_SERVICES)")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<ServiceView> services = serviceService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), services);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_SERVICE," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_SERVICE)")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        ServiceView service = serviceService.getById(id);

        return new Response(HttpStatus.OK.name(), service);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_SERVICE," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).UPDATE_SERVICE)")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody ServiceRequest request, @PathVariable Long id) {
        ServiceView service = serviceService.update(request, id);

        return new Response(HttpStatus.OK.name(), service);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_SERVICE," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).DELETE_SERVICE)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        serviceService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_SERVICE," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).SET_CATEGORY)")
    @PutMapping("/{id}/category")
    @ResponseStatus(HttpStatus.OK)
    public Response setCategory(@Valid @RequestBody SetCategoryRequest request, @PathVariable Long id) {
        ServiceView service = serviceService.setCategory(request, id);

        return new Response(HttpStatus.OK.name(), service);
    }
}
