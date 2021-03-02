package com.example.mareu.service;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> Dummy_Meetings = Arrays.asList(
            new Meeting(1,"Subject",R.drawable.ic_baseline_circle_24,"Zeus Room", Calendar.getInstance().getTime(),"alexandre@gmail.com, mark@gmail.com, justin@gmail.com"),
            new Meeting(2,"Subject",R.drawable.ic_baseline_circle_24,"Hades Room",Calendar.getInstance().getTime(),"antoine@gmail.com, chris@gmail.com"),
            new Meeting(3,"Subject",R.drawable.ic_baseline_circle_24,"Ares Room",Calendar.getInstance().getTime(),"robin@gmail.com, marvin@gmail.com, antoine@gmail.com, gaelle@gmail.com"),
            new Meeting(4,"Subject",R.drawable.ic_baseline_circle_24,"Apollo Room",Calendar.getInstance().getTime(),"etienne@gmail.com, theo@gmail.com, agathe@gmail.com"),
            new Meeting(5,"Subject",R.drawable.ic_baseline_circle_24,"Hades Room",Calendar.getInstance().getTime(),"marvin@gmail.com, antoine@gmail.com, theo@gmail.com, agathe@gmail.com"),
            new Meeting(6,"Subject",R.drawable.ic_baseline_circle_24,"Poseidon Room",Calendar.getInstance().getTime(),"antoine@gmail.com, theo@gmail.com, agathe@gmail.com"),
            new Meeting(7,"Subject",R.drawable.ic_baseline_circle_24,"Ares Room",Calendar.getInstance().getTime(),"marvin@gmail.com, antoine@gmail.com, zeus@gmail.com, hades@gmail.com"),
            new Meeting(8,"Subject",R.drawable.ic_baseline_circle_24,"Hermes Room",Calendar.getInstance().getTime(),"hera@gmail.com, thibault@gmail.com, timon@gmail.com"),
            new Meeting(9,"Subject",R.drawable.ic_baseline_circle_24,"Poseidon Room",Calendar.getInstance().getTime(),"hermes@gmail.com, ares@gmail.com, pierre@gmail.com")
    );

    static List<Meeting> generateMeetings() { return new ArrayList<>(Dummy_Meetings); }

    public static List<Meeting> Dummy_Random_Meetings = Arrays.asList(
            new Meeting(10,"Subject",R.drawable.ic_baseline_circle_24,"Hades Room",Calendar.getInstance().getTime()," mark@gmail.com, justin@gmail.com, chris@gmail.com"),
            new Meeting(11,"Subject",R.drawable.ic_baseline_circle_24,"Apollo Room",Calendar.getInstance().getTime(),"antoine@gmail.com, chris@gmail.com, agathe@gmail.com"),
            new Meeting(12,"Subject",R.drawable.ic_baseline_circle_24,"Poseidon Room",Calendar.getInstance().getTime(),"robin@gmail.com, marvin@gmail.com, antoine@gmail.com, gaelle@gmail.com"),
            new Meeting(13,"Subject",R.drawable.ic_baseline_circle_24,"Hermes Room",Calendar.getInstance().getTime(),"etienne@gmail.com, theo@gmail.com, agathe@gmail.com"),
            new Meeting(14,"Subject",R.drawable.ic_baseline_circle_24,"Zeus Room",Calendar.getInstance().getTime(),"etienne@gmail.com, theo@gmail.com, agathe@gmail.com")
    );
}
