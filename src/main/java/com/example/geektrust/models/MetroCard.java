package com.example.geektrust.models;

import java.util.AbstractMap;
import java.util.Map;

public class MetroCard {
    private final String id;
    private final Person personId;
    Double balance;

    boolean isActive = false;

    Integer journeys;

    public MetroCard(String id, Person personId) {
        this.personId = personId;
        this.id = id;
        this.isActive = true;
    }
    
    public AbstractMap.SimpleEntry<String, Double> recharge(Double rechargeAmount) {
        this.balance += rechargeAmount;
        return new AbstractMap.SimpleEntry("SUCCESS", this.balance);
    }

}
