package com.example.mareu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MeetingListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FloatingActionButton mFab;
    FloatingActionButton mFabRandomMeeting;
    FloatingActionButton mFabCreateMeeting;
    MeetingApiService meetingApiService;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        myDialog = new Dialog(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.toolbar_dark));

        mFab = findViewById(R.id.add_meeting);
        mFabRandomMeeting = findViewById(R.id.add_random_meeting);
        mFabCreateMeeting = findViewById(R.id.create_meeting);
        mRecyclerView = findViewById(R.id.recycler_view);
        meetingApiService = DI.getMeetingApiService();

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(meetingApiService.getMeeting());
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myRecyclerViewAdapter.setListener(new MyRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void OnItemClicked(Meeting meeting) {
                meetingApiService.deleteMeeting(meeting);
                mRecyclerView.getAdapter().notifyDataSetChanged();
                Snackbar.make(mRecyclerView, "Reunion supprimée : " + meeting.getSubject(), Snackbar.LENGTH_LONG).show();
            }
        });

        // FLOATING ACTION BUTTON
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFabRandomMeeting.getVisibility() == View.VISIBLE && mFabCreateMeeting.getVisibility() == View.VISIBLE) {
                    mFabCreateMeeting.setVisibility(View.GONE);
                    mFabRandomMeeting.setVisibility(View.GONE);
                } else {
                    mFabCreateMeeting.setVisibility(View.VISIBLE);
                    mFabRandomMeeting.setVisibility(View.VISIBLE);
                }
            }
        });

        mFabRandomMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 meetingApiService.createMeeting(Meeting.random());
                 mRecyclerView.getAdapter().notifyDataSetChanged();
                 Snackbar.make(v, "Réunion ajoutée :" + meetingApiService.getMeeting().size(), Snackbar.LENGTH_LONG).show();
            }
        });

        mFabCreateMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    //MENU & MENU OPTIONS
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.localisation_filter) {
            filterRoomDialog();
            Toast.makeText(getApplicationContext(), "Choose your rooms.", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.date_filter) {
            filterDateDialog();
            Toast.makeText(getApplicationContext(), "Date Filter", Toast.LENGTH_LONG).show();
        }

        if (id == R.id.all_filter) {
            ((MyRecyclerViewAdapter) mRecyclerView.getAdapter()).filterDate(null);
            Toast.makeText(getApplicationContext(), "afficher tout", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    //FILTER ROOM
    public void filterRoomDialog() {
        String[] roomsList = {"Zeus Room", "Hades Room", "Hermes Room", "Apollo Room", "Poseidon Room"};
        boolean[] isCheckedList = {false, false, false, false, false};


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select room");
        builder.setMultiChoiceItems(roomsList, isCheckedList, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                isCheckedList[which] = isChecked;
            }
        });

        builder.setPositiveButton("OK" ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ArrayList<String> rooms = new ArrayList<>();
                for (int i = 0; i < roomsList.length; i++) {
                    if  (isCheckedList[i]) {
                        rooms.add(roomsList[i]);
                    }
                }
                ((MyRecyclerViewAdapter) mRecyclerView.getAdapter()).filterRoom(rooms);
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    //FILTER DATE
    public void filterDateDialog() {

        // calender class's instance and get current date , month and year from calender
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker date, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        ((MyRecyclerViewAdapter) mRecyclerView.getAdapter()).filterDate(calendar.getTime());
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    //CREATE MEETING POPUP
    public void ShowPopUpCreateMeeeting(View view){
        myDialog.setContentView(R.layout.popup_create_meeting);
        myDialog.show();
    }


}