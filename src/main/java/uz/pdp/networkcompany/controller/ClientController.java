package uz.pdp.networkcompany.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.networkcompany.dto.request.user.ClientRequest;
import uz.pdp.networkcompany.dto.response.Response;
import uz.pdp.networkcompany.dto.view.client.ClientView;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.service.ClientService;

@Validated
@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_CLIENT," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CREATE_CLIENT)")
    @Validated(OnCreate.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody ClientRequest request) {
        ClientView client = clientService.create(request);

        return new Response(HttpStatus.CREATED.name(), client);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_CLIENT," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_CLIENTS)")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<ClientView> clients = clientService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), clients);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_CLIENT," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_CLIENT)")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        ClientView client = clientService.getById(id);

        return new Response(HttpStatus.OK.name(), client);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_CLIENT," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).UPDATE_CLIENT)")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody ClientRequest request, @PathVariable Long id) {
        ClientView client = clientService.update(request, id);

        return new Response(HttpStatus.OK.name(), client);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_CLIENT," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).DELETE_CLIENT)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        clientService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
