package com.example.geektrust.models;

import java.util.Optional;
import java.util.UUID;

public class Journey {
    private final String journeyId;
    private final Station toStation;
    private final Station fromStation;

    private boolean isTwoWayComplete;

    public Journey(Station fromStation, Station toStation) {
        journeyId = UUID.randomUUID().toString();
        this.toStation = toStation;
        this.fromStation = fromStation;
        isTwoWayComplete = false;
    }

    public boolean isTwoWayComplete() {
        return isTwoWayComplete;
    }

    public void setIsTwoWayComplete(boolean flag){
        this.isTwoWayComplete = flag;
    }

    public Station getToStation() {
        return toStation;
    }

    public Station getFromStation() {
        return fromStation;
    }
}
