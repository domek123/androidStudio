package com.example.domek123;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NewGalleryActivity extends AppCompatActivity {
    private ListView listView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_gallery);
        ArrayList<String> list = new ArrayList();
        list.add("a");
        list.add("b");
        listView1 = findViewById(R.id.listViewID);
        TestAdapter adapter = new TestAdapter (
                NewGalleryActivity.this,
                R.layout.listview,
                list
        );
        listView1.setAdapter(adapter);
    }
}