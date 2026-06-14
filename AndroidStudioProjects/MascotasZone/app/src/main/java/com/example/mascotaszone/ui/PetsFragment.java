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
import com.example.mascotaszone.adapters.PetAdapter;
import com.example.mascotaszone.models.Pet;
import java.util.ArrayList;
import java.util.List;

import com.example.mascotaszone.db.DatabaseHelper;

public class PetsFragment extends Fragment {
    
    private DatabaseHelper dbHelper;
    private PetAdapter adapter;
    private List<Pet> petList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pets, container, false);

        dbHelper = new DatabaseHelper(getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPets);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        petList = dbHelper.getAllPets();
        adapter = new PetAdapter(petList);
        recyclerView.setAdapter(adapter);

        view.findViewById(R.id.btnAddPet).setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(getActivity(), com.example.mascotaszone.AddPetActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh list when returning to fragment
        if (dbHelper != null) {
            petList.clear();
            petList.addAll(dbHelper.getAllPets());
            adapter.notifyDataSetChanged();
        }
    }
}
