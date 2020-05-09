package com.example.akhbariapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.akhbariapp.R;

import java.util.Objects;

public class ShowMessage extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String title = (String) intent.getSerializableExtra("title");
        String description = (String) intent.getSerializableExtra("description");
        String date = (String) intent.getSerializableExtra("date");
        String time = (String) intent.getSerializableExtra("time");
        setContentView(R.layout.show_message_interface);
        TextView titleTV = findViewById(R.id.show_message_title);
        titleTV.setText(Objects.requireNonNull(title));
        TextView read_moreTV = findViewById(R.id.show_message_read_more);
        read_moreTV.setText(description);
        TextView dateTV = findViewById(R.id.show_message_date);
        dateTV.setText(date);
        TextView timeTV = findViewById(R.id.show_message_time);
        timeTV.setText(time);
    }

}
