package com.example.akhbariapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.akhbariapp.R;

public class ShowMessage extends Fragment {

    private String title;
    private String read_more;
    private String date;
    private String time;
    private TextView titleTV;
    private TextView read_moreTV;
    private TextView dateTV;
    private TextView timeTV;

    public ShowMessage(String title, String read_more, String date, String time) {
        this.title = title;
        this.read_more = read_more;
        this.date = date;
        this.time = time;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.show_message_interface,container,false);
        titleTV = root.findViewById(R.id.show_message_title);
        titleTV.setText(title);
        read_moreTV = root.findViewById(R.id.show_message_read_more);
        read_moreTV.setText(read_more);
        dateTV = root.findViewById(R.id.show_message_date);
        dateTV.setText(date);
        timeTV = root.findViewById(R.id.show_message_time);
        timeTV.setText(time);

        return root;
    }
}
