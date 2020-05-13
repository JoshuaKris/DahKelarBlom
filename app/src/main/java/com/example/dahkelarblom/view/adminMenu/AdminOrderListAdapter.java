package com.example.dahkelarblom.view.adminMenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.BookingModel;
import com.example.dahkelarblom.view.userMenu.home.MerchantListFragment.OnListFragmentInteractionListener;

import java.util.List;

public class AdminOrderListAdapter extends RecyclerView.Adapter<AdminOrderListAdapter.ViewHolder> {

    private List<BookingModel> mValues;
    private OnListFragmentInteractionListener mListener;
    private AdminOrderListOnClickListener onClickListener;

    public AdminOrderListAdapter(List<BookingModel> merchantList, AdminOrderListOnClickListener onClickListener) {
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
            tv_bookingStatus = view.findViewById(R.id.tv_bookingStatus);

            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);
        }

        public void bind(BookingModel bookingModel){
            tv_itemName.setText(bookingModel.getMerchantToBook().getMerchantName());
            tv_content.setText(bookingModel.getMerchantToBook().getMerchantAddress());
            tv_bookingCode.setText(bookingModel.getBookingCode());
            tv_bookingPickup.setText(bookingModel.getBookingPickupTime());
            tv_bookingPrice.setText(bookingModel.getBookingPrice());
            tv_bookingStatus.setText(bookingModel.getBookingStatus());
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
