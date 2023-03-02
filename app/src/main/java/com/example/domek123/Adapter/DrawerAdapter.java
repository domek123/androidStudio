package com.example.domek123.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.domek123.Helper.DrawerItem;
import com.example.domek123.R;
import com.example.domek123.multipart.Multipart;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class DrawerAdapter extends ArrayAdapter {
    private ArrayList<DrawerItem> _list;
    private Context _context;
    private int _resource;
    private byte[] photoToUpload;
    private Bitmap bitmap;

    public DrawerAdapter(@NonNull Context context, int resource, ArrayList<DrawerItem> objects, byte[] byteArray, Bitmap bitmap) {
        super(context, resource, objects);
        this._list = objects;
        this._context = context;
        this._resource = resource;
        this.photoToUpload = byteArray;
        this.bitmap = bitmap;
    }
    public void refresh(){
        notifyDataSetChanged();
    }
    private ProgressDialog pDialog;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.drawer_adapter_item, null);

        LinearLayout ll =  convertView.findViewById(R.id.rowdrawer);
        if(position%2==0){
            ll.setBackgroundColor(0xff303030);
        }
        TextView tv1 = convertView.findViewById(R.id.text);
        tv1.setText(_list.get(position).getTitle());



        if(_list.get(position).getTitle().equals("upload")){
            ImageView iv1 = convertView.findViewById(R.id.icon);
            iv1.setBackgroundResource(R.drawable.edit);
            ll.setOnClickListener(v->{
                ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo netInfoWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (netInfoWifi != null && netInfoWifi.isConnectedOrConnecting())
                {
                    String ip = "192.168.0.1";
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(_context);
                    if (preferences.getString("ip", null) != null){
                        ip = preferences.getString("ip", null)+":3000";
                    }

                    Multipart multipart = new Multipart(_context);
                    String uniqueName =  (ZonedDateTime.now( ZoneOffset.UTC ).format( DateTimeFormatter.ISO_INSTANT ) +"_"+ ThreadLocalRandom.current().nextInt(0, 1000000 + 1)).replace('.','-').replace(':','_');
                    multipart.addFile("image/jpeg", "file", uniqueName+".jpg", this.photoToUpload);
                    String finalIp = ip;
                    Log.d("XXXX","https://"+ip+"/api/upload");
                    multipart.launchRequest("http://"+ip+"/api/upload",
                            response -> {
                                Toast.makeText(_context, "UPLOAD", Toast.LENGTH_SHORT).show();
                            },
                            error -> {
                                Log.d("XXXD",error.toString());
                                Toast.makeText(_context, finalIp, Toast.LENGTH_SHORT).show();
                                Toast.makeText(_context, "ERROR", Toast.LENGTH_SHORT).show();
                            });
                }
                else
                    Toast.makeText(_context, "No internet", Toast.LENGTH_SHORT).show();;
            });
        }

        return convertView;


    }
}
