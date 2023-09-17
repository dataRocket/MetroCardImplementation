package com.example.geektrust.models;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Airport extends Station{

    private int numOfPassengers;
    private Map<PersonType, Integer> passengerLedger;

    // easy to get discount or total money also it is easily summarizable ?? is it though?
    private Map<String, Double> moneyLedger;
    public Airport(String name, String type) {
        super(name, type);
        passengerLedger = new EnumMap<>(PersonType.class);
        moneyLedger = new HashMap<>();
    }

    public boolean addToPersonLedger(PersonType personType) {
        passengerLedger.put(personType, passengerLedger.getOrDefault(personType, 0) + 1);
        return true;
    }

    public boolean addToMoneyLedger(Double amount, String type) {
            Double currAmount = moneyLedger.getOrDefault(type, 0.0);
            currAmount += amount;
            moneyLedger.put(type, amount);
            return true;
    }
    @Override
    public String summary() {
        return null;
    }


}
