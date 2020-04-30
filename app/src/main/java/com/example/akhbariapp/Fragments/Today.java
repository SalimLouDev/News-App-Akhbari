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

import com.example.akhbariapp.Activities.HomeActivity;
import com.example.akhbariapp.Entity.PostsEntity;
import com.example.akhbariapp.PostRecyclerViewAdapter;
import com.example.akhbariapp.R;

import java.util.ArrayList;
import java.util.Objects;

public class Today extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.posts_fragment,container,false);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        Objects.requireNonNull(Objects.requireNonNull(homeActivity).getSupportActionBar()).setTitle(getString(R.string.today));

        ArrayList<PostsEntity>posts = new ArrayList<>();
        for(int i=0;i<10;i++)
        posts.add(new PostsEntity("title","read more"));

        RecyclerView post_list = root.findViewById(R.id.post_recycler_view);
        post_list.setLayoutManager(new LinearLayoutManager(getContext()));
        PostRecyclerViewAdapter adapter = new PostRecyclerViewAdapter(getContext(),posts);
        post_list.setAdapter(adapter);
        return root;
    }
}
