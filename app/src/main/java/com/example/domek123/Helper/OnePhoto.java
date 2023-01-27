package com.example.domek123.Helper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.domek123.R;

public class OnePhoto extends AppCompatActivity {
    private ImageView img;
    private LinearLayout layout;
    private TextView textView;
    private boolean small = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_photo);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        img = findViewById(R.id.imag);
        layout = findViewById(R.id.layout);
        layout.setAlpha(0.6f);

        Bundle bundle = getIntent().getExtras();
        String path = bundle.getString("path").toString();
        Bitmap bmp = betterImageDecode(path);

        img.setImageBitmap(bmp);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                small = !small;
                if(small){
                    img.setScaleType(ImageView.ScaleType.CENTER);
                }else{
                    img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
            }
        });
    }
    private Bitmap betterImageDecode(String filePath) {
        Bitmap myBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
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