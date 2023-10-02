package uz.pdp.networkcompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.entity.*;
import uz.pdp.networkcompany.entity.Package;
import uz.pdp.networkcompany.enums.*;
import uz.pdp.networkcompany.service.*;

import java.util.Date;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private SIMCardService simCardService;
    @Autowired
    private PassportService passportService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private PackageService packageService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createEmployee(EmployeeType.DIRECTOR);
        createEmployee(EmployeeType.SIM_CARD_MANAGER);
        createEmployee(EmployeeType.TARIFF_MANAGER);
        createEmployee(EmployeeType.BRANCH_MANAGER);
        createEmployee(EmployeeType.SERVICE_MANAGER);
        createEmployee(EmployeeType.PACKAGE_MANAGER);
        createEmployee(EmployeeType.BRANCH_LEADER);
        createEmployee(EmployeeType.WORKER);
        createClient(ClientType.PHYSICAL);
        createClient(ClientType.LEGAL);
        createTariff("Tariff 1", ClientType.PHYSICAL, 50_000D, 2000D,
                500, 4096, 100, 80D, 80D, 80D, "*10*1#");
        createTariff("Tariff 2", ClientType.LEGAL, 30_000D, 2000D,
                300, 2048, 80, 80D, 80D, 80D, "*10*2#");
        createCategory();
        createService("Service 1", 1900D, ServiceType.DAILY, "*20*1#");
        createService("Service 2", 5600D, ServiceType.MONTHLY, "*20*2#");
        createSIMCard(998951112233L, 10_000D, 1L, 1L);
        createSIMCard(998953332211L, 5_000D, 2L, 1L);
        createPackage("Package 1", 8000D, 512, PackageType.MB, DurationType.WEEK, "*30*1#");
        createPackage("Package 2", 3000D, 70, PackageType.SMS, DurationType.WEEK, "*30*2#");
        createPackage("Package 3", 5000D, 100, PackageType.MINUTE, DurationType.WEEK, "*30*3#");
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

    private void createTariff(String name, ClientType type, Double price, Double connectionPrice,
                              Integer minuteLimit, Integer mbLimit, Integer smsLimit,
                              Double minutePrice, Double mbPrice, Double smsPrice, String ussdCode) {
        Tariff tariff = new Tariff();
        tariff.setName(name);
        tariff.setType(type);
        tariff.setPrice(price);
        tariff.setConnectionPrice(connectionPrice);
        tariff.setPerMonthMinuteLimit(minuteLimit);
        tariff.setPerMonthMBLimit(mbLimit);
        tariff.setPerMonthSMSLimit(smsLimit);
        tariff.setPerMinutePrice(minutePrice);
        tariff.setPerMBPrice(mbPrice);
        tariff.setPerSMSPrice(smsPrice);
        USSD ussd = new USSD();
        ussd.setCode(ussdCode);
        tariff.setUssd(ussd);
        tariffService.save(tariff);
    }

    private void createSIMCard(Long number, Double balance, Long passportId, Long tariffId) {
        SIMCard simCard = new SIMCard();
        simCard.setNumber(number);
        simCard.setBalance(balance);
        simCard.setActive(true);
        simCard.setPassport(passportService.findById(passportId));
        simCard.setTariff(tariffService.findById(tariffId));
        simCard.setMinuteLimit(2);
        simCardService.save(simCard);
    }

    private void createCategory() {
        Category category = new Category();
        category.setName("Category 1");
        categoryService.save(category);
    }

    private void createService(String name, Double price, ServiceType type, String code) {
        Service service = new Service();
        service.setName(name);
        service.setPrice(price);
        service.setType(type);
        service.setCategory(categoryService.findById(1L));
        USSD ussd = new USSD();
        ussd.setCode(code);
        service.setUssd(ussd);
        serviceService.save(service);
    }

    private void createPackage(String name, Double price, Integer amount, PackageType type, DurationType durationType, String code) {
        Package pack = new Package();
        pack.setName(name);
        pack.setPrice(price);
        pack.setAmount(amount);
        pack.setType(type);
        pack.setDurationType(durationType);
        USSD ussd = new USSD();
        ussd.setCode(code);
        pack.setUssd(ussd);
        packageService.save(pack);
    }
}
