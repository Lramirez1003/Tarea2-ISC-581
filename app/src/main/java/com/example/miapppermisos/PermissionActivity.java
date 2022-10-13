package com.example.miapppermisos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.Button;

public class PermissionActivity extends AppCompatActivity {

    Button btnStorage, btnLocation,btnPhone,btnCamera,btnContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnStorage = findViewById(R.id.btnStorage);
        btnLocation = findViewById(R.id.btnLocation);
        btnPhone = findViewById(R.id.btnPhone);
        btnCamera = findViewById(R.id.btnCamera);
        btnContacts = findViewById(R.id.btnContacts);

        btnStorage.setOnClickListener((v)->{
            Intent intentStorage = new Intent(Intent.ACTION_VIEW);
            intentStorage.setType("image/*");
            intentStorage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentStorage);

        });
        btnLocation.setOnClickListener((v)->{
            Intent intentLocation = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intentLocation);

        });
        btnPhone.setOnClickListener((v)->{
            Intent intentPhone = new Intent(Intent.ACTION_CALL_BUTTON);
            startActivity(intentPhone);

        });
        btnCamera.setOnClickListener((v)->{
            Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intentCamera);

        });
        btnContacts.setOnClickListener((v)->{
            Intent intentContacts = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivity(intentContacts);

        });


    }

}