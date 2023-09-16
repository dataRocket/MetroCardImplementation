package com.example.geektrust.models;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CentralStation extends Station{

    private int numOfPassengers;
    private Map<String, Integer> passengerLedger;

    // easy to get discount or total money also it is easily summarizable ?? is it though?
    private Map<String, Integer> moneyLedger;
    public CentralStation(String name, String type) {
        super(name, type);
        passengerLedger = new TreeMap<>();
        moneyLedger = new HashMap<>();
    }
    @Override
    public String summary() {
        return null;
    }
}
