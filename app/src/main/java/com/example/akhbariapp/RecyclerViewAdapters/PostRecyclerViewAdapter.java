package com.example.akhbariapp.RecyclerViewAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.akhbariapp.Entity.PostsEntity;
import com.example.akhbariapp.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder>{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<PostsEntity>posts;
    public PostRecyclerViewAdapter(Context context,List<PostsEntity>posts) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = layoutInflater.inflate(R.layout.post_item,parent,false);
        return new ViewHolder(root);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD-MM-YYYY");
         holder.post_title.setText(posts.get(position).getTitle());
         holder.read_more.setText(R.string.read_more);
         holder.post_date.setText(simpleDateFormat.format(posts.get(position).getPost_date()));
         holder.post_time.setText(posts.get(position).getPost_date().getHours()+":"+posts.get(position).getPost_date().getMinutes());
         Uri image_uri = Uri.parse(posts.get(position).getImage_uri());

          Glide.with(context)
          .load(new File(Objects.requireNonNull(image_uri.getPath())))
          .into(holder.post_image);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setList(List<PostsEntity>posts){
        this.posts = posts;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView post_image;
        private TextView post_title,read_more,post_date,post_time;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            post_image = itemView.findViewById(R.id.post_image);
            post_title = itemView.findViewById(R.id.post_title);
            read_more = itemView.findViewById(R.id.post_read_more);
            post_date = itemView.findViewById(R.id.post_date);
            post_time = itemView.findViewById(R.id.post_time);
        }
    }
}
