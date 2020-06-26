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

import com.example.dahkelarblom.BaseVMF;
import com.example.dahkelarblom.DialogChooseFragment;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.DialogItem;
import com.example.dahkelarblom.model.responses.ViewAllMerchants;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment implements DialogChooseFragment.OnInputListener {

    private HomeViewModel homeViewModel;
    private final ArrayList<DialogItem> dialogItemList = new ArrayList<>();
    private String dialogInput = "";
    private DialogChooseFragment dialogChooseFragment;
    private ArrayList<ViewAllMerchants> merchantSwap = new ArrayList<>();
    private ArrayList<ViewAllMerchants> merchantTemp = new ArrayList<>();
    private HashMap<Integer, String> locationListTemp = new HashMap<>();

    private TextView tv_location;
    private FrameLayout fl_merchantsList;
    private CardView cv_location_here;
    private ImageView iv_location;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        BaseVMF factory = new BaseVMF<>(new HomeViewModel(getContext()));
        homeViewModel = ViewModelProviders.of(this,factory).get(HomeViewModel.class);
        initLiveData();
        homeViewModel.fetchText("Pilih Wilayah");
//        homeViewModel.fetchMerchantData();
        homeViewModel.fetchLocationData();
        homeViewModel.fetchMerchantList();

        tv_location = root.findViewById(R.id.tv_locationName);
        fl_merchantsList = root.findViewById(R.id.fl_merchantsList);
        cv_location_here = root.findViewById(R.id.cv_location_here);
        iv_location = root.findViewById(R.id.iv_location);


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

        homeViewModel.getLocationList().observe(getViewLifecycleOwner(), new Observer<HashMap<Integer, String>>() {
            @Override
            public void onChanged(HashMap<Integer, String> locationList) {
                if (locationList != null) {
                    locationListTemp = locationList;
                    DialogItem item;
                    for (int i = 0; i < locationList.size(); i++) {
                        item = new DialogItem(locationList.get(i+1),false);
                        dialogItemList.add(item);
                    }
                }
            }
        });

        homeViewModel.getTestMerchantList().observe(getViewLifecycleOwner(), new Observer<ArrayList<ViewAllMerchants>>() {
            @Override
            public void onChanged(ArrayList<ViewAllMerchants> viewAllMerchants) {
                if (viewAllMerchants != null) {
                    merchantTemp = viewAllMerchants;
                    replaceFragment(MerchantListFragment.newInstance(viewAllMerchants));
                }
            }
        });
    }

    @Override
    public void sendInput(String input) {
        dialogInput = input;
        String locId = "1";
        if (dialogInput.equals("Pilih Wilayah")) {
            iv_location.setVisibility(View.GONE);
        } else {
            tv_location.setText(dialogInput);
            iv_location.setVisibility(View.VISIBLE);
            merchantSwap.clear();
            for (Integer key : locationListTemp.keySet()) {
                String val = locationListTemp.get(key);
                if (dialogInput.equalsIgnoreCase(val)) {
                    locId = String.valueOf(key);
                }
            }
            for (ViewAllMerchants merchant : merchantTemp) {
                if (merchant.getMerchantLocationId().equalsIgnoreCase(locId)) {
                    merchantSwap.add(merchant);
                }
            }
            replaceFragment(MerchantListFragment.newInstance((ArrayList<ViewAllMerchants>) merchantSwap));
        }
    }
}
