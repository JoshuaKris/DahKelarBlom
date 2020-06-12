package com.example.dahkelarblom.view.menuUser.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dahkelarblom.DialogChooseFragment;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.DialogItem;
import com.example.dahkelarblom.model.Merchant;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class HomeFragment extends Fragment implements DialogChooseFragment.OnInputListener {

    private HomeViewModel homeViewModel;
    private final ArrayList<DialogItem> dialogItemList = new ArrayList<>();
    private String dialogInput = "";
    private DialogChooseFragment dialogChooseFragment;
    private List<Merchant> merchantSwap = new ArrayList<>();
    private List<Merchant> merchantTemp = new ArrayList<>();

    private TextView tv_location;
    private FrameLayout fl_merchantsList;
    private CardView cv_location_here;
    private ImageView iv_location;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.fetchText("Pilih Wilayah");
        homeViewModel.fetchMerchantData();
        homeViewModel.fetchLocationData();

        tv_location = root.findViewById(R.id.tv_locationName);
        fl_merchantsList = root.findViewById(R.id.fl_merchantsList);
        cv_location_here = root.findViewById(R.id.cv_location_here);
        iv_location = root.findViewById(R.id.iv_location);

        initLiveData();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cv_location_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogChooseFragment = DialogChooseFragment.newInstance(dialogItemList,"Pilih Wilayah");
                dialogChooseFragment.show(getFragmentManager(), "dialogChooseOrder");
                dialogChooseFragment.setListener(HomeFragment.this);
            }
        });
    }

    private void replaceFragment(Fragment tempFragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.fl_merchantsList, tempFragment)
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
                    merchantTemp = merchantList;
                    replaceFragment(MerchantListFragment.newInstance((ArrayList<Merchant>) merchantList));
                }
            }
        });

        homeViewModel.getLocationList().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> locationList) {
                if (locationList != null) {
                    DialogItem item;
                    for (String loc : locationList) {
                        item = new DialogItem(loc,false);
                        dialogItemList.add(item);
                    }
                }
            }
        });
    }

    @Override
    public void sendInput(String input) {
        dialogInput = input;
        if (dialogInput.equals("Pilih Wilayah")) {
            iv_location.setVisibility(View.GONE);
        } else {
            tv_location.setText(dialogInput);
            iv_location.setVisibility(View.VISIBLE);
            merchantSwap.clear();
            for (Merchant merchant : merchantTemp) {
                if (merchant.getMerchantAddress().contains(dialogInput)) {
                    merchantSwap.add(merchant);
                }
            }
            replaceFragment(MerchantListFragment.newInstance((ArrayList<Merchant>) merchantSwap));
        }
    }
}
