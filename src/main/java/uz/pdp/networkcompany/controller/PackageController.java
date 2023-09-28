package uz.pdp.networkcompany.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.networkcompany.dto.request.PackageRequest;
import uz.pdp.networkcompany.dto.response.Response;
import uz.pdp.networkcompany.dto.view.pack.PackageView;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.service.PackageService;

@Validated
@RestController
@RequestMapping("/packages")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_PACKAGE," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CREATE_PACKAGE)")
    @Validated(OnCreate.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody PackageRequest request) {
        PackageView pack = packageService.create(request);

        return new Response(HttpStatus.CREATED.name(), pack);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_PACKAGE," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_PACKAGES)")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<PackageView> packages = packageService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), packages);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_PACKAGE," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_PACKAGE)")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        PackageView pack = packageService.getById(id);

        return new Response(HttpStatus.OK.name(), pack);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_PACKAGE," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).UPDATE_PACKAGE)")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody PackageRequest request, @PathVariable Long id) {
        PackageView pack = packageService.update(request, id);

        return new Response(HttpStatus.OK.name(), pack);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_PACKAGE," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).DELETE_PACKAGE)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        packageService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
