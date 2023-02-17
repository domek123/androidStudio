package com.example.domek123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.domek123.Activities.MainActivity;
import com.example.domek123.Activities.NotesActivity;
import com.example.domek123.Helper.ImageData;

import java.util.ArrayList;

public class CollageActivity extends AppCompatActivity {
    private LinearLayout firstCollage;
    private LinearLayout secondCollage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collage);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        firstCollage = findViewById(R.id.firstCollage);
        firstCollage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<ImageData> list = new ArrayList<>();
                list.add(new ImageData(0,0,size.x/2,size.y));
                list.add(new ImageData(size.x/2,0,size.x/2,size.y/2));
                list.add(new ImageData(size.x/2,size.y/2,size.x/2,size.y/2));
                Intent intent = new Intent(CollageActivity.this, CollageMakerActivity.class);
                intent.putExtra("list",list);
                startActivity(intent);
            }
        });
        secondCollage = findViewById(R.id.secondCollage);
        secondCollage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ImageData> list = new ArrayList<>();
                list.add(new ImageData(0,0,size.x,size.y/2));
                list.add(new ImageData(0,size.y/2,size.x/2,size.y/2));
                list.add(new ImageData(size.x/2,size.y/2,size.x/2,size.y/2));
                Intent intent = new Intent(CollageActivity.this, CollageMakerActivity.class);
                intent.putExtra("list",list);
                startActivity(intent);
            }
        });
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