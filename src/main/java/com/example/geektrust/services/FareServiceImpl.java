package com.example.geektrust.services;

import com.example.geektrust.models.Person;
import com.example.geektrust.models.PersonType;
import com.example.geektrust.models.Station;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class FareServiceImpl {

    public EnumMap<PersonType, Double> fareDetails;

    public FareServiceImpl() {
        this.fareDetails = new EnumMap<>(PersonType.class);
        fareDetails.put(PersonType.SENIOR_CITIZEN, 100.0);
        fareDetails.put(PersonType.ADULT, 200.0);
        fareDetails.put(PersonType.KID, 50.0);
    }
    public Double getFareDetails(Person p){
        return fareDetails.get(p.getType());
    }
}
