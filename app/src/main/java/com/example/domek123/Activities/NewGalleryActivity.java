package com.example.domek123.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.domek123.R;
import com.example.domek123.Adapter.TestAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class NewGalleryActivity extends AppCompatActivity {
    private ListView listView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_gallery);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}