package com.example.mareu.service;

import com.example.mareu.R;
import com.example.mareu.model.Employee;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public abstract class DummyMeetingGenerator {

    //ROOM GENERATOR

    static MeetingRoom zeusRoom = new MeetingRoom(1, "Zeus Room", "Zeus Room");
    static MeetingRoom hadesRoom = new MeetingRoom(2, "Hades Room", "Hades Room");
    static MeetingRoom hermesRoom = new MeetingRoom(3, "Hermes Room", "Hermes Room");
    static MeetingRoom apolloRoom = new MeetingRoom(4, "Apollo Room", "Apollo Room");
    static MeetingRoom poseidonRoom = new MeetingRoom(5, "Poseidon Room", "Poseidon Room");

    static List<MeetingRoom> sMeetingRoomList = new ArrayList<>(Arrays.asList(zeusRoom, hadesRoom, hermesRoom, apolloRoom, poseidonRoom));

    public static List<MeetingRoom> generateMeetingRoom() {
        return new ArrayList<>(sMeetingRoomList);
    }

    //EMPLOYEE GENERATOR
    static Employee manuel = new Employee(1, "Manuel", "Godoy", "manuel@gmail.com");
    static Employee louis = new Employee(2, "Louis", "De Richet", "louis@gmail.com");
    static Employee emily = new Employee(3, "Emily", "Hillsborrow", "emily@gmail.com");
    static Employee christoph = new Employee(4, "Johan Christoph", "von Wollner", "johan@gmail.com");
    static Employee gregory = new Employee(5, "Gregory", "Holm", "gregory@gmail.com");
    static Employee william = new Employee(6, "William Alexander", "Mortimer", "william@gmail.com");

    public static ArrayList<Employee> participants1 = new ArrayList<>(Arrays.asList(manuel, louis, emily, christoph, william));
    public static ArrayList<Employee> participants2 = new ArrayList<>(Arrays.asList(manuel, louis, gregory, william));
    public static ArrayList<Employee> participants3 = new ArrayList<>(Arrays.asList(louis, emily, christoph, gregory));
    public static ArrayList<Employee> participantsAll = new ArrayList<>(Arrays.asList(manuel, emily, louis, christoph, gregory, william));

    public static List<Employee> generateMeetingParticipants() {
        return new ArrayList<>(participantsAll);
    }

    //MEETING GENERATOR
    public static List<Meeting> Dummy_Meetings = Arrays.asList(

    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(Dummy_Meetings);
    }

    public static List<Meeting> generateMeetingsRandomList() {
        return new ArrayList<>(Dummy_Random_Meetings);
    }

    public static List<Meeting> Dummy_Random_Meetings = Arrays.asList(
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, zeusRoom, addDays(Calendar.getInstance().getTime()), participants1),
            new Meeting(getRandomID(), "subject", R.drawable.ic_baseline_circle_24, hadesRoom, addDays(Calendar.getInstance().getTime()), participants1),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, hermesRoom, addDays(Calendar.getInstance().getTime()), participants2),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, apolloRoom, addDays(Calendar.getInstance().getTime()), participants3),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, hadesRoom, addDays(Calendar.getInstance().getTime()), participants2),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, poseidonRoom, addDays(Calendar.getInstance().getTime()), participants3),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, zeusRoom, addDays(Calendar.getInstance().getTime()), participants1),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, hermesRoom, addDays(Calendar.getInstance().getTime()), participants2),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, poseidonRoom, addDays(Calendar.getInstance().getTime()), participants1),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, hadesRoom, addDays(Calendar.getInstance().getTime()), participants1),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, apolloRoom, addDays(Calendar.getInstance().getTime()), participants2),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, poseidonRoom, addDays(Calendar.getInstance().getTime()), participants3),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, hermesRoom, addDays(Calendar.getInstance().getTime()), participants1),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, zeusRoom, addDays(Calendar.getInstance().getTime()), participants2)
    );

    public static Date addDays(Date d) {
        int days = new Random().nextInt(3);
        d.setTime(d.getTime() + days * 1000L * 60L * 60L * 24L);
        return d;
    }

    public static Employee getEmployee(String email) {
        for (Employee e : participantsAll) {
            if (e.getEmail().equalsIgnoreCase(email)) {
                return e;
            }
        }
        return null;
    }

    public static int getRandomID() {
        Random random = new Random();
        int val = random.nextInt(100);
        int oldValue = val;
        if (val == oldValue) {
            val = +1;
        }
        return val;
    }

}
