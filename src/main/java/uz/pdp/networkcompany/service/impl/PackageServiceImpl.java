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
import uz.pdp.networkcompany.enums.USSDType;
import uz.pdp.networkcompany.mapper.PackageMapper;
import uz.pdp.networkcompany.repository.PackageRepository;
import uz.pdp.networkcompany.service.PackageService;
import uz.pdp.networkcompany.service.USSDService;

@Service
public class PackageServiceImpl implements PackageService {
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private USSDService ussdService;
    @Autowired
    private PackageMapper packageMapper;
    private final String existsByName = "Package with name = %s already exists";
    private final String existsByCode = "USSD with code = %s already exists";
    private final String notFoundById = "Package with id = %d not found";
    private final String notFoundByCode = "Package with code = %s not found";

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
        for (USSDType value : USSDType.values()) {
            if (value.getCode().equals(request.getUssdCode())) {
                throw new EntityExistsException(String.format(existsByCode, request.getUssdCode()));
            }
        }
        if (ussdService.existsByCode(request.getUssdCode())) {
            throw new EntityExistsException(String.format(existsByCode, request.getUssdCode()));
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

        for (USSDType value : USSDType.values()) {
            if (value.getCode().equals(request.getUssdCode())) {
                throw new EntityExistsException(String.format(existsByCode, request.getUssdCode()));
            }
        }
        if (ussdService.existsByCodeAndIdNot(request.getUssdCode(), pack.getUssd().getId())) {
            throw new EntityExistsException(String.format(existsByCode, request.getUssdCode()));
        }

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
    public Package findByCode(String code) {
        return packageRepository.findByUssdCode(code).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundByCode, code))
        );
    }

    @Override
    public boolean existsByCode(String code) {
        return packageRepository.existsByUssdCode(code);
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
