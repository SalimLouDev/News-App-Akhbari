package com.example.akhbariapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.akhbariapp.Activities.AdminHomeActivity;
import com.example.akhbariapp.Activities.HomeActivity;
import com.example.akhbariapp.RecyclerViewAdapters.MessageAdapter;
import com.example.akhbariapp.Entity.MessageItem;
import com.example.akhbariapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class Inbox extends Fragment {

    private Animation animation;
    private FloatingActionButton fab;
    public Inbox(){}
    public Inbox(Animation animation){

        this.animation=animation;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.inbox_interface,container,false);
        String user_state = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("user");
        fab = root.findViewById(R.id.floating_action_button_for_mail_interface);
        ArrayList<MessageItem> messageItemArrayList = new ArrayList<>();
        for (int i=0; i<10;i++) messageItemArrayList.add(new MessageItem("Corona Diagnosis","We kindly inform you that the ...","25-04-2020","11:34"));

        RecyclerView recyclerView = root.findViewById(R.id.message_recycle_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.Adapter adapter = new MessageAdapter(messageItemArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



        if(Objects.equals(user_state, "normal_user")){
            HomeActivity homeActivity = (HomeActivity) getActivity();
            Objects.requireNonNull(Objects.requireNonNull(homeActivity).getSupportActionBar()).setTitle("Inbox");
            fab.setVisibility(View.INVISIBLE);
        }
        else if(Objects.equals(user_state, "admin")){
            AdminHomeActivity adminHomeActivity = (AdminHomeActivity) getActivity();
            Objects.requireNonNull(Objects.requireNonNull(adminHomeActivity).getSupportActionBar()).setTitle("Inbox");
            fab.startAnimation(animation);
            fab.setOnClickListener(view -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container, new AdminPostMessage(animation)).commit());
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if(dy<0){
                        fab.show();
                    }
                    else if (dy>0){
                        fab.hide();
                    }
                }
            });
        }




        return root;
    }


}
