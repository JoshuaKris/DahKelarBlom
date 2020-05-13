package com.example.dahkelarblom.view.userMenu.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.Merchant;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private TextView tv_location;
    private FrameLayout fl_merchantsList;
    private CardView cv_location_here;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.fetchText("Jakarta Barat");
        homeViewModel.fetchMerchantList();

        tv_location = root.findViewById(R.id.tv_locationName);
        fl_merchantsList = root.findViewById(R.id.fl_merchantsList);
        cv_location_here = root.findViewById(R.id.cv_location_here);

        initLiveData();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cv_location_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "change location", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void replaceFragment(Fragment tempFragment) {
        getFragmentManager().beginTransaction()
                .add(R.id.fl_merchantsList, tempFragment)
                .commit();
    }

    private void initLiveData () {
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tv_location.setText(s);
            }
        });

        homeViewModel.getMerchantList().observe(getViewLifecycleOwner(), new Observer<List<Merchant>>() {
            @Override
            public void onChanged(List<Merchant> merchantList) {
                if (merchantList != null) {
                    replaceFragment(MerchantListFragment.newInstance((ArrayList<Merchant>) merchantList));
                }
            }
        });
    }
}
