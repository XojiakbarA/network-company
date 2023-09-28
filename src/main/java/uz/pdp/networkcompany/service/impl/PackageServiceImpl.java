package uz.pdp.networkcompany.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.dto.request.PackageRequest;
import uz.pdp.networkcompany.dto.view.pack.PackageView;
import uz.pdp.networkcompany.entity.Package;
import uz.pdp.networkcompany.entity.Tariff;
import uz.pdp.networkcompany.mapper.PackageMapper;
import uz.pdp.networkcompany.repository.PackageRepository;
import uz.pdp.networkcompany.service.PackageService;

@Service
public class PackageServiceImpl implements PackageService {
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private PackageMapper packageMapper;
    private final String existsByName = "Package with name = %s already exists";
    private final String notFoundById = "Package with id = %d not found";

    @Override
    public Page<PackageView> getAll(Pageable pageable) {
        return packageRepository.findAll(pageable).map(p -> packageMapper.mapToPackageView(p));
    }

    @Override
    public PackageView getById(Long id) {
        return packageMapper.mapToPackageView(findById(id));
    }

    @Override
    public PackageView create(PackageRequest request) {
        if (packageRepository.existsByName(request.getName())) {
            throw new EntityExistsException(String.format(existsByName, request.getName()));
        }
        Package pack = packageMapper.mapToPackage(request);

        return packageMapper.mapToPackageView(save(pack));
    }

    @Override
    public PackageView update(PackageRequest request, Long id) {
        if (packageRepository.existsByNameAndIdNot(request.getName(), id)) {
            throw new EntityExistsException(String.format(existsByName, request.getName()));
        }
        Package pack = findById(id);

        packageMapper.mapToPackage(request, pack);

        return packageMapper.mapToPackageView(save(pack));
    }

    @Override
    public Package save(Package pack) {
        return packageRepository.save(pack);
    }

    @Override
    public Package findById(Long id) {
        return packageRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundById, id))
        );
    }

    @Override
    public void deleteById(Long id) {
        Package pack = findById(id);
        for (Tariff tariff : pack.getTariffs()) {
            pack.removeTariff(tariff);
        }
        packageRepository.deleteById(id);
    }
}
