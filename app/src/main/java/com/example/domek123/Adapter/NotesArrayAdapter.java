package com.example.domek123.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        TextView tv1 = convertView.findViewById(R.id.notetitle);
        tv1.setText(_list.get(position).getTitle());

        tv1.setTextColor(Color.parseColor("#" +_list.get(position).getColor()));

        TextView tv2 = convertView.findViewById(R.id.notecontent);
        tv2.setText(_list.get(position).getNoteContent());

        TextView tv3 = convertView.findViewById(R.id.path);
        tv3.setText(_list.get(position).getPhotopath());

        TextView tv4 = convertView.findViewById(R.id.noteid);
        tv4.setText(String.valueOf(_list.get(position).getId()));

        LinearLayout ll = convertView.findViewById(R.id.rownote);
        if(position%2==0){
            ll.setBackgroundColor(0xff777777);
        }
        return convertView;
    }
}
