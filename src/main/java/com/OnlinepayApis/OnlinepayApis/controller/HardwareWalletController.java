package com.OnlinepayApis.OnlinepayApis.controller;

import com.OnlinepayApis.OnlinepayApis.services.HardwareWalletService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hardware-wallet")
public class HardwareWalletController {

    private final HardwareWalletService service;

    public HardwareWalletController(HardwareWalletService service) {
        this.service = service;
    }

    @PostMapping("/open")
    public String openWallet() {
        return service.openWallet();
    }
}
