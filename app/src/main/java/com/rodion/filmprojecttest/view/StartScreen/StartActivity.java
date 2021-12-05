package com.rodion.filmprojecttest.view.StartScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rodion.filmprojecttest.R;
import com.rodion.filmprojecttest.databinding.ActivityStartBinding;
import com.rodion.filmprojecttest.view.StartScreen.adapter.BigItemAdapter;
import com.rodion.filmprojecttest.view.StartScreen.viewmodel.StartViewModel;

public class StartActivity extends AppCompatActivity {

    ActivityStartBinding binding;
    StartViewModel viewModel;
    BigItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_FilmProjectTest);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(StartViewModel.class);
        viewModel.getFilms();

        adapter = new BigItemAdapter(this, viewModel.films);
        RecyclerView r = binding.recyclerView;
        r.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        r.setAdapter(adapter);

        viewModel.films.observe(this, (res) -> {
            adapter.notifyDataSetChanged();
        });
    }
}