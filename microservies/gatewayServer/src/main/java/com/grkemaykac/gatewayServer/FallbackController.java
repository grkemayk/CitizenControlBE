package com.grkemaykac.gatewayServer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @RequestMapping(value = "/fallbackAuthService")
    public String fallbackAuthService() {
        return "Authentication Service Not Available";
    }
    @RequestMapping(value = "/fallbackUserCitizenService")
    public String fallbackUserCitizenService() {
        return "Citizen Service Not Available";
    }

}
