package com.rodion.filmprojecttest.view.StartScreen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.rodion.filmprojecttest.data.network.model.Film;
import com.rodion.filmprojecttest.data.network.service.ImageResolver;
import com.rodion.filmprojecttest.databinding.BigMovieItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BigItemAdapter extends RecyclerView.Adapter<BigItemAdapter.BigItemViewHolder> {
    Context context;
    LiveData<List<Film>> films;

    public static class BigItemViewHolder extends RecyclerView.ViewHolder {
        public BigMovieItemBinding binding;
        public BigItemViewHolder(@NonNull BigMovieItemBinding itemBinding) {
            super(itemBinding.getRoot());
            binding = itemBinding;
        }
    }

    public BigItemAdapter(Context context, LiveData<List<Film>> films) {
        this.films = films;
        this.context = context;
    }

    @NonNull
    @Override
    public BigItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BigItemViewHolder(BigMovieItemBinding.inflate(LayoutInflater.from(context)));
    }

    @Override
    public void onBindViewHolder(@NonNull BigItemViewHolder holder, int position) {
        List<Film> fl = films.getValue();
        if((fl != null)&&(!fl.isEmpty())) {
            Film itm = fl.get(position);
            holder.binding.filmText.setText(itm.title);
            String posterUrl = ImageResolver.getInstance().getPosterUrl(itm.poster_path);
            Picasso.get()
                    .load(posterUrl)
                    .fit()
                    .centerCrop()
                    .into(holder.binding.imagePoster);
        }
    }

    @Override
    public int getItemCount() {
        List<Film> fl = films.getValue();
        return (fl != null) ? fl.size() : 0;
    }
}
