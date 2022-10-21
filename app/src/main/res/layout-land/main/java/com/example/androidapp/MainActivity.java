package com.example.androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout cameraBtn;
    private RelativeLayout albumsBtn;
    private RelativeLayout collageBtn;
    private RelativeLayout networkBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 100);

        cameraBtn = findViewById(R.id.buttonCamera);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CameraActivity.class);
                startActivity(intent);
            }
        });
    }
    public void checkPermission(String permission, int requestCode) {
         if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
         ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
         } else {
         Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
         }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case 100:
//                if (grantResults.length > 0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED) {
//                }else{
//
//                }
//                break;
//            case 101:
//
//                break;
//        }
//    }
}