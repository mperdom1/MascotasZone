package com.example.mascotaszone;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mascotaszone.models.ServiceRequest;
import com.google.android.material.textfield.TextInputEditText;

import com.example.mascotaszone.db.DatabaseHelper;

public class CreateServiceRequestActivity extends AppCompatActivity {

    private Spinner spinnerType;
    private TextInputEditText etDescription;
    private Button btnSave;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service_request);

        dbHelper = new DatabaseHelper(this);
        spinnerType = findViewById(R.id.spinnerServiceType);
        etDescription = findViewById(R.id.etRequestDescription);
        btnSave = findViewById(R.id.btnSaveRequest);

        String[] types = {"CARPOOL", "BABYSITTING", "SOCIALIZATION"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String desc = etDescription.getText().toString();
            String typeStr = spinnerType.getSelectedItem().toString();

            if (desc.isEmpty()) {
                Toast.makeText(this, "Por favor describe tu solicitud", Toast.LENGTH_SHORT).show();
            } else {
                ServiceRequest.ServiceType type = ServiceRequest.ServiceType.valueOf(typeStr);
                ServiceRequest request = new ServiceRequest(null, "current_user_id", "c1", type, desc);
                dbHelper.addServiceRequest(request);
                Toast.makeText(this, "Solicitud de " + typeStr + " publicada exitosamente!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
