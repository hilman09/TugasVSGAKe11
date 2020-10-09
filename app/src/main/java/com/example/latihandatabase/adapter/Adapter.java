package com.example.latihandatabase.adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.latihandatabase.R;
import com.example.latihandatabase.model.Data;


import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
     private LayoutInflater inflater;
     private List<Data> items;

    public Adapter(Activity activity, List <Data> items) {
        this.activity = activity;
        this.items = items;


    }

    @Override
    public int getCount() {

        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } else if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row, null);

            TextView textId = convertView.findViewById(R.id.textId);
            TextView nama = convertView.findViewById(R.id.nama);
            TextView alamat = convertView.findViewById(R.id.alamat);

            Data data = items.get(position);

            textId.setText(data.get_id());
            nama.setText(data.get_name());
            alamat.setText(data.get_address());



        }
        return convertView;
    }
}
