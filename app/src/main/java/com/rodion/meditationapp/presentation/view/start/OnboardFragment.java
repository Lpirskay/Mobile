package com.rodion.meditationapp.presentation.view.start;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rodion.meditationapp.R;
import com.rodion.meditationapp.databinding.FragmentOnboardBinding;

public class OnboardFragment extends Fragment {

    FragmentOnboardBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentOnboardBinding.inflate(inflater, container, false);
        binding.onboardLogin.setOnClickListener(LoginFragment::open);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}