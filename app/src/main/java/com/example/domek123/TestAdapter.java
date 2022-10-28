package com.example.domek123;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends ArrayAdapter {
    public TestAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this._list= (ArrayList<String>) objects;
        this._context = context;
        this._resource = resource;
        Log.d("XXX",this._list.size() + " ") ;
    }
    private ArrayList<String> _list;
    private Context _context;
    private int _resource;

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        convertView = inflater.inflate(_resource, null);
//        Log.d("XXX",position + "");
//        TextView tv1 = (TextView) convertView.findViewById(R.id.tv1);
//        tv1.setText(_list.get(position));
//
//        ImageView iv1 = (ImageView) convertView.findViewById(R.id.del);
//        iv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("XXX","usuniecie");
//            }
//        });
//        ImageView iv2 = (ImageView) convertView.findViewById(R.id.edit);
//        iv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("XXX","edycja");
//            }
//        });
//        ImageView iv3 = (ImageView) convertView.findViewById(R.id.info);
//        iv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("XXX","info");
//            }
//        });
//        return convertView;
//    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("XXX",position + "");
        return super.getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return _list.size();
    }
}
