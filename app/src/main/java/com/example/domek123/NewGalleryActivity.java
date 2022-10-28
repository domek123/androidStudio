package com.example.domek123;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewGalleryActivity extends AppCompatActivity {
    private ListView listView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_gallery);
        Bundle bundle = getIntent().getExtras();
        String folder = bundle.getString("folderName");
        File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File dir = new File(pic, "DominikWojcik/" + folder);
        dir.mkdir();
        Log.d("AAA",dir.listFiles().length + " ");
        ArrayList<File> list = new ArrayList<>(
                Arrays.asList(dir.listFiles())
        );
        Log.d("AAA",list.size() + " ");
        listView1 = findViewById(R.id.listViewID);
        TestAdapter adapter = new TestAdapter (
                NewGalleryActivity.this,
                R.layout.gallery_item,
                list
        );
        listView1.setAdapter(adapter);
    }
}