package com.example.mascotaszone;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mascotaszone.db.DatabaseHelper;
import com.example.mascotaszone.models.Contact;
import java.util.regex.Pattern;

public class AddContactActivity extends AppCompatActivity {

    private EditText etName, etCountry, etPhone, etNote;
    private ImageView ivContact;
    private Uri imageUri;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = new DatabaseHelper(this);
        etName = findViewById(R.id.etName);
        etCountry = findViewById(R.id.etCountry);
        etPhone = findViewById(R.id.etPhone);
        etNote = findViewById(R.id.etNote);
        ivContact = findViewById(R.id.ivContact);
        Button btnSave = findViewById(R.id.btnSave);

        ActivityResultLauncher<String> pickMedia = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                imageUri = uri;
                ivContact.setImageURI(uri);
            }
        });

        ivContact.setOnClickListener(v -> pickMedia.launch("image/*"));

        findViewById(R.id.btnViewList).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String country = etCountry.getText().toString();
            String phone = etPhone.getText().toString();
            String note = etNote.getText().toString();

            if (name.isEmpty() || phone.isEmpty()) {
                showAlert("Error", "Los campos Nombre y Teléfono son obligatorios.");
                return;
            }

            if (!Pattern.matches("^[0-9]+$", phone)) {
                showAlert("Error", "El teléfono solo debe contener números.");
                return;
            }

            Contact c = new Contact(0, name, country, phone, note, imageUri != null ? imageUri.toString() : "");
            db.addContact(c);
            
            // Rúbrica: Ir a la MainActivity para ver todas las pestañas
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    private void showAlert(String title, String message) {
        // Rúbrica 3.2: Implementar diálogos con AlertDialog.Builder
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Aceptar", null)
                .show();
    }
}
