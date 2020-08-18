package com.example.akhbariapp.RecyclerViewAdapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.akhbariapp.Entity.PostsEntity;
import com.example.akhbariapp.R;

import java.util.ArrayList;
import java.util.List;

public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder>{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<PostsEntity>posts = new ArrayList<>();
    private OnpostClickListner listner;
    public PostRecyclerViewAdapter(Context context,OnpostClickListner listner) {
        this.context = context;
        this.listner = listner;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = layoutInflater.inflate(R.layout.post_item,parent,false);
        return new ViewHolder(root,listner);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

         holder.post_title.setText(posts.get(position).getTitle());
         holder.read_more.setText(R.string.read_more);
         holder.post_date.setText(posts.get(position).getPost_date().toString("dd-MM-yyyy"));
         holder.post_time.setText(posts.get(position).getPost_time().toString("HH:mm"));

         ImageView post_image = holder.post_image;
         Uri image_uri = Uri.parse(posts.get(position).getImage_uri());

         Glide.with(context)
                .load(image_uri)
                .centerCrop()
                .into(post_image);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setList(List<PostsEntity>posts){
        this.posts = posts;
        notifyDataSetChanged();
    }

//    @Override
//    public Filter getFilter() {
//        return search_post_filter;
//    }
//
//     private Filter search_post_filter = new Filter() {
//         @Override
//         protected FilterResults performFiltering(CharSequence constraint) {
//             List<PostsEntity> filtered_list = new ArrayList<>();
//
//             if(constraint.toString().isEmpty()){
//                 filtered_list.addAll(posts);
//
//             }else {
//                 String filter_pattern = constraint.toString().toLowerCase().trim();
//                 filtered_list.addAll((Collection<? extends PostsEntity>) postsViewModel.get_posts_by_name(filter_pattern));
//             }
//
//             FilterResults filterResults = new FilterResults();
//             filterResults.values = filtered_list;
//             return filterResults;
//         }
//
//         @Override
//         protected void publishResults(CharSequence constraint, FilterResults results) {
//            posts= (List) results.values;
//            notifyDataSetChanged();
//         }
//     };

     class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView post_image;
        private TextView post_title,read_more,post_date,post_time;
        private CardView post_view;
        private PostRecyclerViewAdapter.OnpostClickListner listner_2;
        ViewHolder(@NonNull View itemView,PostRecyclerViewAdapter.OnpostClickListner listner_2) {
            super(itemView);
            post_image = itemView.findViewById(R.id.post_image);
            post_title = itemView.findViewById(R.id.post_title);
            read_more = itemView.findViewById(R.id.post_read_more);
            post_date = itemView.findViewById(R.id.post_date);
            post_time = itemView.findViewById(R.id.post_time);
            post_view = itemView.findViewById(R.id.card_view);
            this.listner_2 = listner_2;

            read_more.setOnClickListener(v ->listner_2.onclick(posts.get(getAdapterPosition())));
            post_view.setOnClickListener(v ->listner_2.onclick(posts.get(getAdapterPosition())));
        }
    }

    public interface OnpostClickListner{
        void onclick(PostsEntity postsEntity);
    }
}
