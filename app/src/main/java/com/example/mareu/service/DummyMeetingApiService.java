package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();
    private List<Meeting> mRandomMeeting = DummyMeetingGenerator.generateMeetingsRandomList();

    @Override
    public List<Meeting> getMeeting() {
        return mMeetings;
    }

    public List<Meeting> getRandomMeetingList() {
        return mRandomMeeting;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    @Override
    public void generateRandomMeeting() {
        mMeetings.add(Meeting.random());
    }

    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    @Override
    public List<Meeting> getMeetingsFromRoomFilter(ArrayList<String> rooms, List<Meeting> meetings) {
        ArrayList<Meeting> resultList = new ArrayList<>();
        for (String room : rooms) {
            for (Meeting meeting : meetings) {
                if (room.equalsIgnoreCase(meeting.getMeetingRoom().getName())) {
                    resultList.add(meeting);
                }
            }
        }
        return resultList;
    }

    @Override
    public List<Meeting> getMeetingsFromDateFilter(Date date, List<Meeting> meetings) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
        ArrayList<Meeting> resultListDate = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (sdf.format(date).equalsIgnoreCase(sdf.format(meeting.getDate()))) {
                resultListDate.add(meeting);
            }
        }
        return resultListDate;
    }
}
