package com.example.mascotaszone.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.mascotaszone.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mascotaszone.adapters.CommunityAdapter;
import com.example.mascotaszone.models.Community;
import java.util.ArrayList;
import java.util.List;

import com.example.mascotaszone.db.DatabaseHelper;

public class CommunitiesFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private CommunityAdapter adapter;
    private List<Community> communityList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_communities, container, false);

        dbHelper = new DatabaseHelper(getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewCommunities);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        communityList = dbHelper.getAllCommunities();
        adapter = new CommunityAdapter(communityList);
        recyclerView.setAdapter(adapter);

        view.findViewById(R.id.btnCreateCommunity).setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(getActivity(), com.example.mascotaszone.CreateCommunityActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (dbHelper != null) {
            communityList.clear();
            communityList.addAll(dbHelper.getAllCommunities());
            adapter.notifyDataSetChanged();
        }
    }
}
