package com.example.miapppermisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static int permission = 200;

    Button cancelar, continuar;
    Switch swStorage, swCamera,swLocation,swPhone,swContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continuar = findViewById(R.id.btnContinue);
        cancelar = findViewById(R.id.btnCancel);
        swStorage = findViewById(R.id.swStorage);
        swCamera = findViewById(R.id.swCamera);
        swLocation = findViewById(R.id.swLocation);
        swPhone = findViewById(R.id.swPhone);
        swContacts = findViewById(R.id.swContacts);

        swStorage.setChecked(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED);
        swCamera.setChecked(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED);
        swLocation.setChecked(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED);
        swPhone.setChecked(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED);
        swContacts.setChecked(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED);


        continuar.setOnClickListener(v->{
            boolean otorgado = false;
            ArrayList<String>perms = new ArrayList<>();
            if(swStorage.isChecked()){
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    perms.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                }
            }
            if(swCamera.isChecked()){
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
                    perms.add(Manifest.permission.CAMERA);
                }
            }
            if(swLocation.isChecked()){
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                    perms.add(Manifest.permission.ACCESS_FINE_LOCATION);
                }
            }
            if(swPhone.isChecked()){
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
                    perms.add(Manifest.permission.CALL_PHONE);
                }
            }
            if(swContacts.isChecked()){
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED){
                    perms.add(Manifest.permission.READ_CONTACTS);
                }
            }
            Intent intent = new Intent(v.getContext(),PermissionActivity.class);
            otorgado = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED;

            if(perms.size()>0) {
                ActivityCompat.requestPermissions(MainActivity.this, perms.toArray(new String [0]),permission);
            }else if (otorgado)
                startActivity(intent);
        });

        cancelar.setOnClickListener(v->{
            finish();

        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == permission) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Permisos otorgados", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), PermissionActivity.class);
                startActivity(intent);
            }
        }
    }
}