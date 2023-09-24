package uz.pdp.networkcompany.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.networkcompany.dto.request.SIMCardRequest;
import uz.pdp.networkcompany.dto.response.Response;
import uz.pdp.networkcompany.dto.view.simCard.SIMCardView;
import uz.pdp.networkcompany.service.SIMCardService;

@RestController
@RequestMapping("/sim-cards")
public class SIMCardController {
    @Autowired
    private SIMCardService simCardService;

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_SIM_CARD," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CREATE_SIM_CARD)")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody SIMCardRequest request) {
        SIMCardView simCard = simCardService.create(request);

        return new Response(HttpStatus.CREATED.name(), simCard);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_SIM_CARD," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_SIM_CARDS)")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<SIMCardView> simCards = simCardService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), simCards);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_SIM_CARD," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_SIM_CARD)")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        SIMCardView simCard = simCardService.getById(id);

        return new Response(HttpStatus.OK.name(), simCard);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_SIM_CARD," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).UPDATE_SIM_CARD)")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody SIMCardRequest request, @PathVariable Long id) {
        SIMCardView simCard = simCardService.update(request, id);

        return new Response(HttpStatus.OK.name(), simCard);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_SIM_CARD," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).DELETE_SIM_CARD)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        simCardService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}