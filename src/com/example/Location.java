package com.example;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private final int locationID;
    private final String description;
    private final Map<String,Integer> exits;

    public Location(int locationID, String description, Map<String, Integer> tempExit) {
        this.locationID = locationID;
        this.description = description;
        if(tempExit != null){
            this.exits = new HashMap<>(tempExit);
        }else {
            this.exits = new HashMap<>();

        }
        this.exits.put("Q",0);
    }

    protected void addExit(String direction, int location){
        exits.put(direction,location);
    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new HashMap<>(exits); // Instead of returning and exits map we are creating a new hashMap and passing out exits in the constructor
    // so this means nothing can change the exits, bcs we are giving a copy
    }
}
