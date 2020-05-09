package com.example.akhbariapp.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.akhbariapp.Activities.AdminHomeActivity;
import com.example.akhbariapp.Activities.HomeActivity;
import com.example.akhbariapp.Entity.EntityUser;
import com.example.akhbariapp.R;
import com.example.akhbariapp.ViewModel.UserViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;

public class SignIn extends Fragment {

    private TextInputLayout national_id,password;
    private UserViewModel userViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login,container,false);
        SharedPreferences admin = Objects.requireNonNull(getActivity()).getSharedPreferences("Admin", MODE_PRIVATE);
        national_id = root.findViewById(R.id.nat_edit_text_sign_in);
        if(!admin.getString("access","yes").equals("no")){

        if(!admin.getString("first_name","no").equals("no") && admin.getString("user_type","no").equals("admin")){
            Intent intent = new Intent(getActivity(), AdminHomeActivity.class);
            intent.putExtra("user","admin");
            intent.putExtra("id", Objects.requireNonNull(national_id.getEditText()).getText().toString());
            startActivity(intent);
            Objects.requireNonNull(getActivity()).finish();

        }else if (!admin.getString("first_name","no").equals("no") && admin.getString("user_type","no").equals("normal_user")){
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            intent.putExtra("user","normal_user");
            intent.putExtra("national_id", Objects.requireNonNull(national_id.getEditText()).getText().toString());
            startActivity(intent);
            Objects.requireNonNull(getActivity()).finish();
         }
        }

        Button sign_in = root.findViewById(R.id.login_button);
        TextView sign_up_text = root.findViewById(R.id.signUp);

        sign_in.setOnClickListener(v->{
            try {
                login();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        sign_up_text.setOnClickListener(v->{
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(fragmentManager).beginTransaction();

            SignUp signUpFragment = new SignUp();
            fragmentTransaction.replace(R.id.fragment_container_sign,signUpFragment,"tag");
            fragmentTransaction.commit();
        });


        password = root.findViewById(R.id.password_sign_in);

        userViewModel = new UserViewModel(Objects.requireNonNull(getActivity()).getApplication());

        return root;
    }

    private void login() throws ExecutionException, InterruptedException {

       if ((Objects.requireNonNull(national_id.getEditText()).getText().toString().trim().isEmpty() ||
           Objects.requireNonNull(password.getEditText()).getText().toString().trim().isEmpty())){
           Toast.makeText(getContext(),"You have to fill both of the fields",Toast.LENGTH_SHORT).show();
       }else {
           String _password = Objects.requireNonNull(password.getEditText()).getText().toString().trim();
           String nat_id = national_id.getEditText().getText().toString().trim();

           EntityUser user = userViewModel.find(_password,nat_id);

           if (user==null){
               Toast.makeText(getContext(),"Your password or national_id is false",Toast.LENGTH_SHORT).show();
           }else {
               if(user.getAdmin_code()==1234){
                       Intent intent = new Intent(getActivity(), AdminHomeActivity.class);
                       intent.putExtra("user","admin");
                       intent.putExtra("id", Objects.requireNonNull(national_id.getEditText()).getText().toString());
                       startActivity(intent);
                       Objects.requireNonNull(getActivity()).finish();

                   }else if(user.getAdmin_code()==0){
                       Intent intent = new Intent(getActivity(), HomeActivity.class);
                       intent.putExtra("user","normal_user");
                      intent.putExtra("id", Objects.requireNonNull(national_id.getEditText()).getText().toString());
                       startActivity(intent);
                       Objects.requireNonNull(getActivity()).finish();
                   }
               }
           }
       }
    }
