package com.example.dahkelarblom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dahkelarblom.model.SpinnerStatus;

import java.util.ArrayList;


public class SpinnerStatusAdapter extends ArrayAdapter<SpinnerStatus> {

    public SpinnerStatusAdapter(@NonNull Context context, @NonNull ArrayList<SpinnerStatus> list) {
        super(context, 0, list);

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return init(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return init(position, convertView, parent);
    }

    private View init(int pos, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.spinner_status,parent,false);
        }
        TextView tv_spinner_text = view.findViewById(R.id.tv_spinner_text);
        SpinnerStatus current = getItem(pos);

        if (current != null) {
            tv_spinner_text.setText(current.getStatus());
        }
        return view;
    }
}
