package com.example.geektrust.models;

public abstract class Station {
    public String name;
    public String type;

    public Station(String name, String type) {
        this.name = name;
        this.type = type;
    }
    public abstract String summary();

    public abstract boolean addToPersonLedger(PersonType personType);

    public abstract boolean addToMoneyLedger(Double amount, String type);

    public abstract String getName();


}
