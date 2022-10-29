package com.example.domek123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.File;

public class PhotoActivity extends AppCompatActivity {
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        layout = findViewById(R.id.layout);
        Bundle bundle = getIntent().getExtras();
        String folder = bundle.getString("folderName").toString();
        File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File dir = new File(pic, "DominikWojcik/" + folder);
        dir.mkdir();
        for (int i =0;i<dir.listFiles().length;i++) {
            String imagepath = dir.listFiles()[i].getPath();                           // pobierz scieżkę z obiektu File
            Bitmap bmp = betterImageDecode(imagepath);
            ImageView img = new ImageView(PhotoActivity.this);// własna funkcja betterImageDecode opisana jest poniżej
            img.setImageBitmap(bmp);// wstawienie bitmapy do ImageView
            img.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,900));
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PhotoActivity.this, OnePhoto.class);
                    intent.putExtra("path",imagepath);
                    startActivity(intent);
                }
            }
                );
            layout.addView(img);

        }
    }
    private Bitmap betterImageDecode(String filePath) {
        Bitmap myBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();    //opcje przekształcania bitmapy
        options.inSampleSize = 4; // zmniejszenie jakości bitmapy 4x
        //
        myBitmap = BitmapFactory.decodeFile(filePath, options);
        return myBitmap;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}