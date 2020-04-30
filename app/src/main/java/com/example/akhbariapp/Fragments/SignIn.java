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

public class SignIn extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login,container,false);

        Button sign_in = root.findViewById(R.id.login_button);
        TextView sign_up_text = root.findViewById(R.id.signUp);
        sign_in.setOnClickListener(v->{
            startActivity(new Intent(getActivity(), HomeActivity.class));
        });

        sign_up_text.setOnClickListener(v->{
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(fragmentManager).beginTransaction();

            SignUp signUpFragment = new SignUp();
            fragmentTransaction.replace(R.id.fragment_container_sign,signUpFragment,"tag");
            fragmentTransaction.commit();
        });
        return root;
    }
}
