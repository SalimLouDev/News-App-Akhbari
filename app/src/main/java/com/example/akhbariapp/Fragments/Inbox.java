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
import com.example.akhbariapp.MessageAdapter;
import com.example.akhbariapp.MessageItem;
import com.example.akhbariapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class Inbox extends Fragment {

    private Animation animation;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public Inbox(Animation animation){

        this.animation=animation;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.inbox_interface,container,false);
        AdminHomeActivity homeActivity = (AdminHomeActivity) getActivity();
        Objects.requireNonNull(Objects.requireNonNull(homeActivity).getSupportActionBar()).setTitle("Inbox");
        FloatingActionButton fab = root.findViewById(R.id.floating_action_button_for_mail_interface);
        fab.startAnimation(animation);
        fab.setOnClickListener(view -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container, new AdminPostMessage(animation)).commit());
        ArrayList<MessageItem> messageItemArrayList = new ArrayList<>();
        for (int i=0; i<10;i++) messageItemArrayList.add(new MessageItem("Corona Diagnosis","We kindly inform you that the ...","25-04-2020","11:34"));

        recyclerView = root.findViewById(R.id.message_recycle_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new MessageAdapter(messageItemArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


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



        return root;
    }


}
