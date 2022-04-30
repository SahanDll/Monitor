package com.dev.mon.controller;


import com.dev.mon.util.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
public class CashController {
    @RequestMapping(value = "/balance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage> balance(@RequestParam String name) throws Exception {
        log.info("Balance for : {}", name);
        if(name != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(name + " with balance : "+Math.random(), 200));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("No data found", 100));
        }

    }

}
