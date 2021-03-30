package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

        private List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();
        private List<Meeting> mRandomMeeting = DummyMeetingGenerator.generateMeetingsRandomList();

        @Override
        public List<Meeting> getMeeting() {
            return mMeetings;
        }
        public List<Meeting> getRandomMeetingList(){return mRandomMeeting;}

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
