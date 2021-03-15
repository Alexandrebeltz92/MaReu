package com.example.mareu.ui;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mareu.R;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.DummyMeetingGenerator;

import java.util.ArrayList;
import java.util.List;

public class MeetingAddActivity extends AppCompatActivity {

    //Initialize variable
    private ImageButton mCloseButton, mAddButton;
    private EditText mSubjectMeeting;
    private Spinner mSpinnerRoom;
    private TextView mTextViewParticipants;

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

        //variable
        mCloseButton = findViewById(R.id.imageButtonClose);
        mAddButton = findViewById(R.id.add_meeting_button_create);
        mSubjectMeeting = findViewById(R.id.subject_meeting_add);
        mSpinnerRoom = (Spinner) findViewById(R.id.spinner_room);
        mTextViewParticipants = findViewById(R.id.tv_participants);

        List<MeetingRoom> RoomList = new ArrayList<>();
        RoomList.addAll(DummyMeetingGenerator.generateMeetingRoom());

        ArrayAdapter<MeetingRoom> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, RoomList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerRoom.setAdapter(adapter);

        mSpinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MeetingRoom meetingRoom = (MeetingRoom) parent.getSelectedItem();
                displayRoomData(meetingRoom);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void getSelectedRoom(View v) {
        MeetingRoom meetingRoom = (MeetingRoom) mSpinnerRoom.getSelectedItem();
        displayRoomData(meetingRoom);
    }

    private void displayRoomData(MeetingRoom meetingRoom) {
        String name = meetingRoom.getName();
        String place = meetingRoom.getPlace();
        int id = meetingRoom.getId();

        String meetingRoomData = name;
        Toast.makeText(this,meetingRoomData,Toast.LENGTH_LONG).show();
    }

}
