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
import com.example.mascotaszone.adapters.ChatAdapter;
import com.example.mascotaszone.models.ChatPreview;
import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewChats);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<ChatPreview> dummyChats = new ArrayList<>();
        dummyChats.add(new ChatPreview("1", "Juan Pérez", "¿A qué hora pasas por Firulais?", "10:30 AM"));
        dummyChats.add(new ChatPreview("2", "María García", "¡Gracias por cuidar a Michi!", "Ayer"));
        dummyChats.add(new ChatPreview("3", "Carlos López", "Visto en el parque central.", "Lunes"));

        ChatAdapter adapter = new ChatAdapter(dummyChats);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
