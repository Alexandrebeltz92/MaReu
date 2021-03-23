package com.example.mareu;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.DummyMeetingGenerator;
import com.example.mareu.service.MeetingApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

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
public void getMeetingWithSucess(){
        List<Meeting> meetings = service.getMeeting();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.Dummy_Meetings;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
        }

@Test
public void deleteMeetingWithSuccess() {
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

}