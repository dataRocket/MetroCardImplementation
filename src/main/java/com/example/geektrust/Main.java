package com.example.geektrust;

import com.example.geektrust.models.*;
import com.example.geektrust.services.TicketServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        TicketServiceImpl ticketService = new TicketServiceImpl();
        Station s1 = new CentralStation("CENTRAL", "CENTRAL");
        Station s2 = new Airport("AIRPORT", "AIRPORT");
        ticketService.registerStation(s1);
        ticketService.registerStation(s2);
        Person kid = new Person(PersonType.KID, "kid");
        Person adult = new Person(PersonType.ADULT, "adult");
        Person senior = new Person(PersonType.SENIOR_CITIZEN, "senior");
        Map<String, MetroCard> metroCardMap = new HashMap<>();

        //Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
                String line = sc.nextLine();
                String[] commandArgs = line.split(" ");
                String command = commandArgs[0];
                //System.out.println("Command;" + line);
                switch(command) {
                    case "BALANCE" :
                        String cardName = commandArgs[1];
                        double recharge = Double.parseDouble(commandArgs[2]);
                        if (metroCardMap.get(cardName) == null) {
                           MetroCard tmp = new MetroCard(cardName, kid);
                           metroCardMap.put(cardName, tmp);
                        }
                        ticketService.recharge(metroCardMap.get(cardName), recharge);
                        break;
                    case "CHECK_IN" :
                        String metroCard = commandArgs[1];
                        String personVal = commandArgs[2];
                        String stationVal = commandArgs[3];
                        ticketService.checkIn(metroCardMap.get(metroCard),
                                stationVal.equals("CENTRAL") ? s1 : s2,
                                new Person(PersonType.getText(personVal), personVal));
                        break;

                    case "PRINT_SUMMARY" :
                        ticketService.generateSummary();
                        break;
                }
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            System.out.println("Failure");
        }

    }
}
