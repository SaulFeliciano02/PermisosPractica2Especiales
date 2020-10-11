package com.example.intentosexplicitospractica2especiales;

import android.Manifest;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class RespuestaConfig extends AppCompatActivity {

    private TextView camera;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private TextView almacenamiento;
    static final int REQUEST_STORAGE_ACCESS = 2;
    private TextView ubicacion;
    static final int REQUEST_LOCATION_ACCESS = 3;
    private TextView telefono;
    static final int REQUEST_PHONE_ACCESS = 4;
    private TextView contactos;
    static final int REQUEST_CONTACTS_ACCESS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta_config);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        camera = findViewById(R.id.lblCameraButton);

        camera.setOnClickListener((v)->{
            dispatchTakePictureIntent();
        });

        almacenamiento = findViewById(R.id.lblAlmacenamientoButton);

        almacenamiento.setOnClickListener(v -> {
            openDirectory(Uri.parse("/documents/"));
        });

        ubicacion = findViewById(R.id.lblLocationButton);

        ubicacion.setOnClickListener(v -> {
            openMap();
        });

        telefono = findViewById(R.id.lblPhoneButton);

        telefono.setOnClickListener(v -> {
            makeCall();
        });

        contactos = findViewById(R.id.lblContactsButton);

        contactos.setOnClickListener(v -> {
            openContacts();
        });
    }

    private void dispatchTakePictureIntent() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {
            new AlertDialog.Builder(this).setTitle("Aviso")
                    .setMessage("Desea abrir la camara para verse lindo?")
                    .setPositiveButton("ok", (dialog, which) -> {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                        }
                    })
                    .setNegativeButton("cancelar", (dialog, which) -> dialog.dismiss())
                    .create().show();
        }
        else
        {
            Toast.makeText(this, "El permiso a la camara no ha sido concedido", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Toast.makeText(this, "Imagen capturada", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == REQUEST_STORAGE_ACCESS && resultCode == RESULT_OK) {
            Toast.makeText(this, "Almacenamiento accedido", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == REQUEST_LOCATION_ACCESS && resultCode == RESULT_OK) {
            Toast.makeText(this, "Localizacion accedida", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == REQUEST_PHONE_ACCESS && resultCode == RESULT_OK) {
            Toast.makeText(this, "Llamada accedida", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == REQUEST_CONTACTS_ACCESS && resultCode == RESULT_OK) {
            Toast.makeText(this, "Contactos accedidos", Toast.LENGTH_SHORT).show();
        }
    }

    public void openDirectory(Uri uriToLoad) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {
            new AlertDialog.Builder(this).setTitle("Aviso")
                    .setMessage("Desea abrir el explorador de archivos para ver todos los disparates que tiene?")
                    .setPositiveButton("ok", (dialog, which) -> {
                        // Choose a directory using the system's file picker.
                        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);

                        // Provide read access to files and sub-directories in the user-selected
                        // directory.
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        // Optionally, specify a URI for the directory that should be opened in
                        // the system file picker when it loads.
                        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uriToLoad);

                        startActivityForResult(intent, REQUEST_STORAGE_ACCESS);
                    })
                    .setNegativeButton("cancelar", (dialog, which) -> dialog.dismiss())
                    .create().show();
        }
        else
        {
            Toast.makeText(this, "El permiso al almacenamiento no ha sido concedido", Toast.LENGTH_SHORT).show();
        }
    }

    private void openMap()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            new AlertDialog.Builder(this).setTitle("Aviso")
                    .setMessage("Desea abrir el mapa de google para que recuerde donde usted vive?")
                    .setPositiveButton("ok", (dialog, which) -> {
                        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/@?api=1&map_action=pano&pano=4U-oRQCNsC6u7r8gp02sLA");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivityForResult(mapIntent, REQUEST_LOCATION_ACCESS);
                    })
                    .setNegativeButton("cancelar", (dialog, which) -> dialog.dismiss())
                    .create().show();
        }
        else
        {
            Toast.makeText(this, "El permiso a la ubicacion no ha sido concedido", Toast.LENGTH_SHORT).show();
        }
    }

    private void makeCall()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
        {
            new AlertDialog.Builder(this).setTitle("Aviso")
                    .setMessage("Desea gastar sus minutos hablando solo?")
                    .setPositiveButton("ok", (dialog, which) -> {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:0377778888"));
                        startActivityForResult(callIntent, REQUEST_PHONE_ACCESS);
                    })
                    .setNegativeButton("cancelar", (dialog, which) -> dialog.dismiss())
                    .create().show();
        }
        else
        {
            Toast.makeText(this, "El permiso a llamadas no ha sido concedido", Toast.LENGTH_SHORT).show();
        }
    }

    private void openContacts()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
        {
            new AlertDialog.Builder(this).setTitle("Aviso")
                    .setMessage("Desea ver sus contactos para que no hable solo?")
                    .setPositiveButton("ok", (dialog, which) -> {
                        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        startActivityForResult(intent, REQUEST_CONTACTS_ACCESS);
                    })
                    .setNegativeButton("cancelar", (dialog, which) -> dialog.dismiss())
                    .create().show();
        }
        else
        {
            Toast.makeText(this, "El permiso a los contactos no ha sido concedido", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}