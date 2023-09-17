package com.example.geektrust.services;

import com.example.geektrust.models.MetroCard;
import com.example.geektrust.models.Person;
import com.example.geektrust.models.Station;

import java.util.*;

public class TicketServiceImpl implements TicketService{

    private Map<String, Station> stations = null;

    private FareServiceImpl fareService = null;

    public TicketServiceImpl() {
        this.stations = new TreeMap<>();
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
        if(balanceNeeded < currBalance) {
                stations.get(origin.name).addToMoneyLedger(balanceNeeded, "FARE");
                metroCard.updateBalance( -1 * balanceNeeded);
        } else {
            // need to add tax for recharge
            stations.get(origin.name).addToMoneyLedger(balanceNeeded, "FARE");
            // add tax on recharge
            double rechargeVal = balanceNeeded - currBalance;
            double tax = (rechargeVal) * 0.02;
            stations.get(origin.name).addToMoneyLedger(tax, "TAX");
            metroCard.updateBalance(0.00);
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
