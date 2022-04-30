package com.dev.mon.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "CASH")
//@RibbonClient(name = "CASH")
@Service
public interface CashProxy {
    @GetMapping("/v1/balance")
    String cashBalance(@RequestParam("name") String name);
}