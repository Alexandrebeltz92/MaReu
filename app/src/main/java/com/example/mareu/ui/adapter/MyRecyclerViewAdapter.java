package com.example.mareu.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Employee;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private List<Meeting> mMeeting;
    private List<Meeting> filterList;
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm", Locale.FRANCE);
    MeetingApiService meetingApiService;

    public MyRecyclerViewAdapter(List<Meeting> items) {
        mMeeting = items;
        filterList = items;
        meetingApiService = DI.getMeetingApiService();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recyclerview_item_meeting, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meeting meeting = filterList.get(position);
        holder.mSubject.setText(meeting.getSubject());
        holder.mParticipant.setText(addText(meeting.getParticipants()));
        holder.mLocalisation.setText(meeting.getMeetingRoom().getName());
        String mDate = sdf.format(meeting.getDate());
        holder.mDate.setText(mDate);
        holder.mDeleteMeeting.setOnClickListener(v -> {
            listener.onItemClicked(filterList.get(position));
        });
        holder.mDate.setOnLongClickListener(v -> {
            listener.onMeetingLongClicked(filterList.get(position));
            return true;
        });
        setRoomColor(holder, meeting.getMeetingRoom().getId());
    }

    private String addText(ArrayList<Employee> participants) {
        StringBuilder text = new StringBuilder();
        text.append(participants.get(0).getEmail());
        for (int i = 1; i < participants.size(); i++) {
            text.append(", " + participants.get(i).getEmail());
        }
        return text.toString();
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    private void setRoomColor(@NonNull MyViewHolder holder, int position) {
        switch (position) {
            case 1:
                holder.mAvatar.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.ZeusRoom), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 2:
                holder.mAvatar.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.HadesRoom), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 3:
                holder.mAvatar.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.HermesRoom), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 4:
                holder.mAvatar.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.ApolloRoom), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 5:
                holder.mAvatar.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.PoseidonRoom), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mSubject, mDate, mLocalisation, mParticipant;
        ImageView mAvatar;
        ImageButton mDeleteMeeting;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mSubject = itemView.findViewById(R.id.subject_meeting);
            mDate = itemView.findViewById(R.id.date_meeting);
            mLocalisation = itemView.findViewById(R.id.localisation_meeting);
            mParticipant = itemView.findViewById(R.id.participant_meeting);
            mAvatar = itemView.findViewById(R.id.avatar_meeting);
            mDeleteMeeting = itemView.findViewById(R.id.item_list_delete_button);
        }
    }

    private ItemClickListener listener;

    public void setListener(ItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClicked(Meeting meeting);
        void onMeetingLongClicked(Meeting meeting);
    }

    public void filterRoom(ArrayList<String> rooms) {
        if (rooms == null || rooms.isEmpty()) {
            filterList = mMeeting;
        } else {
            filterList = meetingApiService.getMeetingsFromRoomFilter(rooms, mMeeting);
        }
        notifyDataSetChanged();
    }

    public void filterDate(Date date) {

        if (date == null) {
            filterList = mMeeting;
        } else {
            filterList = meetingApiService.getMeetingsFromDateFilter(date, mMeeting);
        }
        notifyDataSetChanged();
    }
}
