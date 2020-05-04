package com.example.akhbariapp.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.akhbariapp.Entity.MessageItem;
import com.example.akhbariapp.R;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.messageViewHolder> {

    private ArrayList<MessageItem> arrayListItems;
    public static class messageViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView read_more;
        public TextView date;
        public TextView time;


        public messageViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.message_title);
            read_more = itemView.findViewById(R.id.message_read_more);
            date = itemView.findViewById(R.id.message_date);
            time = itemView.findViewById(R.id.message_time);
        }
    }

    public MessageAdapter(ArrayList<MessageItem> messageItemArrayList){
        this.arrayListItems=messageItemArrayList;
    }

    @NonNull
    @Override
    public messageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item,parent,false);
        return new messageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull messageViewHolder holder, int position) {

        MessageItem currentItem = arrayListItems.get(position);
        holder.title.setText(currentItem.getTitle());
        holder.read_more.setText(currentItem.getRead_more());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());
    }

    @Override
    public int getItemCount() {
        return arrayListItems.size();
    }
}
