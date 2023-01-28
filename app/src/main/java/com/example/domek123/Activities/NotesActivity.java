package com.example.domek123.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.domek123.Adapter.NotesArrayAdapter;
import com.example.domek123.Helper.DatabaseManager;
import com.example.domek123.Helper.Note;
import com.example.domek123.R;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {
    private ListView lv;
    private ArrayList<Note> notes;
    protected void Refresh(){
        DatabaseManager db = new DatabaseManager (
                NotesActivity.this, // activity z galerią zdjęć
                "NotatkiDW.db", // nazwa bazy
                null,
                1 //wersja bazy, po zmianie schematu bazy należy ją zwiększyć
        );
        notes = db.getAll();
        NotesArrayAdapter adapter = new NotesArrayAdapter(
                NotesActivity.this,
                R.layout.note_layout,
                notes

        );
        lv = findViewById(R.id.lv);
        lv.setAdapter(adapter);
        db.close();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Refresh();
        ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(NotesActivity.this);
                String[] options = {"Usuń" , "Edytuj"};
                alert.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        if(options[j] == "Usuń"){
                            DatabaseManager db = new DatabaseManager (
                                    NotesActivity.this, // activity z galerią zdjęć
                                    "NotatkiDW.db", // nazwa bazy
                                    null,
                                    1 //wersja bazy, po zmianie schematu bazy należy ją zwiększyć
                            );
                            db.deleteNote(notes.get(i).getId());
                            Refresh();
                        }else if(options[j] == "Edytuj"){
                            android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(NotesActivity.this);
                            LinearLayout ll = new LinearLayout(NotesActivity.this);
                            ll.setOrientation(LinearLayout.VERTICAL);

                            EditText title = new EditText(NotesActivity.this);
                            title.setText(notes.get(i).getTitle());

                            EditText noteContent = new EditText(NotesActivity.this);
                            noteContent.setText(notes.get(i).getNoteContent());

                            ll.addView(title);
                            ll.addView(noteContent);

                            alert.setView(ll);


                            final int[] selectedColor = {0xffeb2f58};
                            title.setTextColor(selectedColor[0]);


                            int[] arr = new int[]{0xff0099ff, 0xffff5100, 0xffeb2f58, 0xffa841d1, 0xfff765d0, 0xff7fe3bd, 0xff2fa134, 0xff074f0a};
                            LinearLayout colors = new LinearLayout(NotesActivity.this);
                            colors.setOrientation(LinearLayout.HORIZONTAL);
                            for(int i :arr){
                                ImageView x = new ImageView(NotesActivity.this);
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
                                x.setLayoutParams(layoutParams);
                                x.setBackgroundColor(i);
                                colors.addView(x);
                                x.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        selectedColor[0] = i;
                                        title.setTextColor(selectedColor[0]);
                                    }
                                });
                            }
                            ll.addView(colors);
//ok
                            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //wyświetl zmienną which
                                    DatabaseManager db = new DatabaseManager (
                                            NotesActivity.this, // activity z galerią zdjęć
                                            "NotatkiDW.db", // nazwa bazy
                                            null,
                                            1 //wersja bazy, po zmianie schematu bazy należy ją zwiększyć
                                    );

                                    int color = selectedColor[0];
                                    db.editNote(notes.get(i).getId(),Integer.toHexString(color),title.getText().toString(),noteContent.getText().toString());
                                    db.close();
                                    Refresh();
                                }

                            });
                            alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alert.show();
                        }

                    }
                });
                alert.show();
                return false;
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