package uz.pdp.networkcompany.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.networkcompany.dto.request.BranchRequest;
import uz.pdp.networkcompany.dto.request.LeaderRequest;
import uz.pdp.networkcompany.dto.response.Response;
import uz.pdp.networkcompany.dto.view.branch.BranchView;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.service.BranchService;

@Validated
@RestController
@RequestMapping("/branches")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_BRANCH," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CREATE_BRANCH)")
    @Validated(OnCreate.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody BranchRequest request) {
        BranchView branch = branchService.create(request);

        return new Response(HttpStatus.CREATED.name(), branch);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_BRANCH," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_BRANCHES)")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<BranchView> branches = branchService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), branches);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_BRANCH," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_BRANCH)")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        BranchView branch = branchService.getById(id);

        return new Response(HttpStatus.OK.name(), branch);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_BRANCH," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).UPDATE_BRANCH)")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody BranchRequest request, @PathVariable Long id) {
        BranchView branch = branchService.update(request, id);

        return new Response(HttpStatus.OK.name(), branch);
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_BRANCH," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).DELETE_BRANCH)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        branchService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_BRANCH," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).SET_BRANCH_LEADER)")
    @PutMapping("/{id}/leader")
    @ResponseStatus(HttpStatus.OK)
    public Response setLeader(@Valid @RequestBody LeaderRequest request, @PathVariable Long id) {
        BranchView branch = branchService.setLeader(request, id);

        return new Response(HttpStatus.OK.name(), branch);
    }
}
