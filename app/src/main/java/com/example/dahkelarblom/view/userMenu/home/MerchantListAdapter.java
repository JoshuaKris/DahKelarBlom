package com.example.dahkelarblom.view.userMenu.home;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.Merchant;
import com.example.dahkelarblom.view.userMenu.home.MerchantListFragment.OnListFragmentInteractionListener;

import java.util.List;

public class MerchantListAdapter extends RecyclerView.Adapter<MerchantListAdapter.ViewHolder> {

    private List<Merchant> mValues;
    private OnListFragmentInteractionListener mListener;
    private OnClickListener onClickListener;

    public MerchantListAdapter(List<Merchant> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    public MerchantListAdapter(List<Merchant> merchantList, OnClickListener onClickListener) {
        mValues = merchantList;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view,onClickListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(mValues.get(position));
    }

    @Override
    
    public int getItemCount() {
        return Math.max(mValues.size(), 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mIdView;
        public final TextView mContentView;
        public ImageView mImageView;
        public OnClickListener onClickListener;

        public ViewHolder(View view, OnClickListener onClickListener) {
            super(view);
            mIdView = view.findViewById(R.id.item_name);
            mContentView = view.findViewById(R.id.content);
            mImageView = view.findViewById(R.id.iv_sample);

            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);
        }

        public void bind(Merchant merchant){
            mIdView.setText(merchant.getMerchantName());
            mContentView.setText(merchant.getMerchantAddress());
            //setImageView
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(getAdapterPosition());
        }
    }

    public interface OnClickListener {
        public void onClick(int pos);
    }
}
