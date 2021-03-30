package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

/**
 * Meeting API client
 */
public interface MeetingApiService {
    /**
     * Get all my Meeting
     *
     * @return {@link List}
     */
    List<Meeting> getMeeting();

    /**
     * Get all my Random Meeting
     *
     * @return {@link List}
     */

    List<Meeting> getRandomMeetingList();

    /**
     * Deletes a meeting
     *
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Generate random meeting
     */
    void generateRandomMeeting();

    /**
     * Create a meeting
     *
     * @param meeting
     */
    void createMeeting(Meeting meeting);

}
