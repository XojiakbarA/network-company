package uz.pdp.networkcompany.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uz.pdp.networkcompany.dto.request.*;
import uz.pdp.networkcompany.dto.response.AuthResponse;
import uz.pdp.networkcompany.dto.response.Response;
import uz.pdp.networkcompany.dto.view.mb.MBView;
import uz.pdp.networkcompany.dto.view.minute.MinuteView;
import uz.pdp.networkcompany.dto.view.sms.SMSView;
import uz.pdp.networkcompany.service.*;

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
    @Autowired
    private SMSService smsService;
    @Autowired
    private MinuteService minuteService;
    @Autowired
    private MBService mbService;

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

    @PostMapping("/sms")
    @ResponseStatus(HttpStatus.CREATED)
    public Response sms(@Valid @RequestBody SMSRequest request, Authentication authentication) {
        SMSView sms = smsService.create(request, authentication.getName());

        return new Response(HttpStatus.CREATED.name(), sms);
    }

    @PostMapping("/minute")
    @ResponseStatus(HttpStatus.CREATED)
    public Response minute(@Valid @RequestBody MinuteRequest request, Authentication authentication) {
        MinuteView minute = minuteService.create(request, authentication.getName());

        return new Response(HttpStatus.CREATED.name(), minute);
    }

    @PostMapping("/mb")
    @ResponseStatus(HttpStatus.CREATED)
    public Response mb(@Valid @RequestBody MBRequest request, Authentication authentication) {
        MBView mb = mbService.create(request, authentication.getName());

        return new Response(HttpStatus.CREATED.name(), mb);
    }
}
