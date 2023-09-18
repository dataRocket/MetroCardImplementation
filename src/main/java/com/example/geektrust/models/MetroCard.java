package com.example.geektrust.models;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;

public class MetroCard {
    private final String id;
    private final Person personId;
    Double balance;

    boolean isActive = false;

    Integer journeys;

    Journey lastJourney;

    public MetroCard(String id, Person personId) {
        this.personId = personId;
        this.id = id;
        this.isActive = true;
        this.balance = 0.0;
        this.journeys = 0;
    }

    public Double getBalance() {
        return this.balance;
    }
    
    public AbstractMap.SimpleEntry<String, Double> updateBalance(Double rechargeAmount) {
        this.balance += rechargeAmount;
        return new AbstractMap.SimpleEntry("SUCCESS", this.balance);
    }

    public Journey addJourney(Station originStation, Station toStation, boolean isReturnJourney) {
        this.journeys += 1;
        Journey currJourney = new Journey(originStation, toStation);
        currJourney.setIsTwoWayComplete(isReturnJourney);
        this.lastJourney = currJourney;
        return this.lastJourney;
    }

    public boolean isReturnJourney(Station originStation) {
        Journey lastJourney = this.getJourney();
        return this.getJourney() != null
                && !this.getJourney().isTwoWayComplete()
                && this.getJourney().getToStation().equals(originStation);

    }

    public Journey getJourney() {
        return this.lastJourney;
    }
}
