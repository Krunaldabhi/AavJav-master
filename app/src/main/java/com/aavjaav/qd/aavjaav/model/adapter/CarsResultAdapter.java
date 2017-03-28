package com.aavjaav.qd.aavjaav.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.aavjaav.qd.aavjaav.R;
import com.aavjaav.qd.aavjaav.model.pojo.searchcar.SearchCarResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CarsResultAdapter extends RecyclerView.Adapter<CarsResultAdapter.CarResultHolder> implements Filterable {

    private static final String TAG = "CarResultAdapter";
    private ArrayList<SearchCarResponse.Result> mCars;
    private ArrayList<SearchCarResponse.Result> mFilteredCars;
    private Context mContext;
    private CarFilter mFilter = new CarFilter();

    public CarsResultAdapter(ArrayList<SearchCarResponse.Result> mCars, Context mContext) {
        this.mCars = mCars;
        this.mFilteredCars = mCars;
        this.mContext = mContext;
    }

    public void swap(ArrayList<SearchCarResponse.Result> list) {
        if (mFilteredCars != null) {
            mFilteredCars.clear();
            mFilteredCars.addAll(list);
        } else {
            mFilteredCars = list;
        }
        notifyDataSetChanged();
    }

    @Override
    public CarResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.view_car_row, parent, false);
        return new CarResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarResultHolder holder, int position) {
        SearchCarResponse.Result car = mFilteredCars.get(position);
        holder.mCarType.setText(car.getVenderCarName());
        holder.mCarName.setText(car.getVehicleCompnayName());
        holder.mCarPrice.setText(car.getMbill() + " EUR");
        holder.mVendorName.setText(car.getVendorName());
        Picasso.with(mContext).load(car.getUrl()).placeholder(R.drawable.ic_placeholder).into(holder.mCarImage);
    }

    @Override
    public int getItemCount() {
        if (mFilteredCars == null) {
            return 0;
        } else {
            return mFilteredCars.size();
        }
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    class CarResultHolder extends RecyclerView.ViewHolder {
        private TextView mCarName;
        private TextView mCarType;
        private TextView mCarPrice;
        private TextView mVendorName;
        private ImageView mCarImage;

        public CarResultHolder(View itemView) {
            super(itemView);
            mCarName = (TextView) itemView.findViewById(R.id.car_row_car_name);
            mCarType = (TextView) itemView.findViewById(R.id.car_row_car_type);
            mCarPrice = (TextView) itemView.findViewById(R.id.car_row_car_price);
            mCarImage = (ImageView) itemView.findViewById(R.id.car_row_car_image);
            mVendorName = (TextView) itemView.findViewById(R.id.car_row_car_vendor);


        }
    }


    class CarFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String[] constraints = constraint.toString().split("-");


            FilterResults filterResults = new FilterResults();

            final ArrayList<SearchCarResponse.Result> originalList = mCars;

            int count = originalList.size();
            final ArrayList<SearchCarResponse.Result> newList = new ArrayList<SearchCarResponse.Result>(count);

            String filterableString;

            if (constraint.length() == 0) {
                newList.addAll(originalList);
            } else {
                for (int j = 0; j < constraints.length; j++) {
                    String filterString = constraints[j].toLowerCase();
                    for (int i = 0; i < count; i++) {
                        filterableString = originalList.get(i).getVenderCarName();
                        if (filterableString.toLowerCase().equals(filterString)) {
                            newList.add(originalList.get(i));
                        }
                    }
                }
            }

            filterResults.count = newList.size();
            filterResults.values = newList;

            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mFilteredCars = (ArrayList<SearchCarResponse.Result>) results.values;
            notifyDataSetChanged();
        }
    }

}
