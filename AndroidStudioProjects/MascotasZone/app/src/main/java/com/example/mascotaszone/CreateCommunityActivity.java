package com.example.mascotaszone;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

import com.example.mascotaszone.db.DatabaseHelper;
import com.example.mascotaszone.models.Community;

public class CreateCommunityActivity extends AppCompatActivity {

    private TextInputEditText etName, etDescription;
    private Button btnCreate;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community);

        dbHelper = new DatabaseHelper(this);
        etName = findViewById(R.id.etCommunityName);
        etDescription = findViewById(R.id.etCommunityDescription);
        btnCreate = findViewById(R.id.btnSaveCommunity);

        btnCreate.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String desc = etDescription.getText().toString();

            if (name.isEmpty() || desc.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                Community community = new Community(null, name, desc);
                dbHelper.addCommunity(community);
                Toast.makeText(this, "Comunidad '" + name + "' creada exitosamente!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
