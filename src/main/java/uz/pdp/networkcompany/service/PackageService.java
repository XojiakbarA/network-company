package uz.pdp.networkcompany.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.networkcompany.dto.request.PackageRequest;
import uz.pdp.networkcompany.dto.view.pack.PackageView;
import uz.pdp.networkcompany.entity.Package;

public interface PackageService {
    Page<PackageView> getAll(Pageable pageable);

    PackageView getById(Long id);

    PackageView create(PackageRequest request);

    PackageView update(PackageRequest request, Long id);

    Package save(Package pack);

    Package findById(Long id);

    void deleteById(Long id);
}
