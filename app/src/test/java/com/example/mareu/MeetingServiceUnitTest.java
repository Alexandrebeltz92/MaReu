package com.example.mareu;

import com.example.mareu.di.DI;
import com.example.mareu.model.Employee;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.DummyMeetingGenerator;
import com.example.mareu.service.MeetingApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meeting = new Meeting(1, "Mon meeting Test", R.drawable.ic_baseline_circle_24, DummyMeetingGenerator.generateMeetingRoom().get(1), Calendar.getInstance().getTime(), DummyMeetingGenerator.participants1);
        service.createMeeting(meeting);
        Meeting meetingToDelete = service.getMeeting().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeeting().contains(meetingToDelete));
    }

    @Test
    public void createMeetingWithSuccess() {
        Meeting meetingToCreate = new Meeting(1, "Mon meeting Test", R.drawable.ic_baseline_circle_24, DummyMeetingGenerator.generateMeetingRoom().get(1), Calendar.getInstance().getTime(), DummyMeetingGenerator.participants1);
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
            Employee employee = new Employee(10,"Diego","Augusto","email@gmail.com");
            mListEmployee.add(employee);
            assertTrue(mListEmployee.contains(employee));
    }

    @Test
    public void createMeetingRoomWithSuccess() {
            ArrayList<MeetingRoom> mMeetingRoom = (ArrayList<MeetingRoom>) DummyMeetingGenerator.generateMeetingRoom();
            MeetingRoom meetingRoom = new MeetingRoom(10,"Emplacement","Nom");
            mMeetingRoom.add(meetingRoom);
            assertTrue(mMeetingRoom.contains(meetingRoom));
    }

}