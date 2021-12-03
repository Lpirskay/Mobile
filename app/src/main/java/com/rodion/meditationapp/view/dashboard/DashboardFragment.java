package com.rodion.meditationapp.view.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rodion.meditationapp.databinding.FragmentDashboardBinding;
import com.rodion.meditationapp.view.dashboard.adapter.FeelingsAdapter;
import com.rodion.meditationapp.view.dashboard.adapter.QuotesAdapter;
import com.rodion.meditationapp.view.dashboard.viewmodel.DashboardViewModel;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private QuotesAdapter quotesAdapter;
    private FeelingsAdapter feelingsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        dashboardViewModel.loadFeelings();
        dashboardViewModel.loadQuotes();

        feelingsAdapter = new FeelingsAdapter(dashboardViewModel.feelingsData, getContext());
        dashboardViewModel.feelingsData.observe(getViewLifecycleOwner(), (res) -> {
            feelingsAdapter.notifyDataSetChanged();
        });

        RecyclerView r1 = binding.feelingsRecycler;
        r1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        r1.setHasFixedSize(true);
        r1.setAdapter(feelingsAdapter);

        quotesAdapter = new QuotesAdapter(dashboardViewModel.quotesData);
        dashboardViewModel.quotesData.observe(getViewLifecycleOwner(), (res) -> {
            quotesAdapter.notifyDataSetChanged();
        });

        RecyclerView r2 = binding.quotesRecycler;
        r2.setLayoutManager(new LinearLayoutManager(getContext()));
        r2.setAdapter(quotesAdapter);
        r2.setHasFixedSize(true);


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}