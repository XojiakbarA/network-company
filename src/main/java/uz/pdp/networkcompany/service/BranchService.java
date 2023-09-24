package uz.pdp.networkcompany.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.networkcompany.dto.request.BranchRequest;
import uz.pdp.networkcompany.dto.request.SetLeaderRequest;
import uz.pdp.networkcompany.dto.view.branch.BranchView;
import uz.pdp.networkcompany.entity.Branch;

public interface BranchService {
    Page<BranchView> getAll(Pageable pageable);

    BranchView getById(Long id);

    BranchView create(BranchRequest request);

    BranchView update(BranchRequest request, Long id);

    BranchView setLeader(SetLeaderRequest request, Long id);

    Branch save(Branch branch);

    Branch findById(Long id);

    void deleteById(Long id);
}
