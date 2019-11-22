package com.example.weatherapp.Adapters.DaysAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Adapters.DaysAdapter.Holder.DaysHolder;
import com.example.weatherapp.Adapters.HourlyAdapter.Holder.HourlyHolder;
import com.example.weatherapp.Models.Daily;
import com.example.weatherapp.Models.Hourly;
import com.example.weatherapp.R;

import java.util.ArrayList;

public class DailyAdapter extends RecyclerView.Adapter<DaysHolder> {
    private ArrayList<Daily> dailies = new ArrayList<>();

    public void addDailys(ArrayList<Daily> dailies){
        this.dailies = dailies;
        notifyDataSetChanged();
    }

    @Override
    public DaysHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_day, parent, false);
        return new DaysHolder(view);
    }

    @Override
    public void onBindViewHolder(DaysHolder holder, final int position) {
        Daily daily = dailies.get(position);
        if (daily ==  null){
            return;
        }
        holder.populateHolder(daily);
    }

    @Override
    public int getItemCount() {
        return dailies.size();
    }
}
