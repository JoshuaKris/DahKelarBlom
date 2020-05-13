package com.example.dahkelarblom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahkelarblom.model.DialogItem;

import java.util.ArrayList;
import java.util.List;

public class DialogChooseAdapter extends RecyclerView.Adapter<DialogChooseAdapter.DialogFilterDurationViewHolder> {

    private final List<DialogItem> dataList;
    private final OnClickListener rbOnclickListener;

    public DialogChooseAdapter(ArrayList<DialogItem> dataList, OnClickListener onClickListener) {
        this.dataList = dataList;
        this.rbOnclickListener = onClickListener;
    }

    @NonNull
    @Override
    public DialogFilterDurationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.layout_recyclerview_dialog_item, viewGroup, false);
        return new DialogFilterDurationViewHolder(view, rbOnclickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DialogFilterDurationViewHolder dialogFilterDurationViewHolder, int i) {
        DialogItem item = dataList.get(i);
        dialogFilterDurationViewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    public class DialogFilterDurationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView tv_duration;
        final RadioButton rb_duration_select;
        private final OnClickListener rbOnclickListener;

        DialogFilterDurationViewHolder(@NonNull View itemView, OnClickListener rbOnclickListener) {
            super(itemView);
            tv_duration = itemView.findViewById(R.id.tv_itemDesc);
            rb_duration_select = itemView.findViewById(R.id.rb_item);
            this.rbOnclickListener = rbOnclickListener;
        }

        void bind(final DialogItem dialogItem) {
            tv_duration.setText(dialogItem.getItemTitle());
            rb_duration_select.setChecked(dialogItem.isSelected());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            rb_duration_select.setChecked(true);
            rbOnclickListener.onClick(getAdapterPosition());
        }
    }
}
