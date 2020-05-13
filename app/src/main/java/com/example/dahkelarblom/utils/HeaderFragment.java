package com.example.dahkelarblom.utils;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.dahkelarblom.R;

import java.util.Objects;

public class HeaderFragment extends Fragment {

    private CardView cv_headerContainer;
    private ImageButton
            ib_backButton,
            ib_rightButton;
    private RelativeLayout
            rl_header_decoration;
    private ImageView
            iv_title_icon_sub;
    private TextView
            tvRightButton;
    private TextView tv_headerTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_header, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cv_headerContainer = view.findViewById(R.id.cv_headerContainer);
        ib_backButton = view.findViewById(R.id.ib_backButton);
        rl_header_decoration = view.findViewById(R.id.rl_header_decoration);
        iv_title_icon_sub = view.findViewById(R.id.iv_title_icon_sub);
        ib_rightButton = view.findViewById(R.id.ib_rightButton);
        tv_headerTitle = view.findViewById(R.id.tv_headerTitle);
        tvRightButton = view.findViewById(R.id.tv_rightButton);

        ib_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                Objects.requireNonNull(getActivity()).finish();
            }
        });

    }

    public void justBackButton() {
        tv_headerTitle.setVisibility(View.INVISIBLE);
        cv_headerContainer.setBackgroundColor(Color.TRANSPARENT);
        cv_headerContainer.setElevation(0);
    }

    public void backAndTitle(String title) {
        tv_headerTitle.setText(title);
        cv_headerContainer.setBackgroundColor(Color.WHITE);
    }

    public void backAndTitleWhenStringIsLong(String title) {
        tv_headerTitle.setText(title);
        cv_headerContainer.setBackgroundColor(Color.WHITE);
        tv_headerTitle.setPadding(10, 0, 10, 0);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tv_headerTitle.getLayoutParams();
        params.addRule(RelativeLayout.END_OF, R.id.ib_backButton);
        params.addRule(RelativeLayout.START_OF, R.id.ib_rightButton);
    }

    public void fullFeature(String title, int icon) {
        tv_headerTitle.setText(title);
        ib_rightButton.setImageResource(icon);
        tvRightButton.setVisibility(View.INVISIBLE);
        ib_rightButton.setVisibility(View.VISIBLE);
    }

    public void justTitle(String title) {
        tv_headerTitle.setText(title);
        ib_backButton.setVisibility(View.INVISIBLE);
    }

    public void justWhiteBack() {
        ib_backButton.setColorFilter(Color.argb(255, 255, 255, 255));
        cv_headerContainer.setBackgroundColor(Color.TRANSPARENT);
        cv_headerContainer.setElevation(0);
    }

    public void titleAndIcon(String title, int icon) {
        tv_headerTitle.setText(title);
        ib_rightButton.setImageResource(icon);
        ib_rightButton.setVisibility(View.VISIBLE);
        ib_backButton.setVisibility(View.INVISIBLE);
    }

    public void fullFeatureNoElevation(String title, int icon) {
        tv_headerTitle.setText(title);
        tvRightButton.setVisibility(View.INVISIBLE);
        ib_rightButton.setBackgroundResource(icon);
        ib_rightButton.setVisibility(View.VISIBLE);
        cv_headerContainer.setElevation(0);
    }

    public void fullFeatureRightButton(String title, String rightButton) {
        tv_headerTitle.setText(title);
        ib_rightButton.setVisibility(View.INVISIBLE);
        tvRightButton.setVisibility(View.VISIBLE);
        tvRightButton.setText(rightButton);
    }

//    public void flexibleContainer(String title) {
//        tv_headerTitle.setText(title);
//        ib_backButton.setImageResource(R.drawable.ic_close_flexible_container);
//    }
//
    public void headerV2(String title, boolean withIcon, boolean withRightBtn) {
        tv_headerTitle.setText(title);
        tv_headerTitle.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

        ib_rightButton.setImageResource(R.drawable.ic_anim_overflow_to_down);
        ib_backButton.setImageResource(R.drawable.ic_chevron_left_black_24dp);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tv_headerTitle.getLayoutParams();
        params.addRule(RelativeLayout.END_OF, R.id.rl_header_decoration);
        params.addRule(RelativeLayout.START_OF, R.id.ib_rightButton);

        ib_rightButton.setVisibility(View.VISIBLE);
        ib_backButton.setVisibility(View.VISIBLE);
        rl_header_decoration.setVisibility(View.VISIBLE);
        iv_title_icon_sub.setVisibility(withIcon ? View.VISIBLE : View.GONE);
        ib_rightButton.setVisibility(withRightBtn ? View.VISIBLE : View.GONE);

    }

    public void hideCardElevation() {
        cv_headerContainer.setElevation(0);
    }

    public void fullFeature(String string) {
    }

    public void hideAll() {
        cv_headerContainer.setVisibility(View.GONE);
    }

    public void showAll() {
        cv_headerContainer.setVisibility(View.VISIBLE);
    }
}

