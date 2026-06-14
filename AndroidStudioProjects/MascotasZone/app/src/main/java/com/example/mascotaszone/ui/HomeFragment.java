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
import com.example.mascotaszone.adapters.ServiceRequestAdapter;
import com.example.mascotaszone.models.ServiceRequest;
import java.util.ArrayList;
import java.util.List;

import com.example.mascotaszone.db.DatabaseHelper;

public class HomeFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private ServiceRequestAdapter adapter;
    private List<ServiceRequest> requestList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        dbHelper = new DatabaseHelper(getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        requestList = dbHelper.getAllServiceRequests();
        adapter = new ServiceRequestAdapter(requestList);
        recyclerView.setAdapter(adapter);

        view.findViewById(R.id.fabAddRequest).setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(getActivity(), com.example.mascotaszone.CreateServiceRequestActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (dbHelper != null) {
            requestList.clear();
            requestList.addAll(dbHelper.getAllServiceRequests());
            adapter.notifyDataSetChanged();
        }
    }
}
