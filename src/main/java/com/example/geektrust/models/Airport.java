package com.example.geektrust.models;

import com.example.geektrust.util.MapUtils;

import java.util.*;
import java.util.stream.Collectors;


public class Airport extends Station{

    private int numOfPassengers;
    private Map<PersonType, Integer> passengerLedger;

    // easy to get discount or total money also it is easily summarizable ?? is it though?
    private Map<String, Double> moneyLedger;
    public Airport(String name, String type) {
        super(name, type);
        passengerLedger = new EnumMap<>(PersonType.class);
        moneyLedger = new HashMap<>();
        moneyLedger.put("DISCOUNT", 0.0);
        moneyLedger.put("FARE", 0.0);
    }

    public boolean addToPersonLedger(PersonType personType) {
        passengerLedger.put(personType, passengerLedger.getOrDefault(personType, 0) + 1);
        return true;
    }

    public boolean addToMoneyLedger(Double amount, String type) {
           // System.out.println("adding money to ledger airprot: "+ amount);
            Double currAmount = moneyLedger.getOrDefault(type, 0.0);
            currAmount += amount;
            moneyLedger.put(type, currAmount);
            return true;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String summary() {
        Map<PersonType, Integer> sortedPassengers = MapUtils.sortByValue(this.passengerLedger);
        String totalCollection = "TOTAL_COLLECTION"+" "+"AIRPORT"+" ";
        List<Double> collection = moneyLedger.values().stream().toList();
        String result = collection.stream().map(Object::toString).collect(Collectors.joining(" "));
        totalCollection += result;
        System.out.println(totalCollection);
        System.out.println("PASSENGER_TYPE_SUMMARY");
        sortedPassengers.forEach((key, value) -> System.out.println(key + "  " + value));
        return "SUCCESS";
    }


}
