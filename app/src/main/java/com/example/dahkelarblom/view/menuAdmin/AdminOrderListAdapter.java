package com.example.dahkelarblom.view.menuAdmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.responses.ViewAllOrderResponse;

import java.util.List;

public class AdminOrderListAdapter extends RecyclerView.Adapter<AdminOrderListAdapter.ViewHolder> {

    private List<ViewAllOrderResponse> mValues;
    private AdminOrderListOnClickListener onClickListener;

    public AdminOrderListAdapter(List<ViewAllOrderResponse> merchantList, AdminOrderListOnClickListener onClickListener) {
        mValues = merchantList;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_booking_item, parent, false);
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
        public final TextView
                tv_itemName,
                tv_content,
                tv_bookingCode,
                tv_bookingPickup,
                tv_bookingPrice,
                tv_bookingType,
                tv_bookingStatus;
        public AdminOrderListOnClickListener
                onClickListener;

        public ViewHolder(View view, AdminOrderListOnClickListener onClickListener) {
            super(view);
            tv_itemName = view.findViewById(R.id.tv_itemName);
            tv_content = view.findViewById(R.id.tv_content);
            tv_bookingCode = view.findViewById(R.id.tv_bookingCode);
            tv_bookingPickup = view.findViewById(R.id.tv_bookingPickup);
            tv_bookingPrice = view.findViewById(R.id.tv_bookingPrice);
            tv_bookingType = view.findViewById(R.id.tv_bookingType);
            tv_bookingStatus = view.findViewById(R.id.tv_bookingStatus);

            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);
        }

        public void bind(ViewAllOrderResponse model){
            tv_itemName.setText(model.getUsername());
            tv_content.setText(model.getNoHp());
            tv_bookingCode.setText(model.getCodeBooking());
            tv_bookingPickup.setText(model.getPengambilanOrder());
            tv_bookingPrice.setText(model.getKeterangan());
            tv_bookingType.setText(model.getJnsOrder());
            String status = model.getStatus();
            if (model.getStatus() == null) {
                status = "file belum diterima";
            }
            tv_bookingStatus.setText(status);
            //setImageView
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(getAdapterPosition());
        }
    }

    public interface AdminOrderListOnClickListener {
        public void onClick(int pos);
    }
}
