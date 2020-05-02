package com.example.akhbariapp.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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

    private TextInputLayout national_id,password,admin_code;
    private CheckBox admin_code_checkbox;
    private UserViewModel userViewModel;
    private SharedPreferences admin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login,container,false);
        admin = Objects.requireNonNull(getActivity()).getSharedPreferences("Admin",MODE_PRIVATE);

        if(admin.getInt("admin_code",0)!=0 && !admin.getString("first_name","no").equals("no")){
            Intent intent = new Intent(getActivity(), AdminHomeActivity.class);
            intent.putExtra("user","admin");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).finish();

        }else if (!admin.getString("first_name","no").equals("no") && admin.getInt("admin_code",0)==0){
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            intent.putExtra("user","normal_user");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).finish();
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

        national_id = root.findViewById(R.id.nat_edit_text_sign_in);
        password = root.findViewById(R.id.password_sign_in);
        admin_code = root.findViewById(R.id.admin_code_sign_in);
        admin_code_checkbox = root.findViewById(R.id.is_admin_sign_in);

        admin_code_checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)
                admin_code.setVisibility(View.VISIBLE);
            else
                admin_code.setVisibility(View.INVISIBLE);
        });
        userViewModel = new UserViewModel(Objects.requireNonNull(getActivity()).getApplication());

        return root;
    }

    private void login() throws ExecutionException, InterruptedException {

       if ((Objects.requireNonNull(national_id.getEditText()).getText().toString().trim().isEmpty() ||
           Objects.requireNonNull(password.getEditText()).getText().toString().trim().isEmpty()) && !admin_code_checkbox.isChecked()){
           Toast.makeText(getContext(),"You have to fill both of the fields",Toast.LENGTH_SHORT).show();
       }else if(admin_code_checkbox.isChecked() &&
               Objects.requireNonNull(admin_code.getEditText()).getText().toString().trim().isEmpty()){
           Toast.makeText(getContext(),"You have to write the admin code",Toast.LENGTH_SHORT).show();
       }else {
           String _password = Objects.requireNonNull(password.getEditText()).getText().toString().trim();
           String nat_id = national_id.getEditText().getText().toString().trim();

           EntityUser user = userViewModel.find(_password,nat_id);

           if(admin_code_checkbox.isChecked()){
               int _admin_code = Integer.parseInt(Objects.requireNonNull(admin_code.getEditText()).getText().toString().trim());
               if(user!=null && _admin_code==admin.getInt("admin_code",0)){
                   if(user.getAdmin_code()==0){
                       Toast.makeText(getContext(),"You are not admin",Toast.LENGTH_SHORT).show();
                   }else {
                       Intent intent = new Intent(getActivity(), AdminHomeActivity.class);
                       intent.putExtra("user","admin");
                       startActivity(intent);
                       Objects.requireNonNull(getActivity()).finish();
                   }
               }else{

                   Toast.makeText(getContext(),"The admin code is wrong",Toast.LENGTH_SHORT).show();
               }

           }else {
               if(user!=null){
                   Intent intent = new Intent(getActivity(), HomeActivity.class);
                   intent.putExtra("user","normal_user");
                   startActivity(intent);
                   Objects.requireNonNull(getActivity()).finish();
               }else {
                   Toast.makeText(getContext(),"Your password or national_id is false",Toast.LENGTH_SHORT).show();
               }
           }
       }
    }
}
