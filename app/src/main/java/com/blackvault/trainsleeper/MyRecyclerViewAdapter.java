package com.blackvault.trainsleeper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Blackvault on 29/05/2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {

    private static MyClickListener myClickListener;
    private static ArrayList<Station> mStationDataSet;

    public MyRecyclerViewAdapter(ArrayList<Station> aStationDataSet) {
        mStationDataSet = aStationDataSet;

    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.stationText.setText(mStationDataSet.get(position).getName());
        holder.latitudeText.setText("Lat: " + Double.toString(mStationDataSet.get(position).getLatitude()));
        holder.longitudeText.setText("Long:" + Double.toString(mStationDataSet.get(position).getLongtide()));
        holder.distanceFromCurrentLocationText.setText(Double.toString(mStationDataSet.get(position).getDistanceFromCurrentLocation()) + " meters");
    }

    public void addItem(Station dataObj, int index) {
        mStationDataSet.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mStationDataSet.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mStationDataSet.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView stationText;
        TextView latitudeText;
        TextView longitudeText;
        TextView distanceFromCurrentLocationText;

        public DataObjectHolder(View itemView) {
            super(itemView);
            stationText = (TextView) itemView.findViewById(R.id.textStationName);
            latitudeText = (TextView) itemView.findViewById(R.id.textLatitude);
            longitudeText = (TextView) itemView.findViewById(R.id.textLongitude);
            distanceFromCurrentLocationText = (TextView) itemView.findViewById(R.id.textDistanceFromCurrentLocation);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);

            Station station = mStationDataSet.get(getAdapterPosition());
            Toast.makeText(v.getContext(), "User Clicked on Station: " + station.getName(), Toast.LENGTH_LONG).show();


        }
    }
}
