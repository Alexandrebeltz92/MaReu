package com.example.mareu.ui;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Employee;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.DummyMeetingGenerator;
import com.example.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.mareu.service.DummyMeetingGenerator.addDays;
import static com.example.mareu.service.DummyMeetingGenerator.participants1;
import static com.example.mareu.service.DummyMeetingGenerator.participantsAll;

public class MeetingAddActivity extends AppCompatActivity {

    //Initialize variable
    private ImageButton mCloseButton, mAddButton;
    private EditText mSubjectMeeting;
    private Spinner mSpinnerRoom;
    private Spinner mSpinnerParticipants;
    MeetingApiService meetingApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_add);
        DummyMeetingGenerator.generateMeetingRoom();

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.toolbar_dark));

        meetingApiService = DI.getMeetingApiService();

        //Assign variable
        mCloseButton = findViewById(R.id.imageButtonClose);
        mAddButton = findViewById(R.id.add_meeting_button_create);
        mSubjectMeeting = findViewById(R.id.subject_meeting_add);
        mSpinnerRoom = (Spinner) findViewById(R.id.spinner_room);
        mSpinnerParticipants = (Spinner) findViewById(R.id.spinner_participants);

        //Spinner Meeting Room
        List<MeetingRoom> RoomList = new ArrayList<>();
        RoomList.addAll(DummyMeetingGenerator.generateMeetingRoom());

        ArrayAdapter<MeetingRoom> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, RoomList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerRoom.setAdapter(adapter);

        //Spinner Participants
        List<Employee> EmployeeList = new ArrayList<>();
        EmployeeList.addAll(DummyMeetingGenerator.generateMeetingParticipants());

        ArrayAdapter<Employee> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,EmployeeList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerParticipants.setAdapter(adapter1);

        //Close Meeting Add Activity
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Add Meeting
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Meeting m = new Meeting(1,mSubjectMeeting.getText().toString(),R.drawable.ic_baseline_circle_24, (MeetingRoom) mSpinnerRoom.getSelectedItem(), addDays(Calendar.getInstance().getTime()), (ArrayList<Employee>) mSpinnerParticipants.getSelectedItem());
                meetingApiService.createMeeting(m);
                finish();
            }
        });
    }
}
