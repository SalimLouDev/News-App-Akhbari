package com.example.akhbariapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.akhbariapp.Activities.HomeActivity;
import com.example.akhbariapp.R;

import java.util.Objects;

public class Past extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.posts_fragment,container,false);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        Objects.requireNonNull(Objects.requireNonNull(homeActivity).getSupportActionBar()).setTitle(getString(R.string.past));

        return root;
    }
}
