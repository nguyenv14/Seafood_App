package com.nhanlovecode.doancuoiky.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nhanlovecode.doancuoiky.R;

import java.util.List;
import java.util.Locale;

public class SpinnerAdapter extends ArrayAdapter<String> {

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_spinner, parent, false);

        TextView tvNameCategorySelected = convertView.findViewById(R.id.tvCategoryNameSelected);

        String name = this.getItem(position).toString().toUpperCase();
        if(name.isEmpty()){
            tvNameCategorySelected.setText(name);
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getDropDownView(position, convertView, parent);
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_spinner_dropdown, parent, false);
        TextView tvnameCategory = convertView.findViewById(R.id.tvCategoryName);

        String nameCategory = this.getItem(position);

        if(nameCategory.isEmpty()){
            tvnameCategory.setText(nameCategory);
        }

        return convertView;
    }
}
