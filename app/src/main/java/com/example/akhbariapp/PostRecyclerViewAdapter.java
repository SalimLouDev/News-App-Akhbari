package com.example.akhbariapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.akhbariapp.Entity.PostsEntity;

import java.util.List;

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
         holder.post_title.setText(posts.get(position).getTitle());
         holder.read_more.setText(posts.get(position).getDescription());
         holder.post_date.setText("25-04-2020");
         holder.post_time.setText("11:34");

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView post_image;
        private TextView post_title,read_more,post_date,post_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            post_image = itemView.findViewById(R.id.post_image);
            post_title = itemView.findViewById(R.id.post_title);
            read_more = itemView.findViewById(R.id.post_read_more);
            post_date = itemView.findViewById(R.id.post_date);
            post_time = itemView.findViewById(R.id.post_time);
        }
    }
}
