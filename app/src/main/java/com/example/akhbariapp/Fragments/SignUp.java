package com.example.akhbariapp.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.akhbariapp.Activities.AdminHomeActivity;
import com.example.akhbariapp.Activities.HomeActivity;
import com.example.akhbariapp.Entity.EntityUser;
import com.example.akhbariapp.Entity.NationalCardsEntity;
import com.example.akhbariapp.R;
import com.example.akhbariapp.ViewModel.NationalCardsViewModel;
import com.example.akhbariapp.ViewModel.UserViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;

public class SignUp extends Fragment {

    private TextInputLayout first_name,last_name,password,national_id,admin_code;
    private AutoCompleteTextView city;
    private CheckBox admin_code_checkbox;
    private UserViewModel userViewModel;
    private NationalCardsViewModel nationalCardsViewModel;
    private SharedPreferences admin;
    private static final String[] cities = new String []{"Oran","Algiers","Anaba","Tizi","Tiaret","Chlef","Blida"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.sign_up_fragment,container,false);
        admin = Objects.requireNonNull(getActivity()).getSharedPreferences("Admin",MODE_PRIVATE);

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
        nationalCardsViewModel = new NationalCardsViewModel(getActivity().getApplication());
        Button sign_up = root.findViewById(R.id.sign_up_button);
        sign_up.setOnClickListener(v-> {
            try {
                add_user();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, cities);
        city.setAdapter(adapter);

        return root;
    }


    private void add_user() throws ExecutionException, InterruptedException {
        if((Objects.requireNonNull(first_name.getEditText()).getText().toString().trim().isEmpty()  ||
            Objects.requireNonNull(last_name.getEditText()).getText().toString().trim().isEmpty()   ||
            Objects.requireNonNull(password.getEditText()).getText().toString().trim().isEmpty()    ||
            Objects.requireNonNull(national_id.getEditText()).getText().toString().trim().isEmpty() ||
            city.getEditableText().toString().trim().isEmpty()) || (admin_code_checkbox.isChecked() &&
              Objects.requireNonNull(admin_code.getEditText()).getText().toString().trim().isEmpty()))
        {
            Toast.makeText(getContext(),"You have to fill all the fields",Toast.LENGTH_SHORT).show();
        }
        else {
            String firstname = first_name.getEditText().getText().toString().trim().toUpperCase();
            String lastname = Objects.requireNonNull(last_name.getEditText()).getText().toString().trim().toUpperCase();
            String _password = Objects.requireNonNull(password.getEditText()).getText().toString().trim();
            int nat_id = Integer.parseInt(Objects.requireNonNull(national_id.getEditText()).getText().toString().trim());
            String _city = city.getEditableText().toString().trim().toUpperCase();


            NationalCardsEntity nationalCard = nationalCardsViewModel.check_nat(firstname,lastname,_city,String.valueOf(nat_id));

            if(nationalCard==null){
                Toast.makeText(getContext(),"You are not in the government database",Toast.LENGTH_SHORT).show();
                return;
            }

            EntityUser user_check = userViewModel.check(firstname,lastname,String.valueOf(nat_id));

            if(user_check!=null){
                Toast.makeText(getContext(),"You already have an account",Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor fields_save = admin.edit();
            if(admin_code_checkbox.isChecked()){
                int _admin_code = Integer.parseInt(Objects.requireNonNull(admin_code.getEditText()).getText().toString().trim());
                if(_admin_code==admin.getInt("admin_code",0)){


                    EntityUser user = new EntityUser(firstname,lastname,_password,_city,nat_id,_admin_code,"normal_user");
                    userViewModel.add_user(user);

                    fields_save.putString("first_name",firstname);
                    fields_save.putString("user_type","admin");
                    fields_save.apply();
                    Intent intent = new Intent(getActivity(), AdminHomeActivity.class);
                    intent.putExtra("user","admin");
                    startActivity(intent);
                    Objects.requireNonNull(getActivity()).finish();
                }else {
                    Toast.makeText(getContext(),"Admin code is wrong",Toast.LENGTH_SHORT).show();
                }
            }else {
                EntityUser user = new EntityUser(firstname,lastname,_password,_city,nat_id,0,"admin");
                userViewModel.add_user(user);

                fields_save.putString("first_name",firstname);
                fields_save.putString("user_type","normal_user");
                fields_save.apply();

                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("user","normal_user");
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();
            }

        }
    }
}
