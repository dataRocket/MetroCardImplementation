package com.example.geektrust.models;

public enum PersonType {
    ADULT,
    SENIOR_CITIZEN,
    KID;

    public static PersonType getText(String args) {
        return switch (args) {
            case "ADULT" -> ADULT;
            case "KID" -> KID;
            case "SENIOR_CITIZEN" -> SENIOR_CITIZEN;
            default -> null;
        };
    }
}
