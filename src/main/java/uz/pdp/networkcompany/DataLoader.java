package uz.pdp.networkcompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.entity.Client;
import uz.pdp.networkcompany.entity.Employee;
import uz.pdp.networkcompany.entity.Passport;
import uz.pdp.networkcompany.enums.ClientType;
import uz.pdp.networkcompany.enums.EmployeeType;
import uz.pdp.networkcompany.service.ClientService;
import uz.pdp.networkcompany.service.EmployeeService;

import java.util.Date;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createEmployee(EmployeeType.DIRECTOR);
        createEmployee(EmployeeType.SIM_CARD_MANAGER);
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
        employeeService.save(employee);
    }
    private void createClient(ClientType type) {
        Client client = new Client();
        client.setFirstName(type.name());
        client.setLastName(type.name());
        client.setUsername(type.name());
        client.setPassword(passwordEncoder.encode("123"));
        client.setType(type);
        Passport passport = new Passport();
        passport.setNumber(UUID.randomUUID().toString());
        passport.setDateOfBirth(new Date());
        passport.setDateOfIssue(new Date());
        passport.setDateOfExpiration(new Date());
        client.setPassport(passport);
        clientService.save(client);
    }
}
