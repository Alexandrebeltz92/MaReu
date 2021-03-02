package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

        private List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();

        @Override
        public List<Meeting> getMeeting() {
            return mMeetings;
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
}
