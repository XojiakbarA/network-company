package uz.pdp.networkcompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.entity.Client;
import uz.pdp.networkcompany.entity.Employee;
import uz.pdp.networkcompany.enums.ClientType;
import uz.pdp.networkcompany.enums.EmployeeType;
import uz.pdp.networkcompany.repository.ClientRepository;
import uz.pdp.networkcompany.repository.EmployeeRepository;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createEmployee(EmployeeType.DIRECTOR);
        createEmployee(EmployeeType.TARIFF_MANAGER);
        createEmployee(EmployeeType.BRANCH_MANAGER);
        createEmployee(EmployeeType.BRANCH_LEADER);
        createEmployee(EmployeeType.WORKER);
        createClient(ClientType.PHYSICAL);
        createClient(ClientType.LEGAL);
    }

    private void createEmployee(EmployeeType type) {
        Employee employee = new Employee();
        employee.setFirstName(type.name());
        employee.setLastName(type.name());
        employee.setUsername(type.name());
        employee.setPassword(passwordEncoder.encode("123"));
        employee.setType(type);
        employeeRepository.save(employee);
    }
    private void createClient(ClientType type) {
        Client client = new Client();
        client.setFirstName(type.name());
        client.setLastName(type.name());
        client.setUsername(type.name());
        client.setPassword(passwordEncoder.encode("123"));
        client.setType(type);
        clientRepository.save(client);
    }
}
