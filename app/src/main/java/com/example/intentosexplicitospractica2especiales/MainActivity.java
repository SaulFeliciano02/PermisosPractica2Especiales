package com.example.intentosexplicitospractica2especiales;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private TextView btnAceptar, btnCancelar;
    private Switch swiAlmacenamiento, swiUbicacion, swiCamara, swiTelefono, swiContactos;
    private ArrayList<String> permisos = new ArrayList<>();
    private int PERMISOS_PERMISSION_CODE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAceptar = findViewById(R.id.btnAceptar);
        btnCancelar = findViewById(R.id.btnCancelar);
        swiAlmacenamiento = findViewById(R.id.swiAlmacenamiento);
        swiUbicacion = findViewById(R.id.swiUbicacion);
        swiCamara = findViewById(R.id.swiCamara);
        swiTelefono = findViewById(R.id.swiTelefono);
        swiContactos = findViewById(R.id.swiContactos);

        ActivarSwitches();

        if (getIntent().getExtras() != null && getIntent().getExtras().getBoolean("EXIT", false)) {
            finish();
            System.exit(0);
        }

        btnAceptar.setOnClickListener(v -> {
            if(swiAlmacenamiento.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    permisos.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                }
            }
            if(swiUbicacion.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                    permisos.add(Manifest.permission.ACCESS_FINE_LOCATION);
                }
            }
            if(swiCamara.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    permisos.add(Manifest.permission.CAMERA);
                }
            }
            if(swiTelefono.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
                    permisos.add(Manifest.permission.CALL_PHONE);
                }
            }
            if(swiContactos.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED) {
                    permisos.add(Manifest.permission.READ_CONTACTS);
                }
            }
            if(permisos.size() > 0)
            {
                ActivityCompat.requestPermissions(MainActivity.this, permisos.toArray(new String[permisos.size()]), PERMISOS_PERMISSION_CODE);
            }
            else
            {
                Intent intent = new Intent(getApplicationContext(), RespuestaConfig.class);
                startActivity(intent);
            }
        });

        btnCancelar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        });
    }

    private void ActivarSwitches() {
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
        {
            swiCamara.setChecked(true);
            swiCamara.setClickable(false);
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            swiUbicacion.setChecked(true);
            swiUbicacion.setClickable(false);
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
        {
            swiTelefono.setChecked(true);
            swiTelefono.setClickable(false);
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {
            swiAlmacenamiento.setChecked(true);
            swiAlmacenamiento.setClickable(false);
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
        {
            swiContactos.setChecked(true);
            swiContactos.setClickable(false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (requestCode == PERMISOS_PERMISSION_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(MainActivity.this, "Permisos Concedido", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), RespuestaConfig.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(MainActivity.this, "Permisos Negado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}