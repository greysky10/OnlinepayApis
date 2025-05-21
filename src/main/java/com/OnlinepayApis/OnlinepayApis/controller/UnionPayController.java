package com.OnlinepayApis.OnlinepayApis.controller;

import com.OnlinepayApis.OnlinepayApis.services.UnionPayService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unionpay")
public class UnionPayController {

    private final UnionPayService unionPayService;

    public UnionPayController(UnionPayService unionPayService) {
        this.unionPayService = unionPayService;
    }

    @PostMapping("/pay")
    public String pay() {
        return unionPayService.onlinePay();
    }
}
