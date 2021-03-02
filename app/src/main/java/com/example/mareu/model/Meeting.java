package com.example.mareu.model;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

import static com.example.mareu.service.DummyMeetingGenerator.Dummy_Random_Meetings;

public class Meeting {

    /**
     * Identifier
     */
    private long id;

    /**
     * Meeting subject
     */
    private String subject;

    /**
     * Avatar Meeting
     */
    private int avatarMeeting;

    /**
     * Localisation
     */
    private String localisation;

    /**
     * Date
     */
    private Date date;

    /**
     * Participants
     */
    private String participants;

    /**
     * Constructor
     */

    public Meeting(long id, String subject, int avatarMeeting, String localisation,
                   Date date, String participants) {
        this.id = id;
        this.subject = subject;
        this.avatarMeeting = avatarMeeting;
        this.localisation = localisation;
        this.date = date;
        this.participants = participants;
    }

    /** Setter and getter */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getAvatarMeeting() {
        return avatarMeeting;
    }

    public void setAvatarMeeting(int avatarMeeting) {
        this.avatarMeeting = avatarMeeting;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static Meeting random() {
        return Dummy_Random_Meetings.get(new Random().nextInt(Dummy_Random_Meetings.size()));
    }
}
