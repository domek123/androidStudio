package com.example.domek123;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class OnePhoto extends AppCompatActivity {
    private ImageView img;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_photo);
        img = findViewById(R.id.imag);
        layout = findViewById(R.id.layout);
        layout.setAlpha(0.6f);
        Bundle bundle = getIntent().getExtras();
        String path = bundle.getString("path").toString();
        Bitmap bmp = betterImageDecode(path);
        img.setImageBitmap(bmp);

        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
    private Bitmap betterImageDecode(String filePath) {
        Bitmap myBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();    //opcje przekształcania bitmapy
        options.inSampleSize = 4; // zmniejszenie jakości bitmapy 4x
        //
        myBitmap = BitmapFactory.decodeFile(filePath, options);
        return myBitmap;
    }
}