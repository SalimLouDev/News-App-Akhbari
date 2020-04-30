package com.example.akhbariapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.akhbariapp.Activities.HomeActivity;
import com.example.akhbariapp.R;

import java.util.Objects;

public class SignUp extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.sign_up_fragment,container,false);

        Button sign_up = root.findViewById(R.id.sign_up_button);
        sign_up.setOnClickListener(v->{
            startActivity(new Intent(getActivity(), HomeActivity.class));
        });

        TextView sign_in_text = root.findViewById(R.id.logIn);
        sign_in_text.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(fragmentManager).beginTransaction();

            SignIn signInFragment = new SignIn();
            fragmentTransaction.replace(R.id.fragment_container_sign,signInFragment,"tag");
            fragmentTransaction.commit();
        });

        return root;
    }

}
