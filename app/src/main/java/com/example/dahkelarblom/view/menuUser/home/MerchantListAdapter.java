package com.example.dahkelarblom.view.menuUser.home;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.responses.ViewAllMerchants;

import java.util.List;

public class MerchantListAdapter extends RecyclerView.Adapter<MerchantListAdapter.ViewHolder> {

    private List<ViewAllMerchants> mValues;
    private OnClickListener onClickListener;

    public MerchantListAdapter(List<ViewAllMerchants> merchantList, OnClickListener onClickListener) {
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
        public final TextView tv_merchant_name;
        public final TextView tv_merchant_address;
        public final TextView tv_merchant_phonenum;
        public final TextView tv_merchant_email;
        public final TextView tv_merchant_closed;
        public ImageView iv_merchant_image;
        public OnClickListener onClickListener;

        public ViewHolder(View view, OnClickListener onClickListener) {
            super(view);
            tv_merchant_name = view.findViewById(R.id.tv_merchant_name);
            tv_merchant_address = view.findViewById(R.id.tv_merchant_address);
            iv_merchant_image = view.findViewById(R.id.iv_merchant_image);
            tv_merchant_phonenum = view.findViewById(R.id.tv_merchant_phonenum);
            tv_merchant_email = view.findViewById(R.id.tv_merchant_email);
            tv_merchant_closed = view.findViewById(R.id.tv_merchant_closed);

            this.onClickListener = onClickListener;
//            itemView.setOnClickListener(this);
        }

        public void bind(ViewAllMerchants merchant){
            tv_merchant_name.setText(merchant.getMerchantName());
            tv_merchant_address.setText(merchant.getMerchantLocation());
            tv_merchant_phonenum.setText(merchant.getPhoneNum());
            tv_merchant_email.setText(merchant.getMerchantEmail());
            //setImageView
            //iv_merchant_image.
            if (merchant.getMerchantName().equalsIgnoreCase("asdf")) {
                tv_merchant_closed.setVisibility(View.VISIBLE);
                tv_merchant_address.setVisibility(View.GONE);
                tv_merchant_phonenum.setVisibility(View.GONE);
                tv_merchant_email.setVisibility(View.GONE);
            } else {
                itemView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(getAdapterPosition());
        }
    }

    public interface OnClickListener {
        void onClick(int pos);
    }
}
