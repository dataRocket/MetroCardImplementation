package com.example.geektrust.services;

import com.example.geektrust.models.MetroCard;
import com.example.geektrust.models.Person;
import com.example.geektrust.models.Station;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public interface TicketService {

    public AbstractMap.SimpleEntry<String, Double> recharge(MetroCard metrocard, double rechargeAmount);
    public AbstractMap.SimpleEntry<String, Double> checkIn(MetroCard metroCard, Station origin, Person person);

    public void generateSummary();
}
