package com.rodion.meditationapp.view.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.rodion.meditationapp.data.network.model.Feeling;
import com.rodion.meditationapp.databinding.FeelingItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FeelingsAdapter extends RecyclerView.Adapter<FeelingsAdapter.FeelingsViewHolder> {
    LiveData<List<Feeling>> feelingsList;
    Context context;

    public static class FeelingsViewHolder extends RecyclerView.ViewHolder {
        FeelingItemBinding viewBinding;

        public FeelingsViewHolder(@NonNull FeelingItemBinding itemBinding) {
            super(itemBinding.getRoot());
            viewBinding = itemBinding;
        }
    }

    public FeelingsAdapter(LiveData<List<Feeling>> data, Context context) {
        feelingsList = data;
        this.context = context;
    }

    @NonNull
    @Override
    public FeelingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeelingsViewHolder(FeelingItemBinding.inflate(LayoutInflater.from(context)));
    }

    @Override
    public void onBindViewHolder(@NonNull FeelingsViewHolder holder, int position) {
        List<Feeling> f = feelingsList.getValue();
        if(f != null) {
            Feeling itm = f.get(position);
            holder.viewBinding.feelingTitle.setText(itm.title);
            Picasso.get()
                    .load(itm.image)
                    .into(holder.viewBinding.feelingImage);
        }
    }

    @Override
    public int getItemCount() {
        List<Feeling> f = feelingsList.getValue();
        return (f == null) ? 0 : f.size();
    }
}
