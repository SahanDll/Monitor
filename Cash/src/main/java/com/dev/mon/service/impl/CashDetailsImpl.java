package com.dev.mon.service.impl;

import com.dev.mon.service.CashDetails;
import org.springframework.stereotype.Service;

@Service
public class CashDetailsImpl implements CashDetails {
    @Override
    public String getCashDetails(String name) {
        return name + " with balance : " + getBalance(name);
    }

    @Override
    public int getSum(int x, int y) {
        return x+y;
    }

    public double getBalance(String name){
        return Math.random();
    }
}
