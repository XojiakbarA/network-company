package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.request.PackageRequest;
import uz.pdp.networkcompany.dto.view.pack.PackageView;
import uz.pdp.networkcompany.dto.view.pack.TariffView;
import uz.pdp.networkcompany.entity.Package;
import uz.pdp.networkcompany.entity.Tariff;
import uz.pdp.networkcompany.enums.DurationType;
import uz.pdp.networkcompany.enums.PackageType;

import java.util.stream.Collectors;

@Component
public class PackageMapper {
    public PackageView mapToPackageView(Package pack) {
        if (pack == null) return null;
        return PackageView.builder()
                .id(pack.getId())
                .price(pack.getPrice())
                .amount(pack.getAmount())
                .type(pack.getType())
                .durationType(pack.getDurationType())
                .leftover(pack.getLeftover())
                .tariffs(pack.getTariffs().stream().map(this::mapToTariffView).collect(Collectors.toSet()))
                .build();
    }
    public Package mapToPackage(PackageRequest request) {
        if (request == null) return null;
        Package pack = new Package();

        setAttributes(request, pack);

        return pack;
    }
    public void mapToPackage(PackageRequest request, Package pack) {
        setAttributes(request, pack);
    }
    private void setAttributes(PackageRequest request, Package pack) {
        if (request.getName() != null && !request.getName().isBlank()) {
            pack.setName(request.getName());
        }
        if (request.getPrice() != null) {
            pack.setPrice(request.getPrice());
        }
        if (request.getAmount() != null) {
            pack.setAmount(request.getAmount());
        }
        if (request.getType() != null && !request.getType().isBlank()) {
            pack.setType(PackageType.valueOf(request.getType().toUpperCase()));
        }
        if (request.getDurationType() != null && !request.getDurationType().isBlank()) {
            pack.setDurationType(DurationType.valueOf(request.getDurationType().toUpperCase()));
        }
    }

    private TariffView mapToTariffView(Tariff tariff) {
        if (tariff == null) return null;
        return TariffView.builder()
                .id(tariff.getId())
                .name(tariff.getName())
                .type(tariff.getType())
                .build();
    }
}
