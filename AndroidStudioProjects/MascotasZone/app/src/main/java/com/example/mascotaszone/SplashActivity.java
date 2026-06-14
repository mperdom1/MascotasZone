package com.example.mascotaszone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Mostrar el preloader por 3 segundos
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Rúbrica: Salir primero a lo de agregar contacto (Pantalla Inicial)
                Intent intent = new Intent(SplashActivity.this, AddContactActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000); // 3000 milisegundos = 3 segundos
    }
}
