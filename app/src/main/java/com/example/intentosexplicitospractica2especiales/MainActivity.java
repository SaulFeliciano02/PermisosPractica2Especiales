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
    private int CAMERA_PERMISSION_CODE = 100;
    private int LOCATION_PERMISSION_CODE = 101;
    private int PHONE_PERMISSION_CODE = 102;
    private int CONTACTS_PERMISSION_CODE = 103;
    private int STORAGE_PERMISSION_CODE = 104;
    private ArrayList<String> permisos = new ArrayList<>();
    private ArrayList<Integer> codigos = new ArrayList<>();
    private boolean CENTINELA = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAceptar = findViewById(R.id.btnAceptar);
        //btnAceptar.setOnClickListener(this);
        btnCancelar = findViewById(R.id.btnCancelar);
        //btnCancelar.setOnClickListener(this);
        swiAlmacenamiento = findViewById(R.id.swiAlmacenamiento);
        swiUbicacion = findViewById(R.id.swiUbicacion);
        swiCamara = findViewById(R.id.swiCamara);
        swiTelefono = findViewById(R.id.swiTelefono);
        swiContactos = findViewById(R.id.swiContactos);

        ActivarSwitches();

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swiCamara.isChecked()) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "Permiso a camara ya ha sido otorgado", Toast.LENGTH_SHORT).show();
                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
                    }
                }
                if(swiUbicacion.isChecked()) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "Permiso a ubicacion ya ha sido otorgado", Toast.LENGTH_SHORT).show();
                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
                    }
                }
                if(swiAlmacenamiento.isChecked()) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "Permiso a almacenamiento ya ha sido otorgado", Toast.LENGTH_SHORT).show();
                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                    }
                }
                if(swiContactos.isChecked()) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "Permiso a contactos ya ha sido otorgado", Toast.LENGTH_SHORT).show();
                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_CONTACTS}, CONTACTS_PERMISSION_CODE);
                    }
                }
                if(swiTelefono.isChecked()) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "Permiso a telefono ya ha sido otorgado", Toast.LENGTH_SHORT).show();
                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CALL_PHONE}, PHONE_PERMISSION_CODE);
                    }
                }
                if(CENTINELA)
                {
                    Intent intent = new Intent(getApplicationContext(), RespuestaConfig.class);
                    startActivity(intent);
                    CENTINELA = false;
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });
    }

    private void ActivarSwitches() {
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
        {
            swiCamara.setChecked(true);
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            swiUbicacion.setChecked(true);
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
        {
            swiTelefono.setChecked(true);
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {
            swiAlmacenamiento.setChecked(true);
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
        {
            swiContactos.setChecked(true);
        }
    }

    /*@Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btnAceptar:
                if(swiCamara.isChecked())
                {
                    System.out.println("Sdkver: " + Integer.valueOf(android.os.Build.VERSION.SDK));
                    if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(MainActivity.this, "Permiso a camara ya ha sido otorgado", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //requestCameraPermission();
                        System.out.println("entre aqui");
                        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
                    }
                }
                Intent intent = new Intent(getApplicationContext(), RespuestaConfig.class);
                startActivity(intent);
                break;
            case R.id.btnCancelar:
                finishAndRemoveTask();
                break;
            default:
                break;
        }
    }*/

   /* private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
        {
            new AlertDialog.Builder(this).setTitle("Permiso necesario")
                    .setMessage("Este permiso es necesario para abrir tu camara y salir beio")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(MainActivity.this, "Permiso Camara Concedido", Toast.LENGTH_SHORT).show();
                CENTINELA = true;
            }
            else
            {
                Toast.makeText(MainActivity.this, "Permiso Camara Negado", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == LOCATION_PERMISSION_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(MainActivity.this, "Permiso Ubicacion Concedido", Toast.LENGTH_SHORT).show();
                CENTINELA = true;
            }
            else
            {
                Toast.makeText(MainActivity.this, "Permiso Ubicacion Negado", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == PHONE_PERMISSION_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(MainActivity.this, "Permiso telefono Concedido", Toast.LENGTH_SHORT).show();
                CENTINELA = true;
            }
            else
            {
                Toast.makeText(MainActivity.this, "Permiso telefono Negado", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == CONTACTS_PERMISSION_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(MainActivity.this, "Permiso Contactos Concedido", Toast.LENGTH_SHORT).show();
                CENTINELA = true;
            }
            else
            {
                Toast.makeText(MainActivity.this, "Permiso Contactos Negado", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == STORAGE_PERMISSION_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(MainActivity.this, "Permiso Almacenamiento Concedido", Toast.LENGTH_SHORT).show();
                CENTINELA = true;
            }
            else
            {
                Toast.makeText(MainActivity.this, "Permiso Almacenamiento Negado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}