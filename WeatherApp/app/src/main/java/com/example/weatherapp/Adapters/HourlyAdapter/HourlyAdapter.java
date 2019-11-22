package com.example.weatherapp.Adapters.HourlyAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Adapters.HourlyAdapter.Holder.HourlyHolder;
import com.example.weatherapp.Models.Hourly;
import com.example.weatherapp.R;

import java.util.ArrayList;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyHolder> {
    private ArrayList<Hourly> hourlies = new ArrayList<>();

    public void addHourlies(ArrayList<Hourly> hourlies){
        this.hourlies = hourlies;
        notifyDataSetChanged();
    }

    @Override
    public HourlyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_hourly, parent, false);
        return new HourlyHolder(view);
    }

    @Override
    public void onBindViewHolder(HourlyHolder holder, final int position) {
        Hourly hourly = hourlies.get(position);
        if (hourly ==  null){
            return;
        }
        holder.populateHolder(hourly);
    }

    @Override
    public int getItemCount() {
        return hourlies.size();
    }
}
