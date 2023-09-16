package com.example.geektrust.models;

public abstract class Station {
    public String name;
    public String type;

    public Station(String name, String type) {
        this.name = name;
        this.type = type;
    }
    abstract String summary();
}
