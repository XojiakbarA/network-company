package uz.pdp.networkcompany.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import uz.pdp.networkcompany.enums.ServiceType;
import uz.pdp.networkcompany.service.SIMCardService;
import uz.pdp.networkcompany.service.TakenPackageService;

import java.time.LocalDateTime;

@Component
public class Schedule {
    @Autowired
    private SIMCardService simCardService;
    @Autowired
    private TakenPackageService takenPackageService;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void payForTariff() {
        simCardService.findAll().forEach(s -> simCardService.payForTariff(s));
        simCardService.findAllByServiceType(ServiceType.MONTHLY)
                .forEach(s -> simCardService.payForServices(s));
    }

    @Scheduled(cron = "@daily")
    public void payForServicesDaily() {
        takenPackageService.findAllByExpirationDateBefore(LocalDateTime.now())
                .forEach(p -> takenPackageService.deleteById(p.getId()));
        simCardService.findAllByServiceType(ServiceType.DAILY)
                .forEach(s -> simCardService.payForServices(s));
    }
}
