package com.example.mascotaszone;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chat_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets ime = insets.getInsets(WindowInsetsCompat.Type.ime());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, Math.max(systemBars.bottom, ime.bottom));
            return insets;
        });

        String userName = getIntent().getStringExtra("user_name");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Chat con " + userName);
        }

        findViewById(R.id.btnSend).setOnClickListener(v -> {
            // Lógica de envío simulada
            android.widget.Toast.makeText(this, "Mensaje enviado (simulación)", android.widget.Toast.LENGTH_SHORT).show();
            ((android.widget.EditText)findViewById(R.id.etMessage)).setText("");
        });
    }
}
