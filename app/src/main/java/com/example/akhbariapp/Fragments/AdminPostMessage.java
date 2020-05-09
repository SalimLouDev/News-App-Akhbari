package com.example.akhbariapp.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.akhbariapp.Activities.AdminHomeActivity;
import com.example.akhbariapp.Activities.HomeActivity;
import com.example.akhbariapp.Entity.MessageEntity;
import com.example.akhbariapp.R;
import com.example.akhbariapp.ViewModel.MessagesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import static java.time.LocalTime.now;

public class AdminPostMessage extends Fragment
{

    private Animation animation;
    private TextInputEditText to,subject,content;


    AdminPostMessage(Animation animation){

        this.animation=animation;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.admin_massage,container,false);

        to = root.findViewById(R.id.to);
        subject = root.findViewById(R.id.subject);
        content = root.findViewById(R.id.content);

        String user_state = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("user");
        if(Objects.equals(user_state, "normal_user")){
            HomeActivity homeActivity = (HomeActivity) getActivity();
            Objects.requireNonNull(Objects.requireNonNull(homeActivity).getSupportActionBar()).setTitle(getString(R.string.message));
        }
        else if(Objects.equals(user_state, "admin")){
            AdminHomeActivity adminHomeActivity = (AdminHomeActivity) getActivity();
            Objects.requireNonNull(Objects.requireNonNull(adminHomeActivity).getSupportActionBar()).setTitle(getString(R.string.message));

            FloatingActionButton fab = root.findViewById(R.id.floating_action_button_for_send_mail);
            fab.startAnimation(animation);

            fab.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    saveMessage();
                }
            });
        }

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveMessage(){
        String to = Objects.requireNonNull(this.to.getText()).toString();
        String subject = Objects.requireNonNull(this.subject.getText()).toString();
        String content = Objects.requireNonNull(this.content.getText()).toString();

        if (to.trim().isEmpty()){
            Toast.makeText(getActivity(), "Add at least one recipient.", Toast.LENGTH_SHORT).show();
        }else if (content.trim().trim().isEmpty()){
            Toast.makeText(getActivity(), "Content is empty.", Toast.LENGTH_SHORT).show();
        }else if (subject.trim().isEmpty()){
            Toast.makeText(getActivity(), "Title is empty.", Toast.LENGTH_SHORT).show();
        }else {
            MessagesViewModel messagesViewModel = new ViewModelProvider(this).get(MessagesViewModel.class);
            messagesViewModel.insert(new MessageEntity(subject,content,LocalDate.now().toString(),LocalTime.now().toString(),to));
            Toast.makeText(getActivity(), "Mail sent", Toast.LENGTH_SHORT).show();
            this.to.setText("");
            this.content.setText("");
            this.subject.setText("");
        }
    }
}
