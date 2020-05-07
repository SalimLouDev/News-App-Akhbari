package com.example.akhbariapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.akhbariapp.Activities.AdminHomeActivity;
import com.example.akhbariapp.Activities.HomeActivity;
import com.example.akhbariapp.Entity.PostsEntity;
import com.example.akhbariapp.R;
import com.example.akhbariapp.RecyclerViewAdapters.PostRecyclerViewAdapter;
import com.example.akhbariapp.ViewModel.PostsViewModel;

import java.util.List;
import java.util.Objects;

public class Transport extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.posts_fragment,container,false);

        String user_state = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("user");
        if(Objects.equals(user_state, "normal_user")){
            HomeActivity homeActivity = (HomeActivity) getActivity();
            Objects.requireNonNull(Objects.requireNonNull(homeActivity).getSupportActionBar()).setTitle(getString(R.string.transport));
        }
        else if(Objects.equals(user_state, "admin")){
            AdminHomeActivity adminHomeActivity = (AdminHomeActivity) getActivity();
            Objects.requireNonNull(Objects.requireNonNull(adminHomeActivity).getSupportActionBar()).setTitle(getString(R.string.transport));
        }

        PostsViewModel postsViewModel = new ViewModelProvider(this).get(PostsViewModel.class);
        RecyclerView post_list = root.findViewById(R.id.post_recycler_view);
        post_list.setLayoutManager(new LinearLayoutManager(getContext()));
        PostRecyclerViewAdapter adapter = new PostRecyclerViewAdapter(getContext());
        post_list.setAdapter(adapter);

        postsViewModel.get_posts_by_type(getString(R.string.transport)).observe(getViewLifecycleOwner(), postsEntities -> adapter.setList(postsEntities));
        return root;
    }
}
