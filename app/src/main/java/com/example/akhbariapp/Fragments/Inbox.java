package com.example.akhbariapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.akhbariapp.Activities.AdminHomeActivity;
import com.example.akhbariapp.Activities.HomeActivity;
import com.example.akhbariapp.Entity.MessageEntity;
import com.example.akhbariapp.RecyclerViewAdapters.MessageAdapter;
import com.example.akhbariapp.R;
import com.example.akhbariapp.ViewModel.MessagesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        RecyclerView recyclerView = root.findViewById(R.id.message_recycle_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        MessageAdapter messageAdapter = new MessageAdapter();
        recyclerView.setAdapter(messageAdapter);


        MessagesViewModel messagesViewModel = new ViewModelProvider(this).get(MessagesViewModel.class);
        messagesViewModel.getAllMessages().observe(getViewLifecycleOwner(), messageAdapter::submitList);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                messagesViewModel.delete(messageAdapter.getMessageAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Message deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        messageAdapter.setOnItemClickListener(messageEntity -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container, new ShowMessage(messageEntity.getTitle(),messageEntity.getDescription(),messageEntity.getDate(),messageEntity.getTime())).commit());

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
