package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AlbumActivity extends AppCompatActivity {
    String[] array = new String[]{"wynik 1","wynik 2","wynik 3"};
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        list = findViewById(R.id.ListViewId);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                 AlbumActivity.this, // tzw Context
                 R.layout.listview, // nazwa pliku xml naszego wiersza na liście
                 R.id.text, // id pola txt w wierszu
                 array ); // tablica przechowująca testowe dane

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG","numer klikanego wiersza w ListView = " + i);
            }
        });

        }
    }
