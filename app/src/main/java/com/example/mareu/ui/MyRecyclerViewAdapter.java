package com.example.mareu.ui;

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
import com.example.mareu.model.Meeting;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private List<Meeting> mMeeting;

    public MyRecyclerViewAdapter(List<Meeting> items) {
        mMeeting = items;
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
        Meeting meeting = mMeeting.get(position);
        holder.mSubject.setText(meeting.getSubject());
        holder.mParticipant.setText(meeting.getParticipants());
        holder.mLocalisation.setText(meeting.getLocalisation());
        String[] mDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.FRANCE).format(meeting.getDate()).split(" ");
        holder.mDate.setText(mDate[mDate.length - 1]);
        holder.mDeleteMeeting.setOnClickListener(v -> {
            listener.OnItemClicked(mMeeting.get(position));
        });
        setRoomColor(holder,position);
    }

    @Override
    public int getItemCount() {
        return mMeeting.size();
    }

    private void setRoomColor(@NonNull MyViewHolder holder, int position) {
        switch (position) {
            case 1:
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.ZeusRoom));
                break;
            case 2 :
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.HadesRoom));
                break;
            case 3:
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.AresRoom));
                break;
            case 4 :
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.ApolloRoom));
                break;
            case 5:
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.AthenaRoom));
                break;
            case 6:
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.PoseidonRoom));
                break;
            case 7:
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.HeraRoom));
                break;
            case 8:
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.HermesRoom));
                break;
            case 9:
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.DionysusRoom));
                break;
            case 10:
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.HeraclesRoom));
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
        void OnItemClicked(Meeting meeting);
    }
}
