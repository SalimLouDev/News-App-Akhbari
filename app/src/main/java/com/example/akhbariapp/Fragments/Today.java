package com.example.akhbariapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.akhbariapp.Activities.AdminHomeActivity;
import com.example.akhbariapp.Activities.HomeActivity;
import com.example.akhbariapp.Entity.PostsEntity;
import com.example.akhbariapp.PostRecyclerViewAdapter;
import com.example.akhbariapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class Today extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.posts_fragment,container,false);

        String user_state = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("user");

        if(Objects.equals(user_state, "normal_user")){
            HomeActivity homeActivity = (HomeActivity) getActivity();
            Objects.requireNonNull(Objects.requireNonNull(homeActivity).getSupportActionBar()).setTitle(getString(R.string.today));
        }
        else if(Objects.equals(user_state, "admin")){
            AdminHomeActivity adminHomeActivity = (AdminHomeActivity) getActivity();
            Objects.requireNonNull(Objects.requireNonNull(adminHomeActivity).getSupportActionBar()).setTitle(getString(R.string.today));
        }

        ArrayList<PostsEntity>posts = new ArrayList<>();
        for(int i=0;i<10;i++)
        posts.add(new PostsEntity("Manhattan Bridge","The Manhattan Bridge is a suspension bridge that ..."));

        RecyclerView post_list = root.findViewById(R.id.post_recycler_view);
        FloatingActionButton floatingActionButton = Objects.requireNonNull(requireActivity()).findViewById(R.id.floating_action_button);

        if (Objects.requireNonNull(user_state).equals("admin")){

            post_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if(dy<0){
                        floatingActionButton.show();
                    }
                    else if (dy>0){
                        floatingActionButton.hide();
                    }
                }
            });
        }

        post_list.setLayoutManager(new LinearLayoutManager(getContext()));
        PostRecyclerViewAdapter adapter = new PostRecyclerViewAdapter(getContext(),posts);
        post_list.setAdapter(adapter);
        return root;
    }
}
