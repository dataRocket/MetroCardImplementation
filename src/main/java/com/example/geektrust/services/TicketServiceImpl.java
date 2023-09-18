package com.example.geektrust.services;

import com.example.geektrust.models.Journey;
import com.example.geektrust.models.MetroCard;
import com.example.geektrust.models.Person;
import com.example.geektrust.models.Station;

import java.util.*;

public class TicketServiceImpl implements TicketService{

    private Map<String, Station> stations = null;

    private FareServiceImpl fareService = null;

    public TicketServiceImpl() {
        this.stations = new LinkedHashMap<>();
        this.fareService = new FareServiceImpl();
    }
    @Override
    public AbstractMap.SimpleEntry<String, Double> recharge(MetroCard metrocard, double rechargeAmount) {
        metrocard.updateBalance(rechargeAmount);
        return new AbstractMap.SimpleEntry<>("SUCCESS", metrocard.getBalance());
    }

    @Override
    public AbstractMap.SimpleEntry<String, Double> checkIn(MetroCard metroCard, Station origin, Person person) {
        Double balanceNeeded = fareService.getFareDetails(person);
        Double currBalance = metroCard.getBalance();
        boolean isReturnJourney = metroCard.isReturnJourney(origin);
        metroCard.addJourney(origin,
                "AIRPORT".equals(origin.getName()) ? stations.get("CENTRAL") : stations.get("AIRPORT"),
                isReturnJourney);
        if (isReturnJourney) {
            stations.get(origin.name).addToMoneyLedger(balanceNeeded/2, "DISCOUNT");
            balanceNeeded = balanceNeeded / 2;
        }
        if(balanceNeeded <= currBalance) {
                stations.get(origin.name).addToMoneyLedger(balanceNeeded, "FARE");
                metroCard.updateBalance( -1 * balanceNeeded);
        } else {
            // need to add tax for recharge
            stations.get(origin.name).addToMoneyLedger(balanceNeeded, "FARE");
            // add tax on recharge
            double rechargeVal = balanceNeeded - currBalance;
            double tax = (rechargeVal * 2) / 100;
            System.out.println("Adding tax value: "+ tax + "for recharge : "+ rechargeVal);
            stations.get(origin.name).addToMoneyLedger(tax, "FARE");
            metroCard.updateBalance(-1 * metroCard.getBalance());
        }
        stations.get(origin.name).addToPersonLedger(person.getType());
        return new AbstractMap.SimpleEntry<>("SUCCESS", metroCard.getBalance());
    }

    private boolean isSufficientBalance(Double balance, Person person) {
        return balance >= fareService.getFareDetails(person);
    }

    @Override
    public void generateSummary() {
        stations.forEach((key, value) -> value.summary());
    }

    public void registerStation(Station s) {
        this.stations.put(s.type, s);
    }
}
