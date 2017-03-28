package com.aavjaav.qd.aavjaav.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aavjaav.qd.aavjaav.R;
import com.aavjaav.qd.aavjaav.model.pojo.mybookings.BookingsResponse;

import java.util.ArrayList;


public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.BookingsHolder> {

    private ArrayList<BookingsResponse.Result> mBookings;
    private Context mContext;

    public BookingsAdapter(Context context, ArrayList<BookingsResponse.Result> currentBookings) {
        mBookings = currentBookings;
        mContext = context;
    }

    public void swap(ArrayList<BookingsResponse.Result> list) {
        if (mBookings != null) {
            mBookings.clear();
            mBookings.addAll(list);
        } else {
            mBookings = list;
        }
        notifyDataSetChanged();
    }


    @Override
    public BookingsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.view_bookings_row, parent, false);
        return new BookingsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookingsHolder holder, int position) {
        holder.mCarName.setText(mBookings.get(position).getVehicleCompanyName());
        holder.mCarType.setText(mBookings.get(position).getTypeName());
        holder.mPickAddress.setText(mBookings.get(position).getPickUpAddress());
        holder.mDropAddress.setText(mBookings.get(position).getDropAddress());
        holder.mDate.setText(mBookings.get(position).getPickUpDate() + " at "
                + mBookings.get(position).getPickUpTime());
        //Picasso.with(mContext).load(R.drawable.ic_placeholder).placeholder(R.drawable.ic_placeholder).into(holder.mCarImage);

    }

    @Override
    public int getItemCount() {
        return mBookings.size();
    }

    class BookingsHolder extends RecyclerView.ViewHolder {
        private TextView mCarName;
        private TextView mCarType;
        private TextView mPickAddress;
        private TextView mDropAddress;
        private TextView mDate;
        private ImageView mCarImage;

        public BookingsHolder(View itemView) {
            super(itemView);
            mCarName = (TextView) itemView.findViewById(R.id.bookings_row_car_name);
            mCarType = (TextView) itemView.findViewById(R.id.bookings_row_car_type);
            mPickAddress = (TextView) itemView.findViewById(R.id.bookings_row_pick_up_location);
            mDropAddress = (TextView) itemView.findViewById(R.id.bookings_row_drop_location);
            mDate = (TextView) itemView.findViewById(R.id.bookings_row_date);
            mCarImage = (ImageView) itemView.findViewById(R.id.bookings_row_car_image);


        }
    }
}
