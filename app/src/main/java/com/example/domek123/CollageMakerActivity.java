package com.example.domek123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.domek123.Helper.ImageData;

import java.util.ArrayList;

public class CollageMakerActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collage_maker);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        frameLayout = findViewById(R.id.frameLayout);
        ArrayList<ImageData> list = (ArrayList<ImageData>) getIntent().getExtras().getSerializable("list");
        Log.d("lista","rozmiar listy 1 "+list.get(1).getX());

        for(int i = 0;i<list.size();i++){
            ImageView iv = new ImageView(CollageMakerActivity.this);
            iv.setX(list.get(i).getX());
            iv.setY(list.get(i).getY());
            iv.setImageResource(R.drawable.addphoto);
            iv.setLayoutParams(new LinearLayout.LayoutParams(list.get(i).getW(),list.get(i).getH()));
            frameLayout.addView(iv);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == android.R.id.home){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}