package com.example.dahkelarblom.view.menuUser.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.Merchant;
import com.example.dahkelarblom.view.OrderActivity;

import java.util.ArrayList;

public class MerchantListFragment extends Fragment implements MerchantListAdapter.OnClickListener {

    private static final String MERCHANT_DATA = "merchant_data";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private ArrayList<Merchant> merchantArrayList;

    public MerchantListFragment() {
    }

    public static MerchantListFragment newInstance(ArrayList<Merchant> merchantList) {
        MerchantListFragment fragment = new MerchantListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(MERCHANT_DATA, merchantList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            merchantArrayList = getArguments().getParcelableArrayList(MERCHANT_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
//            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));
            recyclerView.setAdapter(new MerchantListAdapter(merchantArrayList, this));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(int pos) {
        startActivity(new Intent(getActivity(),OrderActivity.class));
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Merchant item);
    }
}
