package com.example.dahkelarblom;

import android.app.Dialog;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahkelarblom.model.DialogItem;
import com.example.dahkelarblom.utils.Constants;

import java.util.ArrayList;
import java.util.Objects;

public class DialogChooseFragment extends DialogFragment implements DialogChooseAdapter.OnClickListener {
    private static String DATA_ = "dialog_data";
    private static String TITLE_ = "dialog_title";

    private static String inputRadio;
    private OnInputListener mOnInputListener;
    private DialogChooseAdapter adapter;
    private RecyclerView rv_dialog_items;
    private Integer indexSelected;

    private ArrayList<DialogItem> dataFilter = new ArrayList<>();
    private String dialogTitle;

    public DialogChooseFragment() {
        // Required empty public constructor
    }

    public static DialogChooseFragment newInstance(ArrayList<DialogItem> dataFilter, String title) {
        DialogChooseFragment fragment = new DialogChooseFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(DATA_, dataFilter);
        args.putString(TITLE_,title);
        fragment.setArguments(args);
        return fragment;
    }

    public static DialogChooseFragment newInstance(String[] dataFilter, String title) {
        DialogChooseFragment fragment = new DialogChooseFragment();
        Bundle args = new Bundle();
        DialogItem item;
        ArrayList<DialogItem> dataThis = new ArrayList<>();
        for (String s : dataFilter) {
            item = new DialogItem(s, false);
            dataThis.add(item);
        }
        args.putParcelableArrayList(DATA_, dataThis);
        args.putString(TITLE_,title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dataFilter = getArguments().getParcelableArrayList(DATA_);
            dialogTitle = getArguments().getString(TITLE_);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Objects.requireNonNull(getDialog().getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCancelable(true);
        View fragmentView = inflater.inflate(R.layout.fragment_dialog_choose, container, false);

        Button bt_cancel = fragmentView.findViewById(R.id.bt_cancel);
        Button bt_ok = fragmentView.findViewById(R.id.bt_ok);
        rv_dialog_items = fragmentView.findViewById(R.id.rv_dialog_items);
        TextView tv_title = fragmentView.findViewById(R.id.tv_title);

        tv_title.setText(dialogTitle);

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indexSelected != null) {
                    inputRadio = dataFilter.get(indexSelected).getItemTitle();
                    if (mOnInputListener != null) {
                        mOnInputListener.sendInput(inputRadio);
                        getDialog().dismiss();
                    }
                } else {
                    getDialog().dismiss();
                }

            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        updateRV();

        return fragmentView;
    }

    public void setListener(OnInputListener onInputListener) {
        mOnInputListener = onInputListener;
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = Objects.requireNonNull(window).getWindowManager().getDefaultDisplay();
        display.getSize(size);
        int height = size.y;
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, (int) (height * 0.75));
        window.setGravity(Gravity.CENTER);
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawableResource(R.drawable.dialog_background);
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog().getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void updateRV() {
        adapter = new DialogChooseAdapter(dataFilter, this);
        LinearLayoutManager filterDurationLM = new LinearLayoutManager(getContext());
        rv_dialog_items.setLayoutManager(filterDurationLM);
        rv_dialog_items.setAdapter(adapter);
    }

    @Override
    public void onClick(int position) {
        for (int i = 0; i < dataFilter.size(); i++) {
            if (i == position) {
                if (!dataFilter.get(i).isSelected()) {
                    dataFilter.get(i).setSelected(true);
                    indexSelected = position;
                }
            } else {
                dataFilter.get(i).setSelected(false);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void inputNull() {
        inputRadio = null;
    }

    public interface OnInputListener {
        void sendInput(String input);
    }
}