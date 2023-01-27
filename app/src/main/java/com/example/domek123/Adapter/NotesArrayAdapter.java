package com.example.domek123.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.domek123.Helper.Note;
import com.example.domek123.R;

import java.util.ArrayList;
import java.util.List;

public class NotesArrayAdapter extends ArrayAdapter {
    private ArrayList<Note> _list;
    private Context _context;
    private int _resource;
    public NotesArrayAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this._list = (ArrayList<Note>) objects;
        this._context = context;
        this._resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // inflater - klasa konwertująca xml na kod javy
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.note_layout, null);
// convertView = inflater.inflate(_resource, null);
// szukamy każdego TextView w layoucie

        TextView tv1 = (TextView) convertView.findViewById(R.id.tv1);
        tv1.setText("tekst pobrany z listy, getterem");

// gdybyśmy chcieli klikać Imageview wewnątrz wiersza:
        ImageView iv1 = (ImageView) convertView.findViewById(R.id.iv2);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // klik w obrazek
            }
        });

        return convertView;
    }
}
