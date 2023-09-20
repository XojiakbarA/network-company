package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.view.branch.AddressView;
import uz.pdp.networkcompany.dto.view.branch.BranchView;
import uz.pdp.networkcompany.dto.view.branch.UserView;
import uz.pdp.networkcompany.entity.Address;
import uz.pdp.networkcompany.entity.Branch;
import uz.pdp.networkcompany.entity.User;

@Component
public class BranchMapper {
    public BranchView mapToBranchView(Branch branch) {
        if (branch == null) return null;
        return BranchView.builder()
                .id(branch.getId())
                .name(branch.getName())
                .phoneNumber(branch.getPhoneNumber())
                .address(mapToAddressView(branch.getAddress()))
                .leader(mapToUserView(branch.getLeader()))
                .build();
    }
    private AddressView mapToAddressView(Address address) {
        if (address == null) return null;
        return AddressView.builder()
                .id(address.getId())
                .region(address.getRegion())
                .district(address.getDistrict())
                .street(address.getStreet())
                .home(address.getHome())
                .build();
    }
    private UserView mapToUserView(User user) {
        if (user == null) return null;
        return UserView.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
