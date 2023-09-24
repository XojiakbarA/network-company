package uz.pdp.networkcompany.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.networkcompany.dto.request.TariffRequest;
import uz.pdp.networkcompany.dto.response.Response;
import uz.pdp.networkcompany.dto.view.tariff.TariffView;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.service.TariffService;

@Validated
@RestController
@RequestMapping("/tariffs")
public class TariffController {
    @Autowired
    private TariffService tariffService;

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_TARIFF," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CREATE_TARIFF)")
    @Validated(OnCreate.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody TariffRequest request) {
        TariffView tariff = tariffService.create(request);

        return new Response(HttpStatus.CREATED.name(), tariff);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_TARIFF," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_TARIFFS)")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<TariffView> tariffs = tariffService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), tariffs);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_TARIFF," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_TARIFF)")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        TariffView tariff = tariffService.getById(id);

        return new Response(HttpStatus.OK.name(), tariff);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_TARIFF," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).UPDATE_TARIFF)")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody TariffRequest request, @PathVariable Long id) {
        TariffView tariff = tariffService.update(request, id);

        return new Response(HttpStatus.OK.name(), tariff);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_TARIFF," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).DELETE_TARIFF)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        tariffService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
