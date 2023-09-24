package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.request.AddressRequest;
import uz.pdp.networkcompany.dto.request.BranchRequest;
import uz.pdp.networkcompany.dto.view.branch.AddressView;
import uz.pdp.networkcompany.dto.view.branch.BranchView;
import uz.pdp.networkcompany.dto.view.branch.UserView;
import uz.pdp.networkcompany.entity.Address;
import uz.pdp.networkcompany.entity.Branch;
import uz.pdp.networkcompany.entity.Employee;

@Component
public class BranchMapper {
    public BranchView mapToBranchView(Branch branch) {
        if (branch == null) return null;
        return BranchView.builder()
                .id(branch.getId())
                .name(branch.getName())
                .phoneNumber(branch.getPhoneNumber())
                .address(mapToAddressView(branch.getAddress()))
                .leader(mapToEmployeeView(branch.getLeader()))
                .build();
    }
    public Branch mapToBranch(BranchRequest request) {
        if (request == null) return null;
        Branch branch = new Branch();

        setAttributes(request, branch);

        return branch;
    }
    public void mapToBranch(BranchRequest request, Branch branch) {
        setAttributes(request, branch);
    }
    private void setAttributes(BranchRequest request, Branch branch) {
        if (request.getName() != null && !request.getName().isBlank()) {
            branch.setName(request.getName());
        }
        if (request.getPhoneNumber() != null) {
            branch.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getAddress() != null) {
            Address address = new Address();

            if (branch.getAddress() != null) {
                address = branch.getAddress();
            }

            setAttributes(request.getAddress(), address);

            branch.setAddress(address);
        }
    }
    private void setAttributes(AddressRequest request, Address address) {
        if (request.getRegion() != null && !request.getRegion().isBlank()) {
            address.setRegion(request.getRegion());
        }
        if (request.getDistrict() != null && !request.getDistrict().isBlank()) {
            address.setDistrict(request.getDistrict());
        }
        if (request.getStreet() != null && !request.getStreet().isBlank()) {
            address.setStreet(request.getStreet());
        }
        if (request.getHome() != null && !request.getHome().isBlank()) {
            address.setHome(request.getHome());
        }
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
    private UserView mapToEmployeeView(Employee employee) {
        if (employee == null) return null;
        return UserView.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .build();
    }
}
