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

    static MeetingRoom zeusRoom = new MeetingRoom(0,"ZeusRoom","ZeusRoom");
    static MeetingRoom zeusRoom = new MeetingRoom(0,"ZeusRoom","ZeusRoom");
    static MeetingRoom zeusRoom = new MeetingRoom(0,"ZeusRoom","ZeusRoom");
    static MeetingRoom zeusRoom = new MeetingRoom(0,"ZeusRoom","ZeusRoom");
    static MeetingRoom zeusRoom = new MeetingRoom(0,"ZeusRoom","ZeusRoom");

    static ArrayList<Employee> ap = new ArrayList<Employee>();
    ap.add(david);

    static Employee david = new Employee(0,"David","LaSalle","david@gmail.com");

    public static List<Meeting> Dummy_Meetings = Arrays.asList(
            new Meeting(1,"Subject",R.drawable.ic_baseline_circle_24,zeusRoom, addDays(Calendar.getInstance().getTime()),),
            new Meeting(2,"Subject",R.drawable.ic_baseline_circle_24,"Hades Room",addDays(Calendar.getInstance().getTime()),"antoine@gmail.com, chris@gmail.com"),
            new Meeting(3,"Subject",R.drawable.ic_baseline_circle_24,"Hermes Room",addDays(Calendar.getInstance().getTime()),"robin@gmail.com, marvin@gmail.com, antoine@gmail.com, gaelle@gmail.com"),
            new Meeting(4,"Subject",R.drawable.ic_baseline_circle_24,"Apollo Room",addDays(Calendar.getInstance().getTime()),"etienne@gmail.com, theo@gmail.com, agathe@gmail.com"),
            new Meeting(5,"Subject",R.drawable.ic_baseline_circle_24,"Hades Room",addDays(Calendar.getInstance().getTime()),"marvin@gmail.com, antoine@gmail.com, theo@gmail.com, agathe@gmail.com"),
            new Meeting(6,"Subject",R.drawable.ic_baseline_circle_24,"Poseidon Room",addDays(Calendar.getInstance().getTime()),"antoine@gmail.com, theo@gmail.com, agathe@gmail.com"),
            new Meeting(7,"Subject",R.drawable.ic_baseline_circle_24,"Zeus Room",addDays(Calendar.getInstance().getTime()),"marvin@gmail.com, antoine@gmail.com, zeus@gmail.com, hades@gmail.com"),
            new Meeting(8,"Subject",R.drawable.ic_baseline_circle_24,"Hermes Room",addDays(Calendar.getInstance().getTime()),"hera@gmail.com, thibault@gmail.com, timon@gmail.com"),
            new Meeting(9,"Subject",R.drawable.ic_baseline_circle_24,"Poseidon Room",addDays(Calendar.getInstance().getTime()),"hermes@gmail.com, ares@gmail.com, pierre@gmail.com")
    );

    static List<Meeting> generateMeetings() { return new ArrayList<>(Dummy_Meetings); }

    public static List<Meeting> Dummy_Random_Meetings = Arrays.asList(
            new Meeting(10,"Subject",R.drawable.ic_baseline_circle_24,"Hades Room",addDays(Calendar.getInstance().getTime())," mark@gmail.com, justin@gmail.com, chris@gmail.com"),
            new Meeting(11,"Subject",R.drawable.ic_baseline_circle_24,"Apollo Room",addDays(Calendar.getInstance().getTime()),"antoine@gmail.com, chris@gmail.com, agathe@gmail.com"),
            new Meeting(12,"Subject",R.drawable.ic_baseline_circle_24,"Poseidon Room",addDays(Calendar.getInstance().getTime()),"robin@gmail.com, marvin@gmail.com, antoine@gmail.com, gaelle@gmail.com"),
            new Meeting(13,"Subject",R.drawable.ic_baseline_circle_24,"Hermes Room",addDays(Calendar.getInstance().getTime()),"etienne@gmail.com, theo@gmail.com, agathe@gmail.com"),
            new Meeting(14,"Subject",R.drawable.ic_baseline_circle_24,"Zeus Room",addDays(Calendar.getInstance().getTime()),"etienne@gmail.com, theo@gmail.com, agathe@gmail.com")
    );

    public static Date addDays(Date d)  {
        int days = new Random().nextInt(3);
        d.setTime(d.getTime() + days * 1000L * 60L * 60L * 24L);
        return d;
    }
}
