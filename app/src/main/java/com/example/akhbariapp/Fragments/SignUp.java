package com.example.akhbariapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.akhbariapp.Activities.HomeActivity;
import com.example.akhbariapp.Entity.EntityUser;
import com.example.akhbariapp.R;
import com.example.akhbariapp.ViewModel.UserViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUp extends Fragment {

    private TextInputLayout first_name,last_name,password,national_id,admin_code;
    private AutoCompleteTextView city;
    private CheckBox admin_code_checkbox;
    private UserViewModel userViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.sign_up_fragment,container,false);


        first_name = root.findViewById(R.id.first_name_edit);
        last_name = root.findViewById(R.id.last_name_edit);
        password = root.findViewById(R.id.password_edit_sign_up);
        national_id = root.findViewById(R.id.nationalid_edit_sign_up);
        city = root.findViewById(R.id.user_city);
        admin_code = root.findViewById(R.id.admin_code_sign_up);
        admin_code_checkbox = root.findViewById(R.id.is_admin_sign_up);

        admin_code_checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)
                admin_code.setVisibility(View.VISIBLE);
            else
                admin_code.setVisibility(View.INVISIBLE);
        });

        userViewModel = new UserViewModel(Objects.requireNonNull(getActivity()).getApplication());
        Button sign_up = root.findViewById(R.id.sign_up_button);
        sign_up.setOnClickListener(v-> add_user());

        return root;
    }

    private void add_user(){
        if((Objects.requireNonNull(first_name.getEditText()).getText().toString().trim().isEmpty() ||
                Objects.requireNonNull(last_name.getEditText()).getText().toString().trim().isEmpty() ||
                Objects.requireNonNull(password.getEditText()).getText().toString().trim().isEmpty() ||
                Objects.requireNonNull(national_id.getEditText()).getText().toString().trim().isEmpty() ||
                city.getEditableText().toString().trim().isEmpty()) && !admin_code_checkbox.isChecked()){
            Toast.makeText(getContext(),"You have to fill all the fields",Toast.LENGTH_SHORT).show();
        }
        else if(admin_code_checkbox.isChecked() &&
                Objects.requireNonNull(admin_code.getEditText()).getText().toString().trim().isEmpty()){
            Toast.makeText(getContext(),"You have to fill the code filed",Toast.LENGTH_SHORT).show();
        }
        else {
            String firstname = first_name.getEditText().getText().toString().trim();
            String lastname = Objects.requireNonNull(last_name.getEditText()).getText().toString().trim();
            String _password = Objects.requireNonNull(password.getEditText()).getText().toString().trim();
            int nat_id = Integer.parseInt(Objects.requireNonNull(national_id.getEditText()).getText().toString().trim());
            String _city = city.getEditableText().toString().trim();

            EntityUser user = new EntityUser(firstname,lastname,_password,_city,nat_id);
            userViewModel.add_user(user);
            startActivity(new Intent(getActivity(),HomeActivity.class));
            Objects.requireNonNull(getActivity()).finish();
        }
    }
}
