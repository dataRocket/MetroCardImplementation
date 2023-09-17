package com.example.geektrust.models;

import java.util.AbstractMap;
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
    }

    public Double getBalance() {
        return this.balance;
    }
    
    public AbstractMap.SimpleEntry<String, Double> updateBalance(Double rechargeAmount) {
        this.balance += rechargeAmount;
        return new AbstractMap.SimpleEntry("SUCCESS", this.balance);
    }

    public Optional<Journey> addJourney(Station originStation) {
        this.journeys += 1;
        Optional<Journey> lastJourney = this.getJourney();
        lastJourney.ifPresent(element-> {
            if (!element.isTwoWayComplete() && element.getToStation().equals(originStation)) {
                Journey currJourney = new Journey(originStation, null);
                currJourney.setIsTwoWayComplete(true);
            }
        });
        return Optional.of(this.lastJourney);
    }

    public boolean isReturnJourneyCompleted() {
        return this.lastJourney.isTwoWayComplete();
    }

    public Optional<Journey> getJourney() {
        return Optional.of(this.lastJourney);
    }
}
