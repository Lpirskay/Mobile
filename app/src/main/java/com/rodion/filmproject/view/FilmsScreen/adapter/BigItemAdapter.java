package com.rodion.filmproject.view.FilmsScreen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.rodion.filmproject.data.network.model.Film;
import com.rodion.filmproject.data.network.service.ImageResolver;
import com.rodion.filmproject.databinding.BigMovieItemBinding;
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
            holder.binding.ratingBar.setRating(itm.vote_average/2);
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
