package uz.pdp.networkcompany.service.impl;

import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.service.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class USSDRouteServiceImpl implements USSDRouteService {
    @Autowired
    private SIMCardService simCardService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private PackageService packageService;

    @Override
    public Map<String, String> routeUSSDCode(String code, String username) {
        switch (code) {
            case "*101#" -> {
                String balance = simCardService.getBalance(username);
                return new HashMap<>(Map.of("balance", balance));
            }
            case "*102#" -> {
                String tariffName = simCardService.getTariffName(username);
                return new HashMap<>(Map.of("tariffName", tariffName));
            }
            case "*103#" -> {
                String limit = simCardService.getMinuteLimit(username);
                return new HashMap<>(Map.of("minuteLimit", limit));
            }
            case "*104#" -> {
                String limit = simCardService.getMBLimit(username);
                return new HashMap<>(Map.of("mbLimit", limit));
            }
            case "*105#" -> {
                String limit = simCardService.getSMSLimit(username);
                return new HashMap<>(Map.of("smsLimit", limit));
            }
        }

        if (tariffService.existsByCode(code)) {
            String string = simCardService.setTariff(username, code);
            return new HashMap<>(Map.of("message", string));
        }

        if (serviceService.existsByCode(code)) {
            String string = simCardService.addService(username, code);
            return new HashMap<>(Map.of("message", string));
        }

        if (packageService.existsByCode(code)) {
            String string = simCardService.addPackage(username, code);
            return new HashMap<>(Map.of("message", string));
        }

        throw new EntityActionVetoException("wrong ussd code", null);
    }
}
