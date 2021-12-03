package com.rodion.meditationapp.view.dashboard.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.rodion.meditationapp.data.network.model.Quote;
import com.rodion.meditationapp.databinding.QuoteItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder> {

    LiveData<List<Quote>> quoteList;

    public static class QuotesViewHolder extends RecyclerView.ViewHolder {
        public QuoteItemBinding viewBinding;
        public QuotesViewHolder(@NonNull QuoteItemBinding itemView) {
            super(itemView.getRoot());
            viewBinding = itemView;
        }
    }

    public QuotesAdapter(LiveData<List<Quote>> data) {
        quoteList = data;
    }

    @NonNull
    @Override
    public QuotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuoteItemBinding quoteItemBinding = QuoteItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new QuotesViewHolder(quoteItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesViewHolder holder, int position) {
        List<Quote> q = quoteList.getValue();
        if((q != null)&&(!q.isEmpty())) {
            Quote itm = q.get(position);
            holder.viewBinding.textTitle.setText(itm.getTitle());
            holder.viewBinding.textDescription.setText(itm.getDescription());
            Picasso.get()
                    .load(itm.getImage())
                    .fit()
                    .centerInside()
                    .into(holder.viewBinding.imageView);
        }
    }

    @Override
    public int getItemCount() {
        List<Quote> q = quoteList.getValue();
        return (q == null) ? 0 : q.size();
    }

}
