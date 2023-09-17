package com.example.geektrust.models;

public class Person {
    private final PersonType type;
    private final String id;
     public Person(PersonType type, String id) {
         this.type = type;
         this.id = id;
     }

    public PersonType getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
