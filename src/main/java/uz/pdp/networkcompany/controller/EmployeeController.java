package uz.pdp.networkcompany.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.networkcompany.dto.request.user.EmployeeRequest;
import uz.pdp.networkcompany.dto.response.Response;
import uz.pdp.networkcompany.dto.view.employee.EmployeeView;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.service.EmployeeService;

@Validated
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Validated(OnCreate.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody EmployeeRequest request) {
        EmployeeView employee = employeeService.create(request);

        return new Response(HttpStatus.CREATED.name(), employee);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<EmployeeView> employees = employeeService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), employees);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        EmployeeView employee = employeeService.getById(id);

        return new Response(HttpStatus.OK.name(), employee);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody EmployeeRequest request, @PathVariable Long id) {
        EmployeeView employee = employeeService.update(request, id);

        return new Response(HttpStatus.OK.name(), employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
