package com.dev.mon.controller;

import com.dev.mon.annotation.Matrix;
import com.dev.mon.model.Data;
import com.dev.mon.proxy.CashProxy;
import com.dev.mon.util.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    @Lazy
    CashProxy cashProxy;

    @Value("${defaultName}")
    private String defaultName;

    @Matrix(name = "userInfo", enableLogs = true)
    @RequestMapping(value = "/userInfo", method = {RequestMethod.GET,RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage> userInfo() throws Exception {
        Data data = new Data();

        data.setName(getName());
        //data.setCash(cashProxy.cashBalance(defaultName));

        log.info(data.toString());

        if(data != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(data.toString(), 200));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("No data found", 100));
        }

    }

    @Matrix(name = "getName", enableLogs = true)
    private String getName(){

        return defaultName;
    }

}
