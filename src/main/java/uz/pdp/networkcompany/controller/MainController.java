package uz.pdp.networkcompany.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uz.pdp.networkcompany.dto.request.AddAmountRequest;
import uz.pdp.networkcompany.dto.request.LoginRequest;
import uz.pdp.networkcompany.dto.response.AuthResponse;
import uz.pdp.networkcompany.dto.response.Response;
import uz.pdp.networkcompany.service.AuthService;
import uz.pdp.networkcompany.service.SIMCardService;
import uz.pdp.networkcompany.service.USSDRouteService;

import java.util.Map;

@RestController
@RequestMapping
public class MainController {
    @Autowired
    private AuthService authService;
    @Autowired
    private USSDRouteService ussdRouteService;
    @Autowired
    private SIMCardService simCardService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);

        return new AuthResponse(token, HttpStatus.OK.name());
    }

    @GetMapping("/ussd")
    @ResponseStatus(HttpStatus.OK)
    public Response ussd(@RequestParam String code, Authentication authentication) {
        Map<String, String> map = ussdRouteService.routeUSSDCode(code, authentication.getName());

        return new Response(HttpStatus.OK.name(), map);
    }

    @PutMapping("/balance")
    @ResponseStatus(HttpStatus.OK)
    public Response addBalance(@Valid @RequestBody AddAmountRequest request, Authentication authentication) {
        String string = simCardService.addBalance(request, authentication.getName());

        return new Response(HttpStatus.OK.name(), string);
    }
}
