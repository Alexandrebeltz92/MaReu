package com.example.mareu.model;

public class MeetingRoom {

    private int id;
    private String place, name;

    public MeetingRoom(int id, String place, String name) {
        this.id = id;
        this.place = place;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
