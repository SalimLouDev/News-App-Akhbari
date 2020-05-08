package com.example.akhbariapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.akhbariapp.Activities.AdminHomeActivity;
import com.example.akhbariapp.Activities.HomeActivity;
import com.example.akhbariapp.Activities.PostDetailsActivity;
import com.example.akhbariapp.Entity.PostsEntity;
import com.example.akhbariapp.RecyclerViewAdapters.PostRecyclerViewAdapter;
import com.example.akhbariapp.R;
import com.example.akhbariapp.ViewModel.PostsViewModel;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Today extends Fragment implements PostRecyclerViewAdapter.OnpostClickListner{

    private PostRecyclerViewAdapter adapter;
    private PostsViewModel postsViewModel;
    private HomeActivity homeActivity;
    private AdminHomeActivity adminHomeActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.posts_fragment,container,false);
        String user_state = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("user");

        if(Objects.equals(user_state, "normal_user")){
            homeActivity = (HomeActivity) getActivity();
            Objects.requireNonNull(Objects.requireNonNull(homeActivity).getSupportActionBar()).setTitle(getString(R.string.today));
        }
        else if(Objects.equals(user_state, "admin")){
            adminHomeActivity = (AdminHomeActivity) getActivity();
            Objects.requireNonNull(Objects.requireNonNull(adminHomeActivity).getSupportActionBar()).setTitle(getString(R.string.today));
        }

        postsViewModel = new ViewModelProvider(this).get(PostsViewModel.class);
        RecyclerView post_list = root.findViewById(R.id.post_recycler_view);
        post_list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PostRecyclerViewAdapter(getContext(),this);
        post_list.setAdapter(adapter);

        LocalDate today_date = LocalDate.now();
        postsViewModel.gettodayposts(today_date.toDate().getTime()).observe(getViewLifecycleOwner(), postsEntities -> adapter.setList(postsEntities));

        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.action_menu,menu);

        MenuItem search_item = menu.findItem(R.id.settings);
        SearchView searchView = (SearchView) search_item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                LocalDate today_date = LocalDate.now();
                postsViewModel.get_posts_by_name(newText,today_date.toDate().getTime()).observe(getViewLifecycleOwner(), postsEntities -> adapter.setList(postsEntities));
                return false;
            }
        });
    }

    @Override
    public void onclick(PostsEntity postsEntity) {

        Intent intent = new Intent(getContext(), PostDetailsActivity.class);
        intent.putExtra("post",postsEntity);
        startActivity(intent);
    }
}
