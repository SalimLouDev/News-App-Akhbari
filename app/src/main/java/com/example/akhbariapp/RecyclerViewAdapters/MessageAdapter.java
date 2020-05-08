package com.example.akhbariapp.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.akhbariapp.Entity.MessageEntity;
import com.example.akhbariapp.R;

public class MessageAdapter extends ListAdapter<MessageEntity, MessageAdapter.messageViewHolder> {

    public static OnItemClickListener listener;

    public MessageAdapter() {
        super(DIFF_CALLBACK);

    }

    private static final DiffUtil.ItemCallback<MessageEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<MessageEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull MessageEntity oldItem, @NonNull MessageEntity newItem) {
            return oldItem.getMessageID() == newItem.getMessageID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MessageEntity oldItem, @NonNull MessageEntity newItem) {
            return false;
        }
    };

    class messageViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView read_more;
        private TextView date;
        private TextView time;


        messageViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.message_title);
            read_more = itemView.findViewById(R.id.message_read_more);
            date = itemView.findViewById(R.id.message_date);
            time = itemView.findViewById(R.id.message_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(getAdapterPosition()));
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public messageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new messageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull messageViewHolder holder, int position) {

        MessageEntity currentItem = getItem(position);
        holder.title.setText(currentItem.getTitle());
        holder.read_more.setText(currentItem.getDescription());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());
    }

    public MessageEntity getMessageAt(int position) {
        return getItem(position);
    }

    public interface OnItemClickListener {
        void onItemClick(MessageEntity messageEntity);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        MessageAdapter.listener = listener;
    }
}
