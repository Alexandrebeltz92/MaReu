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
    static MeetingRoom zeusRoom = new MeetingRoom(0,"Zeus Room","Zeus Room");
    static MeetingRoom hadesRoom = new MeetingRoom(0,"Hades Room","Hades Room");
    static MeetingRoom hermesRoom = new MeetingRoom(0,"Hermes Room","Hermes Room");
    static MeetingRoom apolloRoom = new MeetingRoom(0,"Apollo Room","Apollo Room");
    static MeetingRoom poseidonRoom = new MeetingRoom(0,"Poseidon Room","Poseidon Room");

    //EMPLOYEE GENERATOR
    static Employee manuel = new Employee(0,"Manuel","Godoy","manuel@gmail.com");
    static Employee louis = new Employee(1,"Louis","De Richet","louis@gmail.com");
    static Employee emily = new Employee(2,"Emily","Hillsborrow","emily@gmail.com");
    static Employee christoph = new Employee(3,"Johan Christoph","von Wollner","johan@gmail.com");
    static Employee gregory = new Employee(4,"Gregory","Holm","gregory@gmail.com");
    static Employee william = new Employee(5,"William Alexander","Mortimer","william@gmail.com");

    public static ArrayList<Employee> participants1 = new ArrayList<>(Arrays.asList(manuel,louis, emily, christoph, william));
    public static ArrayList<Employee> participants2 = new ArrayList<>(Arrays.asList(manuel,louis, gregory, william));
    public static ArrayList<Employee> participants3 = new ArrayList<>(Arrays.asList(louis, emily, christoph, gregory));
    public static ArrayList<Employee> participantsAll = new ArrayList<>(Arrays.asList(manuel,emily,louis,christoph,gregory,william));

    //?
    static List<Employee> generateMeetingParticipants() { return  new ArrayList<>(participantsAll);}

    //MEETING GENERATOR
    public static List<Meeting> Dummy_Meetings = Arrays.asList(
            new Meeting(1,"Subject",R.drawable.ic_baseline_circle_24,zeusRoom, addDays(Calendar.getInstance().getTime()),participants1),
            new Meeting(2,"Subject",R.drawable.ic_baseline_circle_24,hadesRoom,addDays(Calendar.getInstance().getTime()),participants1),
            new Meeting(3,"Subject",R.drawable.ic_baseline_circle_24,hermesRoom,addDays(Calendar.getInstance().getTime()),participants2),
            new Meeting(4,"Subject",R.drawable.ic_baseline_circle_24,apolloRoom,addDays(Calendar.getInstance().getTime()),participants3),
            new Meeting(5,"Subject",R.drawable.ic_baseline_circle_24,hadesRoom,addDays(Calendar.getInstance().getTime()),participants2),
            new Meeting(6,"Subject",R.drawable.ic_baseline_circle_24,poseidonRoom,addDays(Calendar.getInstance().getTime()),participants3),
            new Meeting(7,"Subject",R.drawable.ic_baseline_circle_24,zeusRoom,addDays(Calendar.getInstance().getTime()),participants1),
            new Meeting(8,"Subject",R.drawable.ic_baseline_circle_24,hermesRoom,addDays(Calendar.getInstance().getTime()),participants2),
            new Meeting(9,"Subject",R.drawable.ic_baseline_circle_24,poseidonRoom,addDays(Calendar.getInstance().getTime()),participants1)
    );

    static List<Meeting> generateMeetings() { return new ArrayList<>(Dummy_Meetings); }

    public static List<Meeting> Dummy_Random_Meetings = Arrays.asList(
            new Meeting(10,"Subject",R.drawable.ic_baseline_circle_24,hadesRoom,addDays(Calendar.getInstance().getTime()),participants1),
            new Meeting(11,"Subject",R.drawable.ic_baseline_circle_24,apolloRoom,addDays(Calendar.getInstance().getTime()),participants2),
            new Meeting(12,"Subject",R.drawable.ic_baseline_circle_24,poseidonRoom,addDays(Calendar.getInstance().getTime()),participants3),
            new Meeting(13,"Subject",R.drawable.ic_baseline_circle_24,hermesRoom,addDays(Calendar.getInstance().getTime()),participants1),
            new Meeting(14,"Subject",R.drawable.ic_baseline_circle_24,zeusRoom,addDays(Calendar.getInstance().getTime()),participants2)
    );

    public static Date addDays(Date d)  {
        int days = new Random().nextInt(3);
        d.setTime(d.getTime() + days * 1000L * 60L * 60L * 24L);
        return d;
    }
}
