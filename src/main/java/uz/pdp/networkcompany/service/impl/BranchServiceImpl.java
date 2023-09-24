package uz.pdp.networkcompany.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.dto.request.BranchRequest;
import uz.pdp.networkcompany.dto.request.LeaderRequest;
import uz.pdp.networkcompany.dto.view.branch.BranchView;
import uz.pdp.networkcompany.entity.Branch;
import uz.pdp.networkcompany.entity.Employee;
import uz.pdp.networkcompany.enums.EmployeeType;
import uz.pdp.networkcompany.mapper.BranchMapper;
import uz.pdp.networkcompany.repository.BranchRepository;
import uz.pdp.networkcompany.service.BranchService;
import uz.pdp.networkcompany.service.EmployeeService;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private BranchMapper branchMapper;
    private final String existsByName = "Branch with name = %s already exists";
    private final String notFoundById = "Branch with id = %d not found";

    @Override
    public Page<BranchView> getAll(Pageable pageable) {
        return branchRepository.findAll(pageable).map(b -> branchMapper.mapToBranchView(b));
    }

    @Override
    public BranchView getById(Long id) {
        return branchMapper.mapToBranchView(findById(id));
    }

    @Override
    public BranchView create(BranchRequest request) {
        if (branchRepository.existsByName(request.getName())) {
            throw new EntityExistsException(String.format(existsByName, request.getName()));
        }
        Branch branch = branchMapper.mapToBranch(request);

        return branchMapper.mapToBranchView(save(branch));
    }

    @Override
    public BranchView update(BranchRequest request, Long id) {
        if (branchRepository.existsByNameAndIdNot(request.getName(), id)) {
            throw new EntityExistsException(String.format(existsByName, request.getName()));
        }
        Branch branch = findById(id);

        branchMapper.mapToBranch(request, branch);

        return branchMapper.mapToBranchView(save(branch));
    }

    @Override
    public BranchView setLeader(LeaderRequest request, Long id) {
        Branch branch = findById(id);

        Employee employee = employeeService.findByIdAndType(request.getLeaderId(), EmployeeType.BRANCH_LEADER);

        branch.setLeader(employee);

        return branchMapper.mapToBranchView(save(branch));
    }

    @Override
    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public Branch findById(Long id) {
        return branchRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundById, id))
        );
    }

    @Override
    public void deleteById(Long id) {
        if (!branchRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(notFoundById, id));
        }
        branchRepository.deleteById(id);
    }
}
