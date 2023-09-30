package uz.pdp.networkcompany.service;

import java.util.Map;

public interface USSDRouteService {
    Map<String, String> routeUSSDCode(String code, String username);
}
