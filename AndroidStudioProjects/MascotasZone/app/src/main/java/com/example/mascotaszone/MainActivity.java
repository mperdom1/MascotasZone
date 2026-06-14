package com.example.mascotaszone;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import com.example.mascotaszone.ui.ChatFragment;
import com.example.mascotaszone.ui.CommunitiesFragment;
import com.example.mascotaszone.ui.HomeFragment;
import com.example.mascotaszone.ui.ContactsFragment;
import com.example.mascotaszone.ui.CommunitiesFragment;
import com.example.mascotaszone.ui.HomeFragment;
import com.example.mascotaszone.ui.PetsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets sb = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(sb.left, sb.top, sb.right, sb.bottom);
            return insets;
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.nav_communities) {
                selectedFragment = new CommunitiesFragment();
            } else if (itemId == R.id.nav_pets) {
                selectedFragment = new PetsFragment();
            } else if (itemId == R.id.nav_contacts) {
                selectedFragment = new ContactsFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, selectedFragment)
                        .commit();
            }
            return true;
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, new ContactsFragment())
                    .commit();
            // Asegurar que el icono de contactos esté seleccionado en el menú inferior
            bottomNav.setSelectedItemId(R.id.nav_contacts);
        }
    }
}
