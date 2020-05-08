package com.example.akhbariapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.akhbariapp.Entity.PostsEntity;
import com.example.akhbariapp.R;

import java.util.Objects;

public class PostDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        Intent intent = getIntent();
        PostsEntity post_details= (PostsEntity) intent.getSerializableExtra("post");
        TextView post_title = findViewById(R.id.post_details_title);
        post_title.setText(Objects.requireNonNull(post_details).getTitle());

        TextView post_desc = findViewById(R.id.post_details_desc);
        post_desc.setText(post_details.getDescription());

        ImageView post_image = findViewById(R.id.post_details_image);

        Uri image_uri = Uri.parse(post_details.getImage_uri());

        Glide.with(this)
                .load(image_uri)
                .centerCrop()
                .into(post_image);
    }
}
