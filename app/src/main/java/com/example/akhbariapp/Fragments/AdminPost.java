package com.example.akhbariapp.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.akhbariapp.Activities.AdminHomeActivity;
import com.example.akhbariapp.Entity.PostsEntity;
import com.example.akhbariapp.R;
import com.example.akhbariapp.ViewModel.PostsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import org.joda.time.LocalDate;

import java.util.Date;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class AdminPost extends Fragment {

    private static final String[] cities = new String []{"Oran","Algiers","Anaba","Tizi","Tiaret","Chlef","Blida"};
    private static final String[] post_types = new String[]{"Health","Sport","Transport","Politics","Education"};
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private Animation animation;
    private String image_uri;
    private PostsViewModel postsViewModel;
    private TextInputLayout post_title;
    private TextInputLayout post_desc;
    private AutoCompleteTextView city;
    private AutoCompleteTextView post_type;
    public AdminPost(Animation animation){
        this.animation=animation;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.admin_post,container,false);
        AdminHomeActivity homeActivity = (AdminHomeActivity) getActivity();
        Objects.requireNonNull(Objects.requireNonNull(homeActivity).getSupportActionBar()).setTitle(getString(R.string.post));

        city = root.findViewById(R.id.city);
        ArrayAdapter adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1, cities);
        city.setAdapter(adapter);

        post_type = root.findViewById(R.id.post_type);
        ArrayAdapter post_Type_Adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()),android.R.layout.simple_list_item_1,post_types);
        post_type.setAdapter(post_Type_Adapter);

        post_title = root.findViewById(R.id.post_title);
        post_desc = root.findViewById(R.id.post_desc);

        FloatingActionButton fab = root.findViewById(R.id.floating_action_button_for_send_post);
        fab.startAnimation(animation);
        ImageView image_button = root.findViewById(R.id.admin_post_image);

        //Button click listener for picking image
        image_button.setOnClickListener(v -> {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                    String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permissions,PERMISSION_CODE);
                }else {
                    pickImageFromGallery();
                }
            }else {
                pickImageFromGallery();
            }
        });

        fab.setOnClickListener(v-> add_post());

        postsViewModel = new PostsViewModel(getActivity().getApplication());
        return root;
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select a picture"), IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImageFromGallery();
            } else {
                Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            assert data != null;
            Uri selectedURIImage = data.getData();
            image_uri= Objects.requireNonNull(selectedURIImage).toString();
            ImageView image_container = Objects.requireNonNull(getActivity()).findViewById(R.id.post_image_center);
            image_container.setImageURI(selectedURIImage);
        }
    }

    private void add_post(){
        LocalDate post_date = LocalDate.now();
        String title = Objects.requireNonNull(post_title.getEditText()).getText().toString().trim();
        String desc = Objects.requireNonNull(post_desc.getEditText()).getText().toString().trim();
        String _city = city.getEditableText().toString().trim();
        String _post_type = post_type.getEditableText().toString().trim();
        postsViewModel.add_post(new PostsEntity(title, desc, _city, _post_type,post_date,image_uri));
    }
}
