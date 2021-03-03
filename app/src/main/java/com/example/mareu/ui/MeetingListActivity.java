package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MeetingListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FloatingActionButton mFab;
    MeetingApiService meetingApiService;
    Button mLocalisationButton;
    Dialog mDialogLocalisation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);

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

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetingApiService.createMeeting(Meeting.random());
                mRecyclerView.getAdapter().notifyDataSetChanged();
                Snackbar.make(v, "Réunion ajoutée :" + meetingApiService.getMeeting().size(), Snackbar.LENGTH_LONG).show();
            }
        });

        mLocalisationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogLocalisation.setContentView(R.layout.popup_localisation);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        MenuItem item = menu.findItem(R.id.filter);
        return true;
    }
}