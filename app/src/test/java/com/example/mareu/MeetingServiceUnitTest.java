package com.example.mareu;

import com.example.mareu.di.DI;
import com.example.mareu.model.Employee;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.DummyMeetingGenerator;
import com.example.mareu.service.MeetingApiService;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.mareu.service.DummyMeetingGenerator.getRandomID;
import static com.example.mareu.service.DummyMeetingGenerator.participants1;
import static com.example.mareu.service.DummyMeetingGenerator.participants2;
import static com.example.mareu.service.DummyMeetingGenerator.participants3;

import static org.junit.Assert.*;

public class MeetingServiceUnitTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> meetings = service.getRandomMeetingList();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.Dummy_Random_Meetings;
        assertThat( meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meeting = new Meeting(1, "Mon meeting Test", R.drawable.ic_baseline_circle_24, DummyMeetingGenerator.generateMeetingRoom().get(1), Calendar.getInstance().getTime(), participants1);
        service.createMeeting(meeting);
        Meeting meetingToDelete = service.getMeeting().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeeting().contains(meetingToDelete));
    }

    @Test
    public void createMeetingWithSuccess() {
        Meeting meetingToCreate = new Meeting(1, "Mon meeting Test", R.drawable.ic_baseline_circle_24, DummyMeetingGenerator.generateMeetingRoom().get(1), Calendar.getInstance().getTime(), participants1);
        service.createMeeting(meetingToCreate);
        assertTrue(service.getMeeting().contains(meetingToCreate));
    }

    @Test
    public void generateListMeetingWithSuccess() {
        List<Meeting> lMeetings = service.getRandomMeetingList();
        int listSize = lMeetings.size();
        assertEquals(14, listSize);
    }

    @Test
    public void createEmployeeWithSuccess() {
        ArrayList<Employee> mListEmployee = DummyMeetingGenerator.participantsAll;
        Employee employee = new Employee(10, "Diego", "Augusto", "email@gmail.com");
        mListEmployee.add(employee);
        assertTrue(mListEmployee.contains(employee));
    }

    @Test
    public void createMeetingRoomWithSuccess() {
        ArrayList<MeetingRoom> mMeetingRoom = (ArrayList<MeetingRoom>) DummyMeetingGenerator.generateMeetingRoom();
        MeetingRoom meetingRoom = new MeetingRoom(10, "Emplacement", "Nom");
        mMeetingRoom.add(meetingRoom);
        assertTrue(mMeetingRoom.contains(meetingRoom));
    }

    @Test
    public void getMeetingsFilterRoom() {
        ArrayList<String> rooms = new ArrayList<>();
        rooms.add("Hades Room");
        rooms.add("Zeus Room");
        //Zeus room to test
        List<Meeting> meetings = Dummy_Meetings;
        List<Meeting> expectedMeetings = service.getMeetingsFromRoomFilter(rooms,meetings);
        assertEquals(6, expectedMeetings.size());
    }

    @Test
    public void getMeetingsFromDateFilter() {
        List<Meeting> meetings = Dummy_Meetings;
        List<Meeting> expectedMeetings = service.getMeetingsFromDateFilter(Calendar.getInstance().getTime(),meetings);
        assertEquals(5, expectedMeetings.size());
    }

    public List<Meeting> Dummy_Meetings = Arrays.asList(
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, zeusRoom, addDays(Calendar.getInstance().getTime(), 2), participants1),
            new Meeting(getRandomID(), "subject", R.drawable.ic_baseline_circle_24, hadesRoom, addDays(Calendar.getInstance().getTime(),3), participants1),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, hermesRoom, addDays(Calendar.getInstance().getTime(), 4), participants2),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, zeusRoom, addDays(Calendar.getInstance().getTime(), 5), participants3),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, hadesRoom, addDays(Calendar.getInstance().getTime(),0), participants2),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, poseidonRoom, addDays(Calendar.getInstance().getTime(),0), participants3),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, zeusRoom, addDays(Calendar.getInstance().getTime(),0), participants1),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, hermesRoom, addDays(Calendar.getInstance().getTime(),0), participants2),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, apolloRoom, addDays(Calendar.getInstance().getTime(),1), participants1),
            new Meeting(getRandomID(), "Subject", R.drawable.ic_baseline_circle_24, hadesRoom, addDays(Calendar.getInstance().getTime(),0), participants1)
    );

    static MeetingRoom zeusRoom = new MeetingRoom(1, "Zeus Room", "Zeus Room");
    static MeetingRoom hadesRoom = new MeetingRoom(2, "Hades Room", "Hades Room");
    static MeetingRoom hermesRoom = new MeetingRoom(3, "Hermes Room", "Hermes Room");
    static MeetingRoom apolloRoom = new MeetingRoom(4, "Apollo Room", "Apollo Room");
    static MeetingRoom poseidonRoom = new MeetingRoom(5, "Poseidon Room", "Poseidon Room");

    public static Date addDays(Date d, int dayIncr) {
        int days = dayIncr;
        d.setTime(d.getTime() + days * 1000L * 60L * 60L * 24L);
        return d;
    }
}