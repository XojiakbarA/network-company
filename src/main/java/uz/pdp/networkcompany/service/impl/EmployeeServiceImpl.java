package uz.pdp.networkcompany.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.dto.request.user.EmployeeRequest;
import uz.pdp.networkcompany.dto.view.employee.EmployeeView;
import uz.pdp.networkcompany.entity.Employee;
import uz.pdp.networkcompany.enums.EmployeeType;
import uz.pdp.networkcompany.mapper.EmployeeMapper;
import uz.pdp.networkcompany.repository.EmployeeRepository;
import uz.pdp.networkcompany.service.AuthService;
import uz.pdp.networkcompany.service.EmployeeService;

import static uz.pdp.networkcompany.enums.AuthorityType.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private AuthService authService;
    private final String existsByUsername = "Employee with username = %s already exists";
    private final String notFoundById = "Employee with id = %d not found";
    private final String accessDenied = "Access Denied";

    @Override
    public Page<EmployeeView> getAll(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(e -> employeeMapper.mapToEmployeeView(e));
    }

    @Override
    public EmployeeView getById(Long id) {
        return employeeMapper.mapToEmployeeView(findById(id));
    }

    @Override
    public EmployeeView create(EmployeeRequest request) {
        if (request.getType() != null && !request.getType().isBlank()) {
            preAuthorize(EmployeeType.valueOf(request.getType().toUpperCase()));
        }

        if (employeeRepository.existsByUsername(request.getUsername())) {
            throw new EntityExistsException(String.format(existsByUsername, request.getUsername()));
        }
        Employee employee = employeeMapper.mapToEmployee(request);

        return employeeMapper.mapToEmployeeView(save(employee));
    }

    @Override
    public EmployeeView update(EmployeeRequest request, Long id) {
        if (request.getType() != null && !request.getType().isBlank()) {
            preAuthorize(EmployeeType.valueOf(request.getType().toUpperCase()));
        }

        if (employeeRepository.existsByUsernameAndIdNot(request.getUsername(), id)) {
            throw new EntityExistsException(String.format(existsByUsername, request.getUsername()));
        }
        Employee employee = findById(id);

        employeeMapper.mapToEmployee(request, employee);

        return employeeMapper.mapToEmployeeView(save(employee));
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundById, id))
        );
    }

    @Override
    public Employee findByIdAndType(Long id, EmployeeType type) {
        return employeeRepository.findByIdAndType(id, type).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundById, id))
        );
    }

    @Override
    public void deleteById(Long id) {
        Employee employee = findById(id);
        if (employee.getType() != null) {
            preAuthorize(employee.getType());
        }
        employeeRepository.deleteById(id);
    }

    private void preAuthorize(EmployeeType type) {
        switch (type) {
            case DIRECTOR, BRANCH_MANAGER, TARIFF_MANAGER, SIM_CARD_MANAGER -> preAuthorizeManager();
            case BRANCH_LEADER -> preAuthorizeBranchLeader();
            case WORKER -> preAuthorizeWorker();
        };
    }
    private void preAuthorizeManager() {
        if (!authService.hasAnyAuthority(CRUD_ALL, CRUD_MANAGER, CREATE_MANAGER)) {
            throw new AccessDeniedException(accessDenied);
        }
    }

    private void preAuthorizeBranchLeader() {
        if (!authService.hasAnyAuthority(CRUD_ALL, CRUD_BRANCH_LEADER, CREATE_BRANCH_LEADER)) {
            throw new AccessDeniedException(accessDenied);
        }
    }

    private void preAuthorizeWorker() {
        if (!authService.hasAnyAuthority(CRUD_ALL, CRUD_WORKER, CREATE_WORKER)) {
            throw new AccessDeniedException(accessDenied);
        }
    }
}
