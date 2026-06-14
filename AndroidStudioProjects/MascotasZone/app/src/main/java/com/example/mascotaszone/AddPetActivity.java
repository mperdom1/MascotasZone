package com.example.mascotaszone;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

import android.net.Uri;
import android.widget.ImageView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.example.mascotaszone.db.DatabaseHelper;
import com.example.mascotaszone.models.Pet;

public class AddPetActivity extends AppCompatActivity {

    private TextInputEditText etName, etBreed, etAge;
    private Button btnSave, btnSelectPhoto;
    private ImageView ivPet;
    private DatabaseHelper dbHelper;
    private Uri selectedImageUri;

    private final ActivityResultLauncher<String> mGetContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    ivPet.setImageURI(uri);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        dbHelper = new DatabaseHelper(this);
        etName = findViewById(R.id.etPetName);
        etBreed = findViewById(R.id.etPetBreed);
        etAge = findViewById(R.id.etPetAge);
        btnSave = findViewById(R.id.btnSavePet);
        ivPet = findViewById(R.id.ivAddPetImage);
        btnSelectPhoto = findViewById(R.id.btnSelectPhoto);

        btnSelectPhoto.setOnClickListener(v -> mGetContent.launch("image/*"));

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String breed = etBreed.getText().toString();
            String ageStr = etAge.getText().toString();

            if (name.isEmpty() || breed.isEmpty() || ageStr.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                int age = Integer.parseInt(ageStr);
                Pet pet = new Pet(null, name, breed, age, "current_user_id");
                if (selectedImageUri != null) {
                    pet.setImageUri(selectedImageUri.toString());
                }
                dbHelper.addPet(pet);
                Toast.makeText(this, "Mascota " + name + " guardada exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
